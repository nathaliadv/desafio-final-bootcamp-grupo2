package com.mercadolibre.desafiofinalbootcampgrupo2.controller;

import com.mercadolibre.desafiofinalbootcampgrupo2.dto.ReturnOrderCreateDTO;
import com.mercadolibre.desafiofinalbootcampgrupo2.dto.ReturnOrderDTO;
import com.mercadolibre.desafiofinalbootcampgrupo2.dto.ReturnOrderResponseDTO;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.ReturnOrder;
import com.mercadolibre.desafiofinalbootcampgrupo2.services.ReturnOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(path = "/fresh-products/returnorders")
public class ReturnOrderController {

    @Autowired
    ReturnOrderService returnOrderService;

    @PostMapping(path = "/")
    public ResponseEntity<ReturnOrderResponseDTO> createReturnOrder(@Valid @RequestBody ReturnOrderCreateDTO returnOrderCreateDTO, Authentication authentication) {
        ReturnOrder returnOrder = returnOrderService.saveReturnOrder(returnOrderCreateDTO.getItens(), returnOrderCreateDTO.getReturnCause(), authentication);

        return ResponseEntity
                .created(URI.create("/fresh-products/orders/id/" + returnOrder.getId())).body(returnOrderService.getReturnOrderById(returnOrder.getId()));
    }

    @GetMapping(path = "/")
    public ResponseEntity<ReturnOrderResponseDTO> getReturnOrderById(@RequestParam Long returnOrderId) {
        return ResponseEntity.ok().body(returnOrderService.getReturnOrderById(returnOrderId));
    }
}