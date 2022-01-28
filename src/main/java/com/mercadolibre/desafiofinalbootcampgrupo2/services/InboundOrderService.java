package com.mercadolibre.desafiofinalbootcampgrupo2.services;

import com.mercadolibre.desafiofinalbootcampgrupo2.dao.InboundOrderDAO;
import com.mercadolibre.desafiofinalbootcampgrupo2.dto.BatchDTO;
import com.mercadolibre.desafiofinalbootcampgrupo2.dto.BatchResponseDTO;
import com.mercadolibre.desafiofinalbootcampgrupo2.dto.InboundOrderDTO;
import com.mercadolibre.desafiofinalbootcampgrupo2.exception.RepositoryException;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.Batch;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.InboundOrder;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.Product;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.Section;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InboundOrderService {

    @Autowired
    private SectionService sectionService;

    @Autowired
    private ProductService productService;

    @Autowired
    private AdvertisingService advertisingService;
    
    @Autowired
    private BatchService batchService;
    
    private InboundOrderDAO inboundOrderDAO;

    public InboundOrderService(InboundOrderDAO inboundOrderDAO) {
        this.inboundOrderDAO = inboundOrderDAO;
    }

    public List<BatchResponseDTO> saveInboundOrder(InboundOrderDTO orderDTO) {

        InboundOrder order = convertInboundOrderDtoToEntity(orderDTO);
        List<Batch> batches = convertListBatchDtoToEntity(orderDTO.getBatchs());

        Long sectionCode = orderDTO.getSection().getSectionCode();
        Long warehouseCode = orderDTO.getSection().getWarehouseCode();

        verifyIfSectorExistsInWarehouse(sectionCode, warehouseCode); // que o armazém é válido E que o setor é válido
        verifyIfRepresentativeWorksInSection(order.getSection(), orderDTO.getRepresentative()); //E que o representante pertence ao armazém
        verifyIfProductsAreTheSameTypeOfSection(batches, order.getSection()); //E que o setor corresponde ao tipo de produto
        verifyIfSectionHaveSpaceEnoughToAddBatches(order.getSection(), batches); //E que o setor tenha espaço disponível

        // TODO VERIFICAR SOBRE O BATCH NAO SALVAR O ID DO ORDER QUANDO PERSISTIR ORDER

        order = inboundOrderDAO.save(order);

        for (Batch batch : batches) {
            batch.setInboundOrder(order);
        }

        order.setBatchs(batchService.saveListBatches(batches));
        Long orderCode = order.getId();

        List<BatchResponseDTO> batchResponseDTO = new ArrayList<>();
        batches.forEach(batch -> batchResponseDTO.add(new BatchResponseDTO(batch, orderCode)));

        return batchResponseDTO;
    }

    private void verifyIfSectorExistsInWarehouse(Long sectionCode, Long warehouseCode) {
        if (!sectionService.findById(sectionCode).getWarehouse().getId().equals(warehouseCode))
            throw new RuntimeException("The mentioned Section don't exists in mentioned Warehouse.");
    }

    public void verifyIfRepresentativeWorksInSection(Section section, Long representativeCode) {
        if (!section.getRepresentative().getId().equals(representativeCode)) {
            throw new RuntimeException("The mentioned Representative don't matches with mentioned Section.");
        }
    }

    public void verifyIfProductsAreTheSameTypeOfSection(List<Batch> batchs, Section section) {
        for (Batch batch : batchs) {
            Product product = productService.findById(batch.getAdvertising().getProduct().getId());
            if (!product.getProductType().equals(section.getProductType()))
                throw new RuntimeException("The mentioned Product don't matches with mentioned Section.");
        }
    }

    public void verifyIfSectionHaveSpaceEnoughToAddBatches(Section section, List<Batch> batchs) {
        for (Batch batch : batchs) {
            if (section.calVolume(batch) < 0) {
                throw new RepositoryException("Space not available in the section, please contact an administrator");
            }
        }
    }

    public InboundOrder convertInboundOrderDtoToEntity(InboundOrderDTO orderDTO) {
        return InboundOrder.builder()
                .creationDate(orderDTO.getCreationDate())
                .section(sectionService.findById(orderDTO.getSection().getSectionCode()))
                .build();
    }

    public List<Batch> convertListBatchDtoToEntity(List<BatchDTO> batchDTO) {
        return batchDTO.
                stream().
                map(this::convertBatchDtoToEntity).
                collect(Collectors.toList());
    }

    public Batch convertBatchDtoToEntity(BatchDTO batchDTO) {
        return Batch.builder()
                .currentTemperature(batchDTO.getCurrentTemperature())
                .currentQuantity(batchDTO.getCurrentQuantity())
                .initialQuantity(batchDTO.getInitialQuantity())
                .expirationDate(batchDTO.getExpirationDate())
                .manufacturingDate(batchDTO.getManufacturingDate())
                .manufacturingTime(batchDTO.getManufacturingTime())
                .minimumTemperature(batchDTO.getMinimumTemperature())
                .advertising(advertisingService.findById(batchDTO.getAdvertsimentId()))
                .build();
    }
}