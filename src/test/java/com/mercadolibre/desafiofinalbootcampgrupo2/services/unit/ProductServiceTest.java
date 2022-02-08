package com.mercadolibre.desafiofinalbootcampgrupo2.services.unit;

import com.mercadolibre.desafiofinalbootcampgrupo2.dto.ProductResponseDTO;
import com.mercadolibre.desafiofinalbootcampgrupo2.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.mercadolibre.desafiofinalbootcampgrupo2.utils.Factory.generateListProductResponseDTO;

@SpringBootTest
public class ProductServiceTest {

    ProductService pd;

    @Test
    public void shouldSortList( ){
        pd = new ProductService();
        List<ProductResponseDTO> list = generateListProductResponseDTO();

        pd.sortByAnyParam(list, "F");
        pd.sortByAnyParam(list, "C");
        pd.sortByAnyParam(list, "E");
        pd.sortByAnyParam(list, "");
    }
}
