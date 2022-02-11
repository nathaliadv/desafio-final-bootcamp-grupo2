package com.mercadolibre.desafiofinalbootcampgrupo2.services;

import com.mercadolibre.desafiofinalbootcampgrupo2.dao.SellerDAO;
import com.mercadolibre.desafiofinalbootcampgrupo2.exception.RepositoryException;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.Seller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerService implements EntityService<Seller>{

    @Autowired
    private SellerDAO sellerDAO;

    public SellerService(SellerDAO sellerDAO) {
        this.sellerDAO = sellerDAO;
    }

    @Override
    public Seller findById(Long id) {
        return sellerDAO.findById(id)
                .orElseThrow(() -> new RepositoryException("Seller not exists in the Database"));
    }

    public void saveSeller(Seller seller){
        sellerDAO.save(seller);
    }
}
