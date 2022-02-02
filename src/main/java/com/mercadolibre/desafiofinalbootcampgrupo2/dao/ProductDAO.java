package com.mercadolibre.desafiofinalbootcampgrupo2.dao;

import com.mercadolibre.desafiofinalbootcampgrupo2.dto.ProductInAllWarehouseDTO;
import com.mercadolibre.desafiofinalbootcampgrupo2.dto.ProductResponseDTO;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDAO extends JpaRepository<Product, Long> {

    @Query(value = "SELECT " +
            "new com.mercadolibre.desafiofinalbootcampgrupo2.dto.ProductInAllWarehouseDTO(" +
            "   prod.id, sec.warehouse.id, SUM(bat.currentQuantity)" +
            ") " +
            "FROM Product prod " +
            "INNER JOIN Advertising adv ON adv.product = prod " +
            "INNER JOIN Batch bat ON bat.advertising = adv " +
            "INNER JOIN InboundOrder inb ON inb = bat.inboundOrder " +
            "INNER JOIN Section sec ON sec = inb.section " +
            "WHERE prod.id = :id " +
            "GROUP BY sec.warehouse.id"
    )
    List<ProductInAllWarehouseDTO> findAllProductsById(@Param("id") Long productCode);

    @Query(value = "SELECT " +
            "new com.mercadolibre.desafiofinalbootcampgrupo2.dto.ProductResponseDTO(" +
            "   prod.id, prod.name, sec.id, sec.warehouse.id, bat.id, bat.currentQuantity, bat.expirationDate" +
            ") " +
            "FROM Product prod " +
            "INNER JOIN Advertising adv ON adv.product = prod " +
            "INNER JOIN Batch bat ON bat.advertising = adv " +
            "INNER JOIN InboundOrder inb ON inb = bat.inboundOrder " +
            "INNER JOIN Section sec ON sec = inb.section " +
            "WHERE prod.id = :productCode AND sec.warehouse.id = :warehouseCode"
    )
    List<ProductResponseDTO> findAllProductsByIdInWarehouse(@Param("productCode") Long productCode, @Param("warehouseCode") Long warehouseCode);
}