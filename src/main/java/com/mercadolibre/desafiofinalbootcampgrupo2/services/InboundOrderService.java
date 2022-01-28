package com.mercadolibre.desafiofinalbootcampgrupo2.services;

import com.mercadolibre.desafiofinalbootcampgrupo2.dao.*;
import com.mercadolibre.desafiofinalbootcampgrupo2.dto.BatchDTO;
import com.mercadolibre.desafiofinalbootcampgrupo2.dto.InboundOrderDTO;
import com.mercadolibre.desafiofinalbootcampgrupo2.dto.SectionDTO;
import com.mercadolibre.desafiofinalbootcampgrupo2.exception.RepositoryException;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.Batch;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.InboundOrder;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.Section;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InboundOrderService {

    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private SectionDAO sectionDAO;

    @Autowired
    private AdvertisingDAO advertisingDAO;

    @Autowired
    private InboundOrderDAO orderDAO;

    public void verifyProduct(List<Batch> batchList) {

        batchList.stream().forEach(batch -> productDAO
                .findById(batch.getAdvertising()
                        .getProduct()
                        .getId())
                .orElseThrow(() -> new RepositoryException
                        ("Product not exists in database, please contact the administrator"))
        );

    }

    public void verifyRequest(InboundOrder order) {
        Section section = verifySection(order);
        verifyRepresentative(section, order);
        verifyBatchOrder(section,order);

        order.getBatchs().stream().forEach(batch -> verifyBatchType(section, batch));
        order.getBatchs().stream().forEach(batch -> verifyVolume(section, batch));
    }

    public void verifyRepresentative( Section section, InboundOrder order ){
         if (! section.getRepresentative().equals(order.getSection().getRepresentative())) {
            throw new RepositoryException
                    ("Representative not exists in database, please contact the administrator");
        }
    }

    public Section verifySection( InboundOrder orderDTO ){
        return sectionDAO.findById(orderDTO.getSection().getId())
                .orElseThrow(() -> new RepositoryException
                        ("Section not exists in database, please contact the administrator"));
    }

    public void verifyBatchOrder( Section section, InboundOrder order ){
        if (!section.getWarehouse().getId().equals(order.getSection().getWarehouse().getId())) {
            throw new RepositoryException
                    ("WareHouse not exists in database, please contact the administrator");
        }
    }

    public void verifyBatchType(Section section, Batch batch) {
        if (!section.getProductType().getType().equals(batch.getAdvertising().getProduct().getProductType().getType())) {
            throw new RepositoryException
                    ("Types not compatible, please contact the administrator");
        }
    }

    public void verifyVolume(Section section, Batch batch) {
        if (section.calVolume(batch) < 0) {
            throw new RepositoryException
                    ("Space not available in the section, please contact an administrator");
        }
    }

    public InboundOrder saveOrder(InboundOrderDTO order){

        InboundOrder orderBuild = InboundOrder
                .builder()
                .creationDate(order.getCreationDate())
                .batchs(convertListBatchDTO(order.getBatchs()))
                .section(convertSectionDTO(order.getSection()))
                .build();

       verifyProduct(orderBuild.getBatchs());
       verifyRequest(orderBuild);

        return orderDAO.save(orderBuild);
    }



    public Section convertSectionDTO(SectionDTO sectionDTO){
        Section section = sectionDAO.
                findById(sectionDTO.
                        getSectionCode()).
                orElseThrow(() -> new RepositoryException("Section not exists in the Database"));
        return section;
    }

    public Batch convertBatchDTO( BatchDTO batchDTO){
        return Batch
                .builder()
                .currentTemperature(batchDTO.getCurrentTemperature())
                .currentQuantity(batchDTO.getCurrentQuantity())
                .initialQuantity(batchDTO.getInitialQuantity())
                .expirationDate(batchDTO.getExpirationDate())
                .manufacturingDate(batchDTO.getManufacturingDate())
                .manufacturingTime(batchDTO.getManufacturingTime())
                .minimumTemperature(batchDTO.getMinimumTemperature())
                .advertising(advertisingDAO.findById(batchDTO.getAdvertsimentId())
                        .orElseThrow(() -> new RepositoryException("Advertise not exists in the Database")))
                .build();
    }

    public List<Batch> convertListBatchDTO( List<BatchDTO> batchDTO){
        return batchDTO.
                stream().
                map(this::convertBatchDTO).
                collect(Collectors.toList());
    }


}
