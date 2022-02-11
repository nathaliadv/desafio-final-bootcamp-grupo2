package com.mercadolibre.desafiofinalbootcampgrupo2.services;

import com.mercadolibre.desafiofinalbootcampgrupo2.dao.RepresentativeDAO;
import com.mercadolibre.desafiofinalbootcampgrupo2.exception.RepositoryException;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.Representative;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RepresentativeService implements EntityService<Representative> {

    @Autowired
    private RepresentativeDAO representativeDAO;

    public RepresentativeService(RepresentativeDAO representativeDAO) {
        this.representativeDAO = representativeDAO;
    }

    @Override
    public Representative findById(Long id) {
        return representativeDAO.findById(id)
                .orElseThrow(() -> new RepositoryException("Representative not exists in database, please contact the administrator"));
    }


}