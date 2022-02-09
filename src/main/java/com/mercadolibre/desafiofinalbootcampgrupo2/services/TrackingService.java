package com.mercadolibre.desafiofinalbootcampgrupo2.services;

import com.mercadolibre.desafiofinalbootcampgrupo2.dao.TrackingDAO;
import com.mercadolibre.desafiofinalbootcampgrupo2.dto.TrackingDTO;
import com.mercadolibre.desafiofinalbootcampgrupo2.exception.TrackingException;
import com.mercadolibre.desafiofinalbootcampgrupo2.exception.VehicleException;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.Buyer;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.Tracking;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TrackingService {

    @Autowired
    private TrackingDAO trackingDAO;

    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private UserService userService;

    public Tracking createTracking(TrackingDTO trackingDTO, Authentication authentication){
        verifyIfUserIsValid(authentication);
        validateVehicleIntegrity(trackingDTO.getVehicleId());
        return trackingDAO.save(convertTrackingDTO(trackingDTO));
    }

    public Tracking findById(Long id,Authentication authentication){
        verifyIfUserIsValid(authentication);
        return trackingDAO.findById(id)
                .orElseThrow(() -> new TrackingException("Vehicle not not exists in database, please contact the Representative"));
    }

    public Tracking convertTrackingDTO( TrackingDTO trackingDTO ){
        return Tracking.builder().
                observacao(trackingDTO.getObservacao()).
                purchaseOrder(purchaseOrderService.findById(trackingDTO.getPurchaseOrderId())).
                vehicle(vehicleService.convertToVehicle(vehicleService.findVehiclebyId(trackingDTO.getVehicleId()))).build();
    }

    public void validateVehicleIntegrity( Long id ){
        Vehicle vehicle = vehicleService.convertToVehicle(vehicleService.findVehiclebyId(id));
        if(vehicle.getMaintenanceDate().plusMonths(6).isBefore(LocalDate.now()))
            throw new VehicleException("This vehicle should not be running until the next maintenance, please contact the warehouse Representative");
    }

    public void verifyIfUserIsValid( Authentication authentication ){
        userService.findById(getUserId(authentication));
    }

    private Long getUserId(Authentication authentication) {
        return ((Buyer) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
    }
}
