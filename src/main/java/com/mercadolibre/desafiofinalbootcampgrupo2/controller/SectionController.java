package com.mercadolibre.desafiofinalbootcampgrupo2.controller;

import com.mercadolibre.desafiofinalbootcampgrupo2.dto.SectionCreateDTO;
import com.mercadolibre.desafiofinalbootcampgrupo2.dto.SectionInfoDTO;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.Section;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.Warehouse;
import com.mercadolibre.desafiofinalbootcampgrupo2.services.SectionService;
import com.mercadolibre.desafiofinalbootcampgrupo2.services.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping(path = "/section")
public class SectionController {

    @Autowired
    SectionService sectionService;

    @Autowired
    WarehouseService warehouseservice;

    @GetMapping(path = "/info-all-sections")
    public ResponseEntity<List<SectionInfoDTO>> findAll(){
        List<SectionInfoDTO> listSection = sectionService.findAll();
        return ResponseEntity.ok().body(listSection);
    }

    @PostMapping(path = "/add")
    public ResponseEntity<SectionInfoDTO> add(@Valid @RequestBody SectionCreateDTO section){
        Warehouse warehouse = warehouseservice.findById(section.getWarehouseId());
        try{
            SectionInfoDTO sec = sectionService.addSection(section);
            return ResponseEntity
                    .created(URI.create("/section/add" + warehouse.getId())).body(sec);
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        try {
            sectionService.deleteSection(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}
