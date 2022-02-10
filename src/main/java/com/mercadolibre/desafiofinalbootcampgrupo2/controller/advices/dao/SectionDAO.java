package com.mercadolibre.desafiofinalbootcampgrupo2.controller.advices.dao;

import com.mercadolibre.desafiofinalbootcampgrupo2.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectionDAO extends JpaRepository<Section, Long> {
}
