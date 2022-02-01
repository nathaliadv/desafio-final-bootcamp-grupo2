package com.mercadolibre.desafiofinalbootcampgrupo2.controller;

import com.mercadolibre.desafiofinalbootcampgrupo2.dto.ProductDTO;
import com.mercadolibre.desafiofinalbootcampgrupo2.dto.PurchaseOrderDTO;
import com.mercadolibre.desafiofinalbootcampgrupo2.dto.TotalDTO;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.PurchaseOrder;
import com.mercadolibre.desafiofinalbootcampgrupo2.services.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

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

    @GetMapping(path = "/fresh-products/orders/")
    public ResponseEntity<PurchaseOrderDTO> getProductsPurchaseOrder(@RequestParam Long purchaseOrderId){
        return ResponseEntity.ok().body(purchaseOrderService.getProductsByPurchaseId(purchaseOrderId));
    }

    //TODO - nao atualiza a tempo o retorno
    @PutMapping(path = "/fresh-products/orders/")
    public ResponseEntity<PurchaseOrderDTO> updatePurchaseOrder(@RequestParam Long purchaseOrderId, @RequestBody PurchaseOrderDTO purchaseOrderDto){
        return ResponseEntity.ok().body(purchaseOrderService.updatePurchaseOrder(purchaseOrderId,purchaseOrderDto));
    }

}
