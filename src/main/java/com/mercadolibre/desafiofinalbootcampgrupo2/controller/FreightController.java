package com.mercadolibre.desafiofinalbootcampgrupo2.controller;

import com.mercadolibre.desafiofinalbootcampgrupo2.dto.FreightCostDTO;
import com.mercadolibre.desafiofinalbootcampgrupo2.dto.FreightCostResponseDTO;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.Freight;
import com.mercadolibre.desafiofinalbootcampgrupo2.services.FreightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(path = "/freight")
public class FreightController {

    @Autowired
    private FreightService freightService;

    @GetMapping(path = "/cost/{state}")
    public ResponseEntity<FreightCostResponseDTO> getFreightCost(@PathVariable String state) {
        return ResponseEntity.ok().body(freightService.getCost(state));
    }

    @PostMapping(path = "/add")
    public ResponseEntity<Freight> addFreight(@RequestBody FreightCostDTO freightDTO) {
        Freight freight = freightService.saveFreight(freightDTO);
        return ResponseEntity.created(URI.create("/add/" + freight.getId())).body(freight);
    }

}
