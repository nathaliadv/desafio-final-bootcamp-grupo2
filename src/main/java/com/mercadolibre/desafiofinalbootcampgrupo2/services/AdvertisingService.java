package com.mercadolibre.desafiofinalbootcampgrupo2.services;

import com.mercadolibre.desafiofinalbootcampgrupo2.dao.AdvertisingDAO;
import com.mercadolibre.desafiofinalbootcampgrupo2.dao.ProductTypeDAO;
import com.mercadolibre.desafiofinalbootcampgrupo2.exception.RepositoryException;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.Advertising;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdvertisingService implements EntityService<Advertising> {

    @Autowired
    private AdvertisingDAO advertisingDAO;

    @Autowired
    private ProductTypeDAO productTypeDAO;

    public List<ProductTypeDAO.AdvertisingDTO> getByType(String type) {
        String typeValidated = convertAndValidateType(type);
        List<ProductTypeDAO.AdvertisingDTO> products = productTypeDAO.advertisingList(typeValidated);
        checkIfListIsEmpty(products);

        return products;
    }

    public List<AdvertisingDAO.AdvertisingDTO> findAllInStock() {

        List<AdvertisingDAO.AdvertisingDTO> products = advertisingDAO.findAllInStock();

        checkIfListIsEmpty(products);

        return products;
    }

    public String convertAndValidateType(String type) {
        switch (type.toUpperCase()) {
            case "FS":
                return "FRESH";
            case "RF":
                return "COLD";
            case "FF":
                return "FREEZE";
            default:
                throw new RepositoryException("Type is not valid");
        }
    }

    @Override
    public Advertising findById(Long id) {
        return advertisingDAO.findById(id)
                .orElseThrow(() -> new RepositoryException("Advertising not exists in the Database"));
    }

    public <T> void checkIfListIsEmpty(List<T> list) {
        if (list.isEmpty()) {
            throw new RepositoryException("");
        }
    }
}
