package com.mercadolibre.desafiofinalbootcampgrupo2.services;

import com.mercadolibre.desafiofinalbootcampgrupo2.controller.advices.dao.ProductDAO;
import com.mercadolibre.desafiofinalbootcampgrupo2.controller.advices.dao.WarehouseDAO;
import com.mercadolibre.desafiofinalbootcampgrupo2.dto.SectionDTO;
import com.mercadolibre.desafiofinalbootcampgrupo2.dto.BatchStockDTO;
import com.mercadolibre.desafiofinalbootcampgrupo2.dto.ProductByWarehouseDTO;
import com.mercadolibre.desafiofinalbootcampgrupo2.dto.ProductInAllWarehouseDTO;
import com.mercadolibre.desafiofinalbootcampgrupo2.dto.ProductResponseDTO;
import com.mercadolibre.desafiofinalbootcampgrupo2.exception.DontMatchesException;
import com.mercadolibre.desafiofinalbootcampgrupo2.exception.RepositoryException;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.Batch;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.Product;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.Section;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

 
@Service
public class ProductService implements EntityService<Product> {

    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private WarehouseDAO warehouseDAO;

    @Override
    public Product findById(Long id) {
        return productDAO.findById(id)
                .orElseThrow(() -> new RepositoryException("Product not exists in database, please contact the administrator"));
    }

    public List<ProductInAllWarehouseDTO> findProductListByID(Long id) {
        List<ProductInAllWarehouseDTO> lista = productDAO.findAllProductsById(id);
        if(lista.size() == 0)
            new RepositoryException("Product not exists in database, please contact the administrator");

        return lista;
    }

    public List<ProductResponseDTO> sortByAnyParam(List<ProductResponseDTO> listProduct, String filter) {
        filter = filter.toUpperCase();
        switch (filter) {
            case "L":
                listProduct.sort(Comparator.comparing(ProductResponseDTO::getBatchCode));
                break;
            case "C":
                listProduct.sort(Comparator.comparing(ProductResponseDTO::getCurrentQuantity));
                break;
            case "F":
                listProduct.sort(Comparator.comparing(ProductResponseDTO::getExpirationDate).reversed());
                break;
            default:
                break;
        }
        return listProduct;
    }

    public ProductByWarehouseDTO findProductListByIdInWarehouse(Long productCode, Long warehouseCode, String filter) {
        validateWarehouseCode(warehouseCode);
        validateProduct(productCode);
        List<ProductResponseDTO> listProducts=  sortByAnyParam(productDAO.findAllProductsByIdInWarehouse(productCode, warehouseCode), filter);
        if(listProducts.size() == 0){
            throw new RepositoryException("Product does not exist in Warehouse.");
        }
        return convertListProductResponseDTOInProductByWarehouseDTO(listProducts);
    }

    public void validateProduct( Long productId){
        productDAO.findById(productId)
                .orElseThrow(() -> new RepositoryException("Product not exists in database, please contact the administrator"));
    }

    public void validateWarehouseCode( Long warehouseCode){
        warehouseDAO.findById(warehouseCode)
                .orElseThrow(() -> new RepositoryException("Warehouse not exists in database, please contact the administrator"));
    }

    protected void verifyIfProductsAreTheSameTypeOfSection(List<Batch> batchs, Section section) {
        for (Batch batch : batchs) {
            Product product = findById(batch.getAdvertising().getProduct().getId());
            if (!product.getProductType().equals(section.getProductType()))
                throw new DontMatchesException("The mentioned Product don't matches with mentioned Section.");
        }
    }

    public ProductByWarehouseDTO convertListProductResponseDTOInProductByWarehouseDTO(List<ProductResponseDTO> productResponseList){

        return ProductByWarehouseDTO.builder()
                .productId(productResponseList.get(0).getProductCode())
                .productName(productResponseList.get(0).getProductName())
                .listBachsStockDto(productResponseList.stream().map(
                        productResponseDTO -> BatchStockDTO.builder()
                                .batchCode(productResponseDTO.getBatchCode())
                                .currentQuantity(productResponseDTO.getCurrentQuantity())
                                .expirationDate(productResponseDTO.getExpirationDate()).build()).collect(Collectors.toList()))
                .sectionDto(SectionDTO.builder()
                        .sectionCode(productResponseList.get(0).getSectionCode())
                        .warehouseCode(productResponseList.get(0).getWarehouseCode())
                        .build())
                .build();
    }
}