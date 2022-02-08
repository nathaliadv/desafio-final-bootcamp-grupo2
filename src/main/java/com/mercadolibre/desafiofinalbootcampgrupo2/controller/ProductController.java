package com.mercadolibre.desafiofinalbootcampgrupo2.controller;

import com.mercadolibre.desafiofinalbootcampgrupo2.dto.*;
import com.mercadolibre.desafiofinalbootcampgrupo2.services.BatchService;
import com.mercadolibre.desafiofinalbootcampgrupo2.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/fresh-products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private BatchService batchService;

    //Requiriment 03
    @GetMapping(path = "/")
    public ResponseEntity<ProductByWarehouseDTO> getProductListByIdInWarehouse(@Valid  @RequestParam Long productCode, @RequestParam Long warehouseCode, @RequestParam(defaultValue = "") String sortBy) {
        ProductByWarehouseDTO productResponseDTOList = productService.findProductListByIdInWarehouse(productCode, warehouseCode, sortBy);
        return ResponseEntity.ok(productResponseDTOList);
    }

    //Requiriment 04
    @GetMapping(path = "/warehouse/")
    public ResponseEntity<List<ProductInAllWarehouseDTO>> getProductListById(@RequestParam Long productCode) {
        List<ProductInAllWarehouseDTO> productInAllWarehouseDTOs = productService.findProductListByID(productCode);
        return ResponseEntity.ok(productInAllWarehouseDTOs);
    }

    @GetMapping(path = "/due-date")
    public ResponseEntity<List<BatchDueDateDTO>> getProductsByDueDate(@RequestParam Long sectionId, @RequestParam Long warehouseId, @RequestParam Long numberDays) {
        return ResponseEntity.ok().body(batchService.findByDueDate(sectionId, warehouseId, numberDays));
    }

    @GetMapping(path = "/due-date/list")
    public ResponseEntity<List<BatchDueDateDTO>> getProductsByDueDate(@RequestParam Long productTypeId, @RequestParam Long numberDays, @RequestParam  String orderBy) {
        return ResponseEntity.ok().body(batchService.findByDueDateByCat(productTypeId, numberDays, orderBy));
    }
}
