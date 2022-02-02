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
public class AdvertisingService implements EntityService<Advertising>{

    @Autowired
    private AdvertisingDAO advertisingDAO;

    @Autowired
    private ProductTypeDAO productDAO;

    public List<AdvertisingDTO> findAll(){

        try {
            List<Advertising> ad = advertisingDAO.findAll();
            return convertListAdvertisingDTO(ad);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RepositoryException
                    ("Space not available in the section, please contact an administrator");
        }
    }

    public List<AdvertisingDTO> getByType(String type){
        return productDAO.advertisingList(type);

    }

    public List<AdvertisingDTO> findAllInStock(){
        return advertisingDAO.findAllInStock();
    }

    public AdvertisingDTO convertAdvertisingDTO(Advertising ad){

        return AdvertisingDTO.builder()
                .name(ad.getProduct().getName())
                .description(ad.getDescription())
                .price(ad.getPrice())
                .quantity(getAdvertisingQuantity(ad))
                .build();
    }

    public List<AdvertisingDTO> convertListAdvertisingDTO(List<Advertising> ad){
        return ad.stream()
                .map(this::convertAdvertisingDTO)
                .collect(Collectors.toList());
    }

    public Integer getAdvertisingQuantity(Advertising ad){
        return ad.getBatchs().stream()
                .map(Batch::getCurrentQuantity)
                .reduce(0, Integer::sum);
    }

    @Override
    public Advertising findById(Long id) {
        return advertisingDAO.findById(id)
                .orElseThrow(() -> new RepositoryException("Advertising not exists in the Database"));
    }
}
