package com.mercadolibre.desafiofinalbootcampgrupo2.controller;

import com.mercadolibre.desafiofinalbootcampgrupo2.dto.BatchResponseDTO;
import com.mercadolibre.desafiofinalbootcampgrupo2.dto.InboundOrderDTO;
import com.mercadolibre.desafiofinalbootcampgrupo2.services.InboundOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequestMapping(path = "/fresh-products/inboundorder")
@RestController
public class InboundOrderController {

    @Autowired
    private InboundOrderService service;

    @GetMapping(path = "/{id}")
    public ResponseEntity<InboundOrderDTO> getInboundOrderById(@PathVariable Long id) {
        InboundOrderDTO orderDTO = new InboundOrderDTO(service.findById(id));
        return ResponseEntity.ok(orderDTO);
    }

    @PostMapping(path = "/")
    public ResponseEntity<List<BatchResponseDTO>> registerInboundOrder(@RequestBody InboundOrderDTO orderDTO) {
        List<BatchResponseDTO> batchResponseDTO = service.saveInboundOrder(orderDTO);
        return ResponseEntity.created(URI.create("/fresh-products/inboundorder/" + batchResponseDTO.get(0).getInboundorderCode())).body(batchResponseDTO);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<List<BatchResponseDTO>> updateInboundOrder(@RequestBody InboundOrderDTO orderDTO, @PathVariable Long id) {
        List<BatchResponseDTO> batchResponseDTO = service.updateInboundOrder(orderDTO, id);
        return ResponseEntity.created(URI.create("/fresh-products/inboundorder/" + batchResponseDTO.get(0).getInboundorderCode())).body(batchResponseDTO);
    }
}