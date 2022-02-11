package com.mercadolibre.desafiofinalbootcampgrupo2.services;

import com.mercadolibre.desafiofinalbootcampgrupo2.dao.InboundOrderDAO;
import com.mercadolibre.desafiofinalbootcampgrupo2.dto.BatchDTO;
import com.mercadolibre.desafiofinalbootcampgrupo2.dto.BatchResponseDTO;
import com.mercadolibre.desafiofinalbootcampgrupo2.dto.InboundOrderRequestDTO;
import com.mercadolibre.desafiofinalbootcampgrupo2.exception.DateInvalidException;
import com.mercadolibre.desafiofinalbootcampgrupo2.exception.RepositoryException;
import com.mercadolibre.desafiofinalbootcampgrupo2.exception.RepresentativeInvalidException;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.Batch;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.InboundOrder;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.Representative;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InboundOrderService implements EntityService<InboundOrder> {

    @Autowired
    private SectionService sectionService;

    @Autowired
    private ProductService productService;

    @Autowired
    private AdvertisingService advertisingService;

    @Autowired
    private BatchService batchService;

    @Autowired
    private RepresentativeService representativeService;

    private InboundOrderDAO inboundOrderDAO;

    public InboundOrderService(InboundOrderDAO inboundOrderDAO) {
        this.inboundOrderDAO = inboundOrderDAO;
    }

    @Override
    public InboundOrder findById(Long id) {
        return inboundOrderDAO.findById(id)
                .orElseThrow(() -> new RepositoryException("Inbound Order not exists in database, please contact the administrator"));
    }

    @Transactional
    public List<BatchResponseDTO> saveInboundOrder(InboundOrderRequestDTO orderDTO) {

        InboundOrder order = convertInboundOrderDtoToEntity(orderDTO);
        List<Batch> batches = convertListBatchDtoToEntity(orderDTO.getBatchs());

        order.setBatchs(batches);
        for (Batch batch : batches) {
            batch.setInboundOrder(order);
        }

        allVerification(order, orderDTO.getSection().getWarehouseCode());

        inboundOrderDAO.save(order);

        return batches.stream().map(batch -> new BatchResponseDTO(batch)).collect(Collectors.toList());
    }

    @Transactional
    public List<BatchResponseDTO> updateInboundOrder(InboundOrderRequestDTO orderDTO, Long id) {

        InboundOrder order = convertInboundOrderDtoToEntity(orderDTO);
        order.setId(findById(id).getId());
        List<Batch> batches = convertListBatchDtoToEntity(orderDTO.getBatchs());

        order.setBatchs(batches);
        for (Batch batch : batches) {
            batch.setInboundOrder(order);
        }

        allVerification(order, orderDTO.getSection().getWarehouseCode());

        batchService.deleteAllBatchByInboundOrder(order);
        inboundOrderDAO.save(order);

        List<BatchResponseDTO> batchResponseDTO = new ArrayList<>();
        batches.forEach(batch -> batchResponseDTO.add(new BatchResponseDTO(batch)));
        return batchResponseDTO;
    }

    protected void allVerification(InboundOrder order, Long warehouseId) {
        Long sectionCode = order.getSection().getId();
        Long warehouseCode = warehouseId;
        Long representativeCode = getUserIdLogged();
        List<Batch> batches = order.getBatchs();


        verifyCreationDate(order); // Verifica se a data de criacao é menor ou igual a hoje
        verifyExpirationDate(batches); // Verifica se a data expirou
        verifyManufactureDate(batches); // Verifica se a data do manufactoring é menor que a de hoje


        // Requirement 01 validations
        verifyIfIsARepresentative(representativeCode); //Verifica se é um representante

        sectionService.verifyIfRepresentativeWorksInSection(sectionCode, representativeCode); //E que o representante pertence ao armazém
        sectionService.verifyIfSectorExistsInWarehouse(sectionCode, warehouseCode); // que o armazém é válido E que o setor é válido
        productService.verifyIfProductsAreTheSameTypeOfSection(batches, order.getSection()); //E que o setor corresponde ao tipo de produto
        sectionService.verifyIfSectionHaveSpaceEnoughToAddBatches(order.getSection(), batches); //E que o setor tenha espaço disponível
    }

    public void verifyCreationDate(InboundOrder order) {
        if (order.getCreationDate().isAfter(LocalDate.now()))
            throw new DateInvalidException("Creation date should not be greater than today");
    }

    public void verifyExpirationDate(List<Batch> batchs) {
        LocalDate dateGreater = LocalDate.now().plusDays(21L);

        batchs.forEach(batch ->
                {
                    if (batch.getExpirationDate().isBefore(dateGreater)) {
                        throw new DateInvalidException("Expiration date " + batch.getExpirationDate() + " must be greater than " + dateGreater);
                    }
                }
        );
    }


    private void verifyIfIsARepresentative(Long representativeCode) {
        if (representativeService.findById(representativeCode) == null)
            throw new RepresentativeInvalidException("Representante invalido");
    }

    public void verifyManufactureDate(List<Batch> batchs) {
        batchs.forEach(batch ->
                {
                    if (batch.getManufacturingDate().isAfter(LocalDate.now())) {
                        throw new DateInvalidException("Manufacturing date " + batch.getManufacturingDate() + " must be less than today");
                    }
                }
        );
    }

    private InboundOrder convertInboundOrderDtoToEntity(InboundOrderRequestDTO orderDTO) {
        return InboundOrder.builder()
                .creationDate(orderDTO.getCreationDate())
                .section(sectionService.findById(orderDTO.getSection().getSectionCode()))
                .build();
    }

    private List<Batch> convertListBatchDtoToEntity(List<BatchDTO> batchDTO) {
        return batchDTO.
                stream().
                map(this::convertBatchDtoToEntity).
                collect(Collectors.toList());
    }

    private Batch convertBatchDtoToEntity(BatchDTO batchDTO) {
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

    private Long getUserIdLogged() {
        return ((Representative) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
    }
}