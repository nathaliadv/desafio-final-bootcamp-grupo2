package com.mercadolibre.desafiofinalbootcampgrupo2.controller;

import com.mercadolibre.desafiofinalbootcampgrupo2.dto.TrackingDTO;
import com.mercadolibre.desafiofinalbootcampgrupo2.dto.VehicleDTO;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.Tracking;
import com.mercadolibre.desafiofinalbootcampgrupo2.services.TrackingService;
import com.mercadolibre.desafiofinalbootcampgrupo2.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/tracking")
public class TrackingController {

    @Autowired
    private TrackingService trackingService;

    //Requiriment 06
    @GetMapping(path = "/detail/")
    public ResponseEntity<Tracking> getVehicleById(@RequestParam Long trackingId, Authentication authentication) {
        Tracking tracking = trackingService.findById(trackingId,authentication);
        return ResponseEntity.ok(tracking);
    }

    //Requiriment 06
    @PostMapping(path = "/create")
    public ResponseEntity<Tracking> getVehicleTrackingById(@RequestBody TrackingDTO trackingDTO,Authentication authentication) {
        Tracking tracking = trackingService.createTracking(trackingDTO,authentication);
        return ResponseEntity.ok(tracking);
    }
}
