package com.mercadolibre.desafiofinalbootcampgrupo2.dao;

import com.mercadolibre.desafiofinalbootcampgrupo2.dto.AdvertisingDTO;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


//TOODO - findAll

@Repository
public interface ProductTypeDAO extends JpaRepository<ProductType, Long> {

    List<ProductType> findByType(String type);

    @Query(" Select new com.mercadolibre.desafiofinalbootcampgrupo2.dto.AdvertisingDTO(product.name, advertising.description, advertising.price, b.currentQuantity) "
            + " from Batch b "
            + " inner join b.advertising advertising"
            + " inner join advertising.product product "
            + " inner join product.productType productType"
            + " where productType.type = :type ")
    List<AdvertisingDTO> advertisingList (@Param("type") String type);

}






