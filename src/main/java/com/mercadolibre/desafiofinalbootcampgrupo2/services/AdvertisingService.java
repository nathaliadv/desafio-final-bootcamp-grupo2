package com.mercadolibre.desafiofinalbootcampgrupo2.services;

import com.mercadolibre.desafiofinalbootcampgrupo2.dao.AdvertisingDAO;
import com.mercadolibre.desafiofinalbootcampgrupo2.dao.ProductTypeDAO;
import com.mercadolibre.desafiofinalbootcampgrupo2.dto.AdvertisingDTO;
import com.mercadolibre.desafiofinalbootcampgrupo2.exception.RepositoryException;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.Advertising;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.Batch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdvertisingService implements EntityService<Advertising> {

    @Autowired
    private AdvertisingDAO advertisingDAO;

    @Autowired
    private ProductTypeDAO productTypeDAO;

    public List<AdvertisingDTO> findAll() {

        try {
            List<Advertising> ad = advertisingDAO.findAll();
            return convertListAdvertisingDTO(ad);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RepositoryException
                    ("Space not available in the section, please contact an administrator");
        }
    }

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

    public AdvertisingDTO convertAdvertisingDTO(Advertising ad) {

        return AdvertisingDTO.builder()
                .name(ad.getProduct().getName())
                .description(ad.getDescription())
                .price(ad.getPrice())
                .quantity(getAdvertisingQuantity(ad))
                .build();
    }

    public List<AdvertisingDTO> convertListAdvertisingDTO(List<Advertising> ad) {
        return ad.stream()
                .map(this::convertAdvertisingDTO)
                .collect(Collectors.toList());
    }

    public Integer getAdvertisingQuantity(Advertising ad) {
        return ad.getBatchs().stream()
                .map(Batch::getCurrentQuantity)
                .reduce(0, Integer::sum);
    }

    @Override
    public Advertising findById(Long id) {
        return advertisingDAO.findById(id)
                .orElseThrow(() -> new RepositoryException("Advertising not exists in the Database"));
    }

    public <T> void checkIfListIsEmpty(List<T> list) {
        if(list.isEmpty())
        {
            throw new RepositoryException("");
        }
    }
}
