package com.mercadolibre.desafiofinalbootcampgrupo2.dao;

import com.mercadolibre.desafiofinalbootcampgrupo2.dto.ProductResponseDTO;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDAO extends JpaRepository<Product, Long> {

    @Query(value = "SELECT pd.id as product_id, " +
            "pd.name as name_product, " +
            "sec.id as section_id," +
            "sec.name as name_section, " +
            "sec.warehouse_id, " +
            "bt.id as batch_id," +
            "bt.current_quantity," +
            "bt.expiration_date " +
            "FROM product pd " +
            "INNER JOIN advertising ad ON ad.product_id = pd.id " +
            "INNER JOIN batch bt ON bt.advertising_id = ad.id " +
            "INNER JOIN inbound_order ib ON ib.id = bt.inbound_order_id " +
            "INNER JOIN section sec ON sec.id = ib.section_id " +
            "WHERE pd.id = :id",nativeQuery = true)
    List<ProductResponseDTO> productList (@Param("id") Long id);
}
