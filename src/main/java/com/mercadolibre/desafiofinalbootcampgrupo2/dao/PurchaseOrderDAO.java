package com.mercadolibre.desafiofinalbootcampgrupo2.dao;

import com.mercadolibre.desafiofinalbootcampgrupo2.model.PurchaseOrder;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.PurchaseStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface PurchaseOrderDAO extends JpaRepository<PurchaseOrder, Long> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE purchase_order SET purchase_status_id = :status WHERE id = :id ", nativeQuery = true)
    public void updatePurchaseStatus(@Param("status") Long idStatus, @Param("id") Long idPurchase);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM PurchaseItens p WHERE p.purchaseOrder = :purchase", nativeQuery = false)
    public void deleteAllByPurchaseOrder(@Param("purchase") PurchaseOrder purchaseOrder);
}
