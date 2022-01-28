package com.mercadolibre.desafiofinalbootcampgrupo2.dao;

import com.mercadolibre.desafiofinalbootcampgrupo2.model.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;


@Repository
public interface ProductTypeDAO extends JpaRepository<ProductType, Long> {

    public List<ProductType> findByType(String type);

    @Query(value = "Select p.name, ad.description, ad.price, b.current_quantity quantity "
           + " from product_type pt "
            + " inner join product p on p.product_type_id = pt.id "
            + " inner join advertising ad on p.product_type_id = ad.product_id "
           + " inner join batch b on b.advertising_id = ad.id"
            + " where pt.type = :type ",nativeQuery = true)
    List<AdvertisingDTO> advertisingList (@Param("type") String type);

    public interface AdvertisingDTO{
         String getName();
         String getDescription();
         BigDecimal getPrice();
         Integer getQuantity();
    }
}


