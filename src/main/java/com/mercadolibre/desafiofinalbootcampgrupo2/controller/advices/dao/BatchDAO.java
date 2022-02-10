package com.mercadolibre.desafiofinalbootcampgrupo2.controller.advices.dao;

import com.mercadolibre.desafiofinalbootcampgrupo2.dto.BatchDueDateDTO;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.Batch;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.InboundOrder;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BatchDAO extends JpaRepository<Batch, Long> {

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM Batch b WHERE b.inboundOrder = :inboundOrder", nativeQuery = false)
    void deleteAllByInboundOrder(@Param("inboundOrder") InboundOrder inboundOrder);


    @Query(value = "SELECT " +
            "new com.mercadolibre.desafiofinalbootcampgrupo2.dto.BatchDueDateDTO(" +
            "   bt.id, pd.id, pd.productType.id, bt.expirationDate, bt.currentQuantity) " +
            "FROM Product pd " +
            "INNER JOIN Advertising ad ON ad.product = pd " +
            "INNER JOIN Batch bt ON bt.advertising = ad " +
            "INNER JOIN InboundOrder ib ON ib = bt.inboundOrder " +
            "INNER JOIN Section sec ON sec = ib.section " +
            "WHERE sec.id = :sectionId " +
            "AND sec.warehouse.id = :warehouseId " +
            "AND bt.expirationDate < :date "
    )
    List<BatchDueDateDTO> findByDueDate(@Param("sectionId") Long sectionId, @Param("warehouseId") Long warehouseId, @Param("date") LocalDate date);

    @Query(value = "SELECT " +
            "new com.mercadolibre.desafiofinalbootcampgrupo2.dto.BatchDueDateDTO(" +
            "   bt.id, pd.id, pd.productType.id, bt.expirationDate, bt.currentQuantity) " +
            "FROM Product pd " +
            "INNER JOIN Advertising ad ON ad.product = pd " +
            "INNER JOIN Batch bt ON bt.advertising = ad " +
            "INNER JOIN InboundOrder ib ON ib = bt.inboundOrder " +
            "INNER JOIN Section sec ON sec = ib.section " +
            "WHERE sec.productType.id = :productTypeId " +
            "AND bt.expirationDate < :date " +
            "ORDER BY :direction "
    )
    List<BatchDueDateDTO> findByDueDateByCat(@Param("productTypeId") Long productTypeId, @Param("date") LocalDate date, Optional<Sort.Direction> direction);
}








