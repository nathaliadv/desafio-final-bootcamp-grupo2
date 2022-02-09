package com.mercadolibre.desafiofinalbootcampgrupo2.controller;

import com.mercadolibre.desafiofinalbootcampgrupo2.dto.*;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.PurchaseOrder;
import com.mercadolibre.desafiofinalbootcampgrupo2.services.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/fresh-products/orders")
public class PurchaseOrderController {

    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @PostMapping(path = "/")
    public ResponseEntity<TotalDTO> createPurchaseOrder(@Valid @RequestBody PurchaseOrderCreateDTO purchaseOrderCreate, Authentication authentication) {
        PurchaseOrder purchaseOrder = purchaseOrderService.savePurchaseOrder(purchaseOrderCreate.getProducts(), authentication);
        TotalDTO totalPrice = purchaseOrderService.getTotalPriceByPurchaseOrder(purchaseOrder);

        return ResponseEntity
                .created(URI.create("/fresh-products/orders/id/" + purchaseOrder.getId()))
                .body(totalPrice);
    }

    @GetMapping(path = "/")
    public ResponseEntity<PurchaseOrderDTO> getProductsPurchaseOrder(@RequestParam Long purchaseOrderId, Authentication authentication) {
        return ResponseEntity.ok().body(purchaseOrderService.getProductsByPurchaseId(purchaseOrderId, authentication));
    }

    @PutMapping(path = "/")
    public ResponseEntity<PurchaseOrderDTO> updatePurchaseOrder(@RequestParam Long purchaseOrderId, @Valid @RequestBody PurchaseOrderUpdateDTO purchaseOrderUpdateDto, Authentication authentication) {
        return ResponseEntity.ok().body(purchaseOrderService.updatePurchaseOrder(purchaseOrderId, purchaseOrderUpdateDto, authentication));
    }
}