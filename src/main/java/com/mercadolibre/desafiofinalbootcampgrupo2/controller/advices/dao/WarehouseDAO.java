package com.mercadolibre.desafiofinalbootcampgrupo2.controller.advices.dao;

import com.mercadolibre.desafiofinalbootcampgrupo2.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseDAO extends JpaRepository<Warehouse, Long> {
}
