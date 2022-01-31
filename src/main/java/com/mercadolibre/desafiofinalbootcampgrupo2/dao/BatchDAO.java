package com.mercadolibre.desafiofinalbootcampgrupo2.dao;

import com.mercadolibre.desafiofinalbootcampgrupo2.model.Batch;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.InboundOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface BatchDAO extends JpaRepository<Batch, Long> {

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM Batch b WHERE b.inboundOrder = :inboundOrder")
    void deleteAllByInboundOrder(@Param("inboundOrder") InboundOrder inboundOrder);
}