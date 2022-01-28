package com.mercadolibre.desafiofinalbootcampgrupo2.controller;


import com.mercadolibre.desafiofinalbootcampgrupo2.dao.ProductTypeDAO;
import com.mercadolibre.desafiofinalbootcampgrupo2.dto.AdvertisingDTO;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.Product;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.ProductType;
import com.mercadolibre.desafiofinalbootcampgrupo2.services.AdvertisingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1")
public class AdvertisingController {

    @Autowired
    private AdvertisingService service;


    @GetMapping(path = "/fresh-products/")
    public ResponseEntity<List<AdvertisingDTO>> getAllAdvertising(){
        List<AdvertisingDTO> advertisingList = service.findAll();

        if(advertisingList.size()< 0){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(advertisingList);
    }

    @GetMapping(path = "/fresh-products/list")
    public ResponseEntity<List<ProductTypeDAO.AdvertisingDTO>> ProducgetByIdAdvressing(@RequestParam String type){

        return ResponseEntity.ok().body(service.getByType(type));
    }
}
