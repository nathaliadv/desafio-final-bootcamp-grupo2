package com.mercadolibre.desafiofinalbootcampgrupo2.services;

import com.mercadolibre.desafiofinalbootcampgrupo2.dao.VehicleDAO;
import com.mercadolibre.desafiofinalbootcampgrupo2.dto.VehicleDTO;
import com.mercadolibre.desafiofinalbootcampgrupo2.dto.VehicleUpdateDTO;
import com.mercadolibre.desafiofinalbootcampgrupo2.exception.VehicleException;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.Buyer;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.User;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleService {

    @Autowired
    VehicleDAO vehicleDAO;

    @Autowired
    WarehouseService warehouseService;

    @Autowired
    RepresentativeService representativeService;

    public VehicleDTO findVehiclebyId(Long id,Authentication authentication ){
        representativeService.verifyIfRepresentativeIsValid(authentication);
        return convertToVehicleDTO(vehicleDAO.findById(id)
                .orElseThrow(() -> new VehicleException("Vehicle not not exists in database, please contact the Representative")));
    }

    public VehicleDTO findVehiclebyId(Long id ){
        return convertToVehicleDTO(vehicleDAO.findById(id)
                .orElseThrow(() -> new VehicleException("Vehicle not not exists in database, please contact the Representative")));
    }

    public List<VehicleDTO> findAllVehicles( Authentication authentication){
        representativeService.verifyIfRepresentativeIsValid(authentication);
        List<VehicleDTO> list =  convertToVehicleDTO(vehicleDAO.findAll());

        if(list.size() == 0)
        throw new VehicleException("Vehicle not not exists in database, please contact the Representative");

        return list;
    }

    public void deleteVehiclebyId(long id,Authentication authentication ){
        representativeService.verifyIfRepresentativeIsValid(authentication);
        vehicleDAO.deleteById(id);
    }

    public Vehicle updateVehicle(VehicleUpdateDTO vehicleUpdateDTO,Authentication authentication){
        representativeService.verifyIfRepresentativeIsValid(authentication);
        return vehicleDAO.save(convertToVehicle(vehicleUpdateDTO));
    }

    public Vehicle createVehicle(VehicleDTO vehicleDTO,Authentication authentication ){
        representativeService.verifyIfRepresentativeIsValid(authentication);
        return vehicleDAO.save(convertToVehicle(vehicleDTO));
    }

    public VehicleDTO convertToVehicleDTO(Vehicle vehicle){
        return VehicleDTO.builder().
                licensePlate(vehicle.getLicensePlate()).
                vehicleModel(vehicle.getVehicleModel()).
                maintenanceDate(vehicle.getMaintenanceDate()).
                mileage(vehicle.getMileage()).
                warehouseId(vehicle.getWarehouse().getId()).
                representativeId(vehicle.getRepresentative().getId()).
                build();
    }

    public Vehicle convertToVehicle(VehicleDTO vehicleDTO){
        return Vehicle.builder().
                licensePlate(vehicleDTO.getLicensePlate()).
                vehicleModel(vehicleDTO.getVehicleModel()).
                maintenanceDate(vehicleDTO.getMaintenanceDate()).
                mileage(vehicleDTO.getMileage()).
                warehouse(warehouseService.findById(vehicleDTO.getWarehouseId())).
                representative(representativeService.findById(vehicleDTO.getRepresentativeId())).
                build();
    }

    public Vehicle convertToVehicle(VehicleUpdateDTO vehicleUpdateDTO){
        Vehicle vehicle = vehicleDAO.findById(vehicleUpdateDTO.getId())
                .orElseThrow(() -> new VehicleException("Vehicle not not exists in database, please contact the Representative"));

        vehicle.setLicensePlate(vehicleUpdateDTO.getLicensePlate());
        vehicle.setMaintenanceDate(vehicleUpdateDTO.getMaintenanceDate());
        vehicle.setWarehouse(warehouseService.findById(vehicleUpdateDTO.getWarehouseId()));
        vehicle.setMileage(vehicleUpdateDTO.getMileage());

        return vehicleDAO.save(vehicle);
    }

    public List<VehicleDTO> convertToVehicleDTO(List<Vehicle> vehicle){
        List<VehicleDTO> list = new ArrayList<>();

        vehicle.stream().
                forEach(vehicle1 -> list.add(convertToVehicleDTO(vehicle1)));

        return list;
    }
}
