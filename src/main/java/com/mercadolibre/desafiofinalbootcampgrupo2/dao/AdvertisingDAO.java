package com.mercadolibre.desafiofinalbootcampgrupo2.dao;


import com.mercadolibre.desafiofinalbootcampgrupo2.model.Advertising;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface AdvertisingDAO extends JpaRepository<Advertising, Long> {

    @Query(nativeQuery = true, value =
            " Select p.name, ad.description, ad.price, sum(b.current_quantity) as quantity "
                    + " from batch b "
                    + " inner join advertising ad on b.advertising_id = ad.id "
                    + " inner join product p on p.id = ad.product_id "
                    + " inner join product_type pt on pt.id = p.product_type_id "
                    + " group by p.name, ad.description, ad.price"

    )
    List<AdvertisingDTO> findAllInStock();
    interface AdvertisingDTO {
        String getName();
        String getDescription();
        BigDecimal getPrice();
        Integer getQuantity();
    }

}
