package com.mercadolibre.desafiofinalbootcampgrupo2.dao;


import com.mercadolibre.desafiofinalbootcampgrupo2.dto.AdvertisingResponseDTO;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.Advertising;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdvertisingDAO extends JpaRepository<Advertising, Long> {

    @Query(value = " SELECT " +
            " new com.mercadolibre.desafiofinalbootcampgrupo2.dto.AdvertisingResponseDTO(" +
            " ad.id, p.name, p.productType.type, ad.description, ad.price, ad.freeShipping, b.currentQuantity" +
            ")"
            + " FROM Batch b "
            + " INNER JOIN Advertising ad on b.advertising = ad"
            + " INNER JOIN Product p on ad.product = p"
            + " INNER JOIN ProductType pt on p.productType = pt "
    )
    List<AdvertisingResponseDTO> findAllInStock();

    @Query(value = " SELECT " +
            " new com.mercadolibre.desafiofinalbootcampgrupo2.dto.AdvertisingResponseDTO(" +
            " ad.id, p.name, p.productType.type, ad.description, ad.price, ad.freeShipping, b.currentQuantity" +
            ")"
            + " FROM Batch b "
            + " INNER JOIN Advertising ad on b.advertising = ad"
            + " INNER JOIN Product p on ad.product = p"
            + " INNER JOIN ProductType pt on p.productType = pt "
            + " WHERE pt.type = :type "
    )
    List<AdvertisingResponseDTO> findAllByProductProductType(@Param("type") String type);

    List<Advertising> findAllByFreeShipping(boolean isFreeShipping);
}
