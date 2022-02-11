package com.mercadolibre.desafiofinalbootcampgrupo2.controller;

import com.mercadolibre.desafiofinalbootcampgrupo2.dto.ReturnOrderCreateDTO;
import com.mercadolibre.desafiofinalbootcampgrupo2.dto.ReturnOrderResponseDTO;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.ReturnOrder;
import com.mercadolibre.desafiofinalbootcampgrupo2.services.ReturnOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(path = "/fresh-products/returnorders")
public class ReturnOrderController {

    @Autowired
    ReturnOrderService returnOrderService;

    @PostMapping(path = "/")
    public ResponseEntity<ReturnOrderResponseDTO> createReturnOrder(@Valid @RequestBody ReturnOrderCreateDTO returnOrderCreateDTO) {
        ReturnOrder returnOrder = returnOrderService.saveReturnOrder(returnOrderCreateDTO.getItens(), returnOrderCreateDTO.getReturnCause());
        return ResponseEntity
                .created(URI.create("/fresh-products/orders/id/" + returnOrder.getId())).body(returnOrderService.getReturnOrderById(returnOrder.getId()));
    }

    @GetMapping(path = "/")
    public ResponseEntity<ReturnOrderResponseDTO> getReturnOrderById(@RequestParam Long returnOrderId) {
        return ResponseEntity.ok().body(returnOrderService.getReturnOrderById(returnOrderId));
    }

    @PutMapping(path = "/")
    public ResponseEntity<ReturnOrderResponseDTO> updateReturnOrder(@RequestParam Long returnOrderId, @Valid @RequestBody ReturnOrderCreateDTO returnOrderCreateDTO){
        ReturnOrder returnOrder = returnOrderService.updateReturnOrder(returnOrderId, returnOrderCreateDTO);
        return ResponseEntity.ok().body(returnOrderService.getReturnOrderById(returnOrder.getId()));
    }

    @PutMapping(path = "/cancel/")
    public ResponseEntity<ReturnOrderResponseDTO> cancelReturnOrder(@RequestParam Long returnOrderId){
        ReturnOrder returnOrder = returnOrderService.cancelReturnOrder(returnOrderId);
        return ResponseEntity.ok().body(returnOrderService.getReturnOrderById(returnOrder.getId()));
    }

//TO DO - APROVAÇÃO DO RETURN ORDER PARA QUE O ITEM SEJA RETORNADO AO ESTOQUE E CREDITO SEJA DADO AO BUYER.
//    @PutMapping(path = "/")
//    public void approveReturnOrder(@RequestParam Long returnOrderId){
//
//    }

}