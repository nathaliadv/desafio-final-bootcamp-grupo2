package com.mercadolibre.desafiofinalbootcampgrupo2.dao;

import com.mercadolibre.desafiofinalbootcampgrupo2.model.ReturnOrderItens;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReturnOrderItensDAO extends JpaRepository<ReturnOrderItens, Long> {

    public List<ReturnOrderItens> findByReturnOrderId(Long returnOrderId);
}
