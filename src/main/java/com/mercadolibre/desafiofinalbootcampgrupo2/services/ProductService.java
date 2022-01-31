package com.mercadolibre.desafiofinalbootcampgrupo2.services;

import com.mercadolibre.desafiofinalbootcampgrupo2.dao.ProductDAO;
import com.mercadolibre.desafiofinalbootcampgrupo2.exception.RepositoryException;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements EntityService<Product> {

    @Autowired
    private ProductDAO productDAO;

    @Override
    public Product findById(Long id) {
        return productDAO.findById(id)
                .orElseThrow(() -> new RepositoryException("Product not exists in database, please contact the administrator"));
    }
}