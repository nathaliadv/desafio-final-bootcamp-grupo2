package com.mercadolibre.desafiofinalbootcampgrupo2.services;

import com.mercadolibre.desafiofinalbootcampgrupo2.dao.BuyerDAO;
import com.mercadolibre.desafiofinalbootcampgrupo2.exception.RepositoryException;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.Buyer;
import org.springframework.stereotype.Service;

@Service
public class BuyerService implements EntityService<Buyer> {

    private BuyerDAO buyerDAO;

    public BuyerService(BuyerDAO buyerDAO) {
        this.buyerDAO = buyerDAO;
    }

    @Override
    public Buyer findById(Long id) {
        return buyerDAO.findById(id)
                .orElseThrow(() -> new RepositoryException("Buyer not exists in the Database"));
    }
}
