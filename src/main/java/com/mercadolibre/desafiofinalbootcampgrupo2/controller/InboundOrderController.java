package com.mercadolibre.desafiofinalbootcampgrupo2.controller;

import com.mercadolibre.desafiofinalbootcampgrupo2.dao.InboundOrderDAO;
import com.mercadolibre.desafiofinalbootcampgrupo2.dto.InboundOrderDTO;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.Batch;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.InboundOrder;
import com.mercadolibre.desafiofinalbootcampgrupo2.services.InboundOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequestMapping("/api/v1/")
@RestController
public class InboundOrderController {

    @Autowired
    private InboundOrderService service;



    @PostMapping("fresh-products/inboundorder/")
    public ResponseEntity<List<Batch>> saveInboundOrder(@RequestBody InboundOrderDTO order) {
        InboundOrder inboundOrder = service.saveCreateOrder(order);
        return ResponseEntity.created(URI.create("fresh-products/inboundorder/" + inboundOrder.getId())).body(inboundOrder.getBatchs());
    }

    @PutMapping("fresh-products/inboundorder/{id}")
    public ResponseEntity<List<Batch>> updateInboundOrder(@RequestBody InboundOrderDTO order, @RequestParam Long id) {
        InboundOrder inboundOrder = service.updateOrder(order, id);
        return ResponseEntity.created(URI.create("fresh-products/inboundorder/" + inboundOrder.getId())).body(inboundOrder.getBatchs());
    }

}
