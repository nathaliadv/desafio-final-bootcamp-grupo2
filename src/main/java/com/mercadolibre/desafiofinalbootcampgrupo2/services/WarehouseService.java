package com.mercadolibre.desafiofinalbootcampgrupo2.services;

import com.mercadolibre.desafiofinalbootcampgrupo2.dao.WarehouseDAO;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.Warehouse;
import org.springframework.stereotype.Service;

@Service
public class WarehouseService {

    private WarehouseDAO warehouseDAO;

    public WarehouseService(WarehouseDAO warehouseDAO) {
        this.warehouseDAO = warehouseDAO;
    }

    public Warehouse findById(Long id) {
        return warehouseDAO.findById(id).orElseThrow(() -> new RuntimeException("WareHouse not exists in database, please contact the administrator"));
    }
}