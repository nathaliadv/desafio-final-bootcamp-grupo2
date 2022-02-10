package com.mercadolibre.desafiofinalbootcampgrupo2.controller.advices.dao;

import com.mercadolibre.desafiofinalbootcampgrupo2.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerDAO extends JpaRepository<Seller, Long> {
}