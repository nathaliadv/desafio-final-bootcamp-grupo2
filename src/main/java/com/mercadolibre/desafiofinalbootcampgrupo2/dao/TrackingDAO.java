package com.mercadolibre.desafiofinalbootcampgrupo2.dao;

import com.mercadolibre.desafiofinalbootcampgrupo2.model.Tracking;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackingDAO extends JpaRepository<Tracking, Long> {
}
