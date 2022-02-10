package com.mercadolibre.desafiofinalbootcampgrupo2.services;

import com.mercadolibre.desafiofinalbootcampgrupo2.controller.advices.dao.WarehouseDAO;
import com.mercadolibre.desafiofinalbootcampgrupo2.exception.RepositoryException;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.Warehouse;
import org.springframework.stereotype.Service;

@Service
public class WarehouseService implements EntityService<Warehouse> {

    private WarehouseDAO warehouseDAO;

    public WarehouseService(WarehouseDAO warehouseDAO) {
        this.warehouseDAO = warehouseDAO;
    }

    @Override
    public Warehouse findById(Long id) {
        return warehouseDAO.findById(id).orElseThrow(() -> new RepositoryException("WareHouse not exists in database, please contact the administrator"));
    }
}