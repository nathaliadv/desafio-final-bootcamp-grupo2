package com.mercadolibre.desafiofinalbootcampgrupo2.controller;

import com.mercadolibre.desafiofinalbootcampgrupo2.dto.PurchaseOrderDTO;
import com.mercadolibre.desafiofinalbootcampgrupo2.dto.TotalDTO;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.PurchaseOrder;
import com.mercadolibre.desafiofinalbootcampgrupo2.services.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(path = "/fresh-products/orders")
public class PurchaseOrderController {

    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @PostMapping(path = "/")
    public ResponseEntity<TotalDTO> createPurchaseOrder(@RequestBody PurchaseOrderDTO purchase) {
        PurchaseOrder purchaseOrder = purchaseOrderService.savePurchaseOrder(purchase);
        TotalDTO totalPrice = purchaseOrderService.getTotalPriceByPurchaseOrder(purchaseOrder);

        return ResponseEntity
                .created(URI.create("/fresh-products/orders/id/" + purchaseOrder.getId()))
                .body(totalPrice);
    }

    @GetMapping(path = "/")
    public ResponseEntity<PurchaseOrderDTO> getProductsPurchaseOrder(@RequestParam Long purchaseOrderId) {
        return ResponseEntity.ok().body(purchaseOrderService.getProductsByPurchaseId(purchaseOrderId));
    }

    @PutMapping(path = "/")
    public ResponseEntity<PurchaseOrderDTO> updatePurchaseOrder(@RequestParam Long purchaseOrderId, @RequestBody PurchaseOrderDTO purchaseOrderDto) {
        return ResponseEntity.ok().body(purchaseOrderService.updatePurchaseOrder(purchaseOrderId, purchaseOrderDto));
    }

}