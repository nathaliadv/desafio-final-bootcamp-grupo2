package com.mercadolibre.desafiofinalbootcampgrupo2.controller;

import com.mercadolibre.desafiofinalbootcampgrupo2.dto.ProductResponseDTO;
import com.mercadolibre.desafiofinalbootcampgrupo2.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/fresh-products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<List<ProductResponseDTO>> getProductListById(@PathVariable Long id, @PathVariable String param) {
        List<ProductResponseDTO> productResponseDTOList = productService.findProductListByID(id,param);
        return ResponseEntity.ok(productResponseDTOList);
    }
}
