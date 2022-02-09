package com.mercadolibre.desafiofinalbootcampgrupo2.controller;

import com.mercadolibre.desafiofinalbootcampgrupo2.dto.VehicleDTO;
import com.mercadolibre.desafiofinalbootcampgrupo2.dto.VehicleUpdateDTO;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.Representative;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.Vehicle;
import com.mercadolibre.desafiofinalbootcampgrupo2.services.RepresentativeService;
import com.mercadolibre.desafiofinalbootcampgrupo2.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/fleet")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;



    //Requiriment 06
    @GetMapping(path = "/detail/")
    public ResponseEntity<VehicleDTO> getVehicleById(@RequestParam Long vehicleId,Authentication authentication) {
        VehicleDTO vehicleDTO = vehicleService.findVehiclebyId(vehicleId, authentication);
        return ResponseEntity.ok(vehicleDTO);
    }

    //Requiriment 06
    @GetMapping(path = "/list")
    public ResponseEntity<List<VehicleDTO>> getVehicleList( Authentication authentication ) {
        List<VehicleDTO> listVehicleDTO = vehicleService.findAllVehicles( authentication );
        return ResponseEntity.ok(listVehicleDTO);
    }



    //Requiriment 06
    @DeleteMapping(path = "/delete/")
    public ResponseEntity<HttpStatus> deleteVehicleById(@RequestParam Long vehicleId, Authentication authentication) {
        vehicleService.deleteVehiclebyId(vehicleId, authentication);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

    //Requiriment 06
    @PostMapping(path = "/create")
    public ResponseEntity<VehicleDTO> createVehicle(@RequestBody VehicleDTO vehicleDTO,Authentication authentication) {
        Vehicle vehicle = vehicleService.createVehicle(vehicleDTO,authentication);

        return ResponseEntity
                .created(URI.create("/fleet/detail/" + vehicle.getId()))
                .body(vehicleDTO);
    }

    //Requiriment 06
    @PutMapping(path = "/update")
    public ResponseEntity<VehicleDTO> updateVehicle(@RequestBody VehicleUpdateDTO vehicleUpdateDTO,Authentication authentication) {
        Vehicle vehicle = vehicleService.updateVehicle(vehicleUpdateDTO,authentication);

        return ResponseEntity
                .created(URI.create("/fleet/detail/" + vehicle.getId()))
                .body(vehicleService.convertToVehicleDTO(vehicle));
    }
}
