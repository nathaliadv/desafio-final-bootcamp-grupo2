package com.mercadolibre.desafiofinalbootcampgrupo2.controller;


import com.mercadolibre.desafiofinalbootcampgrupo2.dto.AdvertisingDTO;
import com.mercadolibre.desafiofinalbootcampgrupo2.dto.AdvertisingResponseDTO;
import com.mercadolibre.desafiofinalbootcampgrupo2.services.AdvertisingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/fresh-products")
public class AdvertisingController {

    @Autowired
    private AdvertisingService service;

    @GetMapping(path = "/in-stock")
    public ResponseEntity<List<AdvertisingResponseDTO>> getAllAdvertisingInStock() {
        return ResponseEntity.ok().body(service.findAllInStock());
    }

    @GetMapping(path = "/in-stock/by-type")
    public ResponseEntity<List<AdvertisingResponseDTO>> getAllAdvertisingByProductType(@RequestParam String type) {
        return ResponseEntity.ok().body(service.findAllByType(type));
    }

    @GetMapping(path = "/advertisings/{id}")
    public ResponseEntity<AdvertisingResponseDTO> getAdvertisingById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findByIdDTO(id));
    }

    @GetMapping(path = "/advertisings")
    public ResponseEntity<List<AdvertisingResponseDTO>> getAllAdvertisingFreeShipping(@RequestParam boolean isFreeShipping) {
        return ResponseEntity.ok(service.findAllByFreeShipping(isFreeShipping));
    }

    @PostMapping(path = "/advertisings")
    public ResponseEntity<AdvertisingResponseDTO> registerAdvertising(@Valid @RequestBody AdvertisingDTO dto) {
        AdvertisingResponseDTO responseDTO = service.saveAdvertising(dto);
        return ResponseEntity.created(URI.create("/fresh-products/advertisings/" + responseDTO.getId())).body(responseDTO);
    }

    @PutMapping(path = "/advertisings/{id}")
    public ResponseEntity<AdvertisingResponseDTO> editAdvertising(@Valid @RequestBody AdvertisingDTO dto, @PathVariable Long id) {
        AdvertisingResponseDTO responseDTO = service.updateAdvertising(dto, id);
        return ResponseEntity.created(URI.create("/fresh-products/advertisings/" + responseDTO.getId())).body(responseDTO);
    }

    @DeleteMapping(path = "/advertisings/{id}")
    public void registerAdvertising(@PathVariable Long id) {
        service.deleteAdvertisingByID(id);
    }

}