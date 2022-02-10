package com.mercadolibre.desafiofinalbootcampgrupo2.dao;

import com.mercadolibre.desafiofinalbootcampgrupo2.model.ReturnOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface ReturnOrderDAO extends JpaRepository<ReturnOrder, Long> {

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM ReturnOrderItens p WHERE p.returnOrder = :return", nativeQuery = false)
    public void deleteAllByReturnOrder(@Param("return") ReturnOrder returnOrder);

}
