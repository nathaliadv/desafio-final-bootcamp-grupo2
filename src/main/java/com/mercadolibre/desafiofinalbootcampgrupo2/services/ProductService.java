package com.mercadolibre.desafiofinalbootcampgrupo2.services;

import com.mercadolibre.desafiofinalbootcampgrupo2.dao.ProductDAO;
import com.mercadolibre.desafiofinalbootcampgrupo2.dto.ProductResponseDTO;
import com.mercadolibre.desafiofinalbootcampgrupo2.exception.RepositoryException;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class ProductService implements EntityService<Product> {

    @Autowired
    private ProductDAO productDAO;

    @Override
    public Product findById(Long id) {
        return productDAO.findById(id)
                .orElseThrow(() -> new RepositoryException("Product not exists in database, please contact the administrator"));
    }

    public List<ProductResponseDTO> findProductListByID(Long id, String filter) {
        return sortByAnyParam(productDAO.productList(id), filter);
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
}