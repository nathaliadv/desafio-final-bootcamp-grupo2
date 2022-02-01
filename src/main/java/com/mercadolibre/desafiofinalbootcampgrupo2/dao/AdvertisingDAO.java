package com.mercadolibre.desafiofinalbootcampgrupo2.dao;


import com.mercadolibre.desafiofinalbootcampgrupo2.dto.AdvertisingDTO;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.Advertising;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface AdvertisingDAO extends JpaRepository<Advertising, Long> {

    @Query("Select new com.mercadolibre.desafiofinalbootcampgrupo2.dto.AdvertisingDTO(product.name, advertising.description, advertising.price, b.currentQuantity) "
            + " from Batch b "
            + " inner join b.advertising advertising"
            + " inner join advertising.product product "
            + " inner join product.productType productType")
    public List<AdvertisingDTO> findAllInStock();

}
