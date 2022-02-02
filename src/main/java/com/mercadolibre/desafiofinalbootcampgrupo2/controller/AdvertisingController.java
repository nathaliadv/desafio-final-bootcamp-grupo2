package com.mercadolibre.desafiofinalbootcampgrupo2.controller;


import com.mercadolibre.desafiofinalbootcampgrupo2.dto.AdvertisingDTO;
import com.mercadolibre.desafiofinalbootcampgrupo2.services.AdvertisingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/fresh-products")
public class AdvertisingController {

    @Autowired
    private AdvertisingService service;


    @GetMapping(path = "/in-stock")
    public ResponseEntity<List<AdvertisingDTO>> getAllAdvertising() {
        List<AdvertisingDTO> advertisingList = service.findAllInStock();

        if (advertisingList.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(advertisingList);
    }

    @GetMapping(path = "/in-stock/by-type")
    public ResponseEntity<List<AdvertisingDTO>> getProducByType(@RequestParam String type) {
        List<AdvertisingDTO> products = service.getByType(type);

        if (products.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(products);
    }
}
