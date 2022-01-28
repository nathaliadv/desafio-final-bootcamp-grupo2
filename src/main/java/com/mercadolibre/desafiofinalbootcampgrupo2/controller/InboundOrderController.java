package com.mercadolibre.desafiofinalbootcampgrupo2.controller;

import com.mercadolibre.desafiofinalbootcampgrupo2.dto.InboundOrderDTO;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.Batch;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.InboundOrder;
import com.mercadolibre.desafiofinalbootcampgrupo2.services.InboundOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RequestMapping("/api/v1/")
@RestController
public class InboundOrderController {

    @Autowired
    private InboundOrderService service;



    @PostMapping("fresh-products/inboundorder/")
    public ResponseEntity<List<Batch>> inboundOrder(@RequestBody InboundOrderDTO order) {
        InboundOrder inboundOrder = service.saveOrder(order);
        return ResponseEntity.created(URI.create("fresh-products/inboundorder/" + inboundOrder.getId())).body(inboundOrder.getBatchs());
    }
}
