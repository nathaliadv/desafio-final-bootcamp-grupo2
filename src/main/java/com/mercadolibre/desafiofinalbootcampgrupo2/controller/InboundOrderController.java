package com.mercadolibre.desafiofinalbootcampgrupo2.controller;

import com.mercadolibre.desafiofinalbootcampgrupo2.dto.BatchResponseDTO;
import com.mercadolibre.desafiofinalbootcampgrupo2.dto.InboundOrderDTO;
import com.mercadolibre.desafiofinalbootcampgrupo2.services.InboundOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping(path = "fresh-products/inboundorder/")
@RestController
public class InboundOrderController {

    @Autowired
    private InboundOrderService service;

    @PostMapping(path = "")
    public ResponseEntity<List<BatchResponseDTO>> inboundOrder(@RequestBody InboundOrderDTO orderDTO) {
        List<BatchResponseDTO> inboundOrder = service.saveInboundOrder(orderDTO);
        return ResponseEntity.created(null).body(inboundOrder);
    }
}