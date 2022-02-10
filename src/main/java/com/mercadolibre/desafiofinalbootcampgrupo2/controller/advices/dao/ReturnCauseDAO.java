package com.mercadolibre.desafiofinalbootcampgrupo2.controller.advices.dao;

import com.mercadolibre.desafiofinalbootcampgrupo2.model.ReturnCause;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReturnCauseDAO extends JpaRepository<ReturnCause, Long> {

    public ReturnCause findByCause(String cause);

}
