package com.mercadolibre.desafiofinalbootcampgrupo2.dao;

import com.mercadolibre.desafiofinalbootcampgrupo2.model.ReturnStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReturnStatusDAO extends JpaRepository<ReturnStatus, Long> {

    public ReturnStatus findByStatusCode(String statusCode);
}
