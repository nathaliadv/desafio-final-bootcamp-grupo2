package com.mercadolibre.desafiofinalbootcampgrupo2.dao;

import com.mercadolibre.desafiofinalbootcampgrupo2.model.Representative;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepresentativeDAO extends JpaRepository<Representative, Long> {
}
