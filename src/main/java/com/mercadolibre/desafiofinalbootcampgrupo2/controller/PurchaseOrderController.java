package com.mercadolibre.desafiofinalbootcampgrupo2.controller;

import com.mercadolibre.desafiofinalbootcampgrupo2.dto.PurchaseOrderDTO;
import com.mercadolibre.desafiofinalbootcampgrupo2.dto.TotalDTO;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.PurchaseOrder;
import com.mercadolibre.desafiofinalbootcampgrupo2.services.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping(path = "/api/v1")
public class PurchaseOrderController {
    @Autowired
    private PurchaseOrderService purchaseOrderService;


    @PostMapping(path = "/fresh-products/orders/")
    public ResponseEntity<TotalDTO> createPurchaseOrder(@RequestBody PurchaseOrderDTO purchase) {
        PurchaseOrder purchaseOrder = purchaseOrderService.savePurchaseOrder(purchase);

        return ResponseEntity
                .created(URI.create("/fresh-products/orders/id/" + purchaseOrder.getId()))
                .body(purchaseOrderService.getTotalPurchaseOrder(purchase));
    }
}
