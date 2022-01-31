package com.mercadolibre.desafiofinalbootcampgrupo2.services;

import com.mercadolibre.desafiofinalbootcampgrupo2.dao.AdvertisingDAO;
import com.mercadolibre.desafiofinalbootcampgrupo2.exception.RepositoryException;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.Advertising;
import org.springframework.stereotype.Service;

@Service
public class AdvertisingService implements EntityService<Advertising> {

    private AdvertisingDAO advertisingDAO;

    public AdvertisingService(AdvertisingDAO advertisingDAO) {
        this.advertisingDAO = advertisingDAO;
    }

    @Override
    public Advertising findById(Long id) {
        return advertisingDAO.findById(id)
                .orElseThrow(() -> new RepositoryException("Advertise not exists in the Database"));
    }
}