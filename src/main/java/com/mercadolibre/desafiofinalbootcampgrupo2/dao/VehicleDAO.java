package com.mercadolibre.desafiofinalbootcampgrupo2.dao;

import com.mercadolibre.desafiofinalbootcampgrupo2.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VehicleDAO extends JpaRepository<Vehicle, Long> {

    Vehicle findBylicensePlate(String licensePlate);

    List<Vehicle> findByVehicleModel(String vehicleModel);
}
