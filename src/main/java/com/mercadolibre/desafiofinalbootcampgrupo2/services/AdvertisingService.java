package com.mercadolibre.desafiofinalbootcampgrupo2.services;

import com.mercadolibre.desafiofinalbootcampgrupo2.dao.AdvertisingDAO;
import com.mercadolibre.desafiofinalbootcampgrupo2.dto.AdvertisingDTO;
import com.mercadolibre.desafiofinalbootcampgrupo2.dto.AdvertisingResponseDTO;
import com.mercadolibre.desafiofinalbootcampgrupo2.exception.DontMatchesException;
import com.mercadolibre.desafiofinalbootcampgrupo2.exception.RepositoryException;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.Advertising;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.Seller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdvertisingService implements EntityService<Advertising> {

    @Autowired
    private AdvertisingDAO advertisingDAO;

    @Autowired
    private ProductService productService;

    @Autowired
    private SellerService sellerService;

    @Override
    public Advertising findById(Long id) {
        return advertisingDAO.findById(id)
                .orElseThrow(() -> new RepositoryException("Advertising not exists in the Database"));
    }

    public List<AdvertisingResponseDTO> findAllByFreeShipping(boolean isFreeShipping) {
        List<Advertising> allAdvertisingsByFreeShipping = advertisingDAO.findAllByFreeShipping(isFreeShipping);
        return allAdvertisingsByFreeShipping.stream().map(AdvertisingResponseDTO::new).collect(Collectors.toList());
    }

    public List<AdvertisingResponseDTO> findAllByType(String type) {
        type = convertAndValidateType(type);
        List<AdvertisingResponseDTO> advertisingsByProductProductType = advertisingDAO.findAllByProductProductType(type);
        checkIfListIsEmpty(advertisingsByProductProductType);
        return advertisingsByProductProductType;
    }

    public List<AdvertisingResponseDTO> findAllInStock() {
        List<AdvertisingResponseDTO> products = advertisingDAO.findAllInStock();
        checkIfListIsEmpty(products);
        return products;
    }

    public AdvertisingResponseDTO saveAdvertising(AdvertisingDTO dto) {
        Advertising entity = convertAdvertisingDTOtoEntity(dto);
        entity.setProduct(productService.findById(dto.getProductCode()));
        entity.setSeller(sellerService.findById(getUserId()));
        return new AdvertisingResponseDTO(advertisingDAO.save(entity));
    }

    public AdvertisingResponseDTO updateAdvertising(AdvertisingDTO dto, Long id) {
        Advertising entity = convertAdvertisingDTOtoEntity(dto);
        entity.setId(findById(id).getId());
        entity.setProduct(productService.findById(dto.getProductCode()));
        entity.setSeller(validIfLoggedSellerIsOwnerOfAdvertising(id));
        return new AdvertisingResponseDTO(advertisingDAO.save(entity));
    }

    public Seller validIfLoggedSellerIsOwnerOfAdvertising(Long id){
        Seller seller = sellerService.findById(getUserId());
        if (!findById(id).getSeller().equals(seller))
            throw new DontMatchesException("Seller aren't owner of Advertising ID " + id);
        return seller;
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

    public AdvertisingResponseDTO findByIdDTO(Long id) {
        return new AdvertisingResponseDTO(findById(id));
    }

    public <T> void checkIfListIsEmpty(List<T> list) {
        if (list.isEmpty()) {
            throw new RepositoryException("");
        }
    }

    private Advertising convertAdvertisingDTOtoEntity(AdvertisingDTO dto) {
        return Advertising.builder()
                .description(dto.getDescription())
                .price(dto.getPrice())
                .freeShipping(dto.getFreeShipping())
                .build();
    }

    private Long getUserId() {
        return ((Seller) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
    }

    public void deleteAdvertisingByID(Long id) {
        findById(id);
        advertisingDAO.deleteById(id);
    }
}