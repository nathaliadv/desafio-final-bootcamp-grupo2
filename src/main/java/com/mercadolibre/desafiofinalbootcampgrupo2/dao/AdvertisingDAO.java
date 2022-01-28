package com.mercadolibre.desafiofinalbootcampgrupo2.dao;


import com.mercadolibre.desafiofinalbootcampgrupo2.model.Advertising;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvertisingDAO extends JpaRepository<Advertising, Long> {
}
