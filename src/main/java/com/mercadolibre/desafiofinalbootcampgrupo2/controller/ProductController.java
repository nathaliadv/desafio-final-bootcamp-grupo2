package com.mercadolibre.desafiofinalbootcampgrupo2.controller;

import com.mercadolibre.desafiofinalbootcampgrupo2.dto.ProductInAllWarehouseDTO;
import com.mercadolibre.desafiofinalbootcampgrupo2.dto.ProductResponseDTO;
import com.mercadolibre.desafiofinalbootcampgrupo2.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/fresh-products")
public class ProductController {

    @Autowired
    private ProductService productService;

    //Requiriment 03
    @GetMapping(path = "/")
    public ResponseEntity<List<ProductResponseDTO>> getProductListByIdInWarehouse(@RequestParam Long productCode, @RequestParam Long warehouseCode, @RequestParam(defaultValue = "") String sortBy) {
        List<ProductResponseDTO> productResponseDTOList = productService.findProductListByIdInWarehouse(productCode, warehouseCode, sortBy);
        return ResponseEntity.ok(productResponseDTOList);
    }

    //Requiriment 04
    @GetMapping(path = "/warehouse/")
    public ResponseEntity<List<ProductInAllWarehouseDTO>> getProductListById(@RequestParam Long productCode) {
        List<ProductInAllWarehouseDTO> productInAllWarehouseDTOs = productService.findProductListByID(productCode);
        return ResponseEntity.ok(productInAllWarehouseDTOs);
    }
}