package com.mercadolibre.desafiofinalbootcampgrupo2.controller;

import com.mercadolibre.desafiofinalbootcampgrupo2.dto.ProductResponseDTO;
import com.mercadolibre.desafiofinalbootcampgrupo2.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/fresh-products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<List<ProductResponseDTO>> getProductListById(@PathVariable Long id, @RequestParam(defaultValue = "") String sortBy) {
        List<ProductResponseDTO> productResponseDTOList = productService.findProductListByID(id, sortBy);
        return ResponseEntity.ok(productResponseDTOList);
    }
}