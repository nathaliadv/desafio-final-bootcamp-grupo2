package com.mercadolibre.desafiofinalbootcampgrupo2.services;

import com.mercadolibre.desafiofinalbootcampgrupo2.dao.*;
import com.mercadolibre.desafiofinalbootcampgrupo2.dto.InboundOrderDTO;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.Batch;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.InboundOrder;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.Section;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InboundOrderService {

    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private SectionDAO sectionDAO;


    @Autowired
    private InboundOrderDAO orderDAO;

    public void verifyProduct(List<Batch> batchList) {

        batchList.stream().forEach(batch -> productDAO
                .findById(batch.getAdvertising()
                        .getProduct()
                        .getId())
                .orElseThrow(() -> new RuntimeException())
        );

    }

    public void verifyRequest(InboundOrderDTO orderDTO) {

        Section section = sectionDAO.findById(orderDTO.getSection().getId()).orElseThrow(() -> new RuntimeException());

        var representativeExist = section.getRepresentative().equals(orderDTO.getRepresentative());

        if (!representativeExist) {
            throw new RuntimeException();
        }

        var wareHouseExist = section.getWarehouse().equals(orderDTO.getSection().getWarehouse());

        if (!wareHouseExist) {
            throw new RuntimeException();
        }

        orderDTO.getBatchs().stream().forEach(batch -> verifyBatch(section, batch));
    }

    public void verifyBatch(Section section, Batch batch) {

        if (!section.getTypeSection().equals(batch.getAdvertising().getProduct().getProductTypes())) {
            throw new RuntimeException();
        }
    }

    public void verifyVolume(Section section, Batch batch) {

        if (section.calVolume(batch) > 0) {
            throw new RuntimeException();
        }
    }

    public InboundOrder saveOrder(InboundOrderDTO order){

       verifyProduct(order.getBatchs());
       verifyRequest(order);

        InboundOrder orderBuild = InboundOrder
                .builder()
                .creationDate(order.getCreationDate())
                .batchs(order.getBatchs())
                .section(order.getSection())
                .build();

        return orderDAO.save(orderBuild);
    }
}
