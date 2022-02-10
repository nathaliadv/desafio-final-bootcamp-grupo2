package com.mercadolibre.desafiofinalbootcampgrupo2.services;

import com.mercadolibre.desafiofinalbootcampgrupo2.dao.ProductTypeDAO;
import com.mercadolibre.desafiofinalbootcampgrupo2.dao.RepresentativeDAO;
import com.mercadolibre.desafiofinalbootcampgrupo2.dao.SectionDAO;
import com.mercadolibre.desafiofinalbootcampgrupo2.dao.WarehouseDAO;
import com.mercadolibre.desafiofinalbootcampgrupo2.dto.*;
import com.mercadolibre.desafiofinalbootcampgrupo2.exception.DontMatchesException;
import com.mercadolibre.desafiofinalbootcampgrupo2.exception.RepositoryException;
import com.mercadolibre.desafiofinalbootcampgrupo2.exception.SectionSpaceNotAvailableException;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SectionService implements EntityService<Section> {

    private SectionDAO sectionDAO;

    @Autowired
    WarehouseDAO warehouseDAO;

    @Autowired
    ProductTypeDAO productTypeDAO;

    @Autowired
    RepresentativeDAO representativeDAO;

    @Autowired
    WarehouseService warehouseservice;

    public SectionService(SectionDAO sectionDAO) {
        this.sectionDAO = sectionDAO;
    }

    @Override
    public Section findById(Long id) {
        return sectionDAO.findById(id)
                .orElseThrow(() -> new RepositoryException("Section not exists in the Database, please contact the administrator"));
    }

    public List<SectionInfoDTO> findAll() {
        return convertSection(sectionDAO.findAll());
    }

    public List<SectionInfoDTO> convertSection (List<Section> section){
        return section.stream().map(SectionInfoDTO::new).collect(Collectors.toList());
    }

    public SectionInfoDTO addSection(SectionCreateDTO section) throws SQLException {
        Warehouse warehouseid = warehouseDAO.findById(section.getWarehouseId()).orElseThrow(SQLException::new);
        ProductType productid = productTypeDAO.findById(section.getProductTypeId()).orElseThrow(SQLException::new);
        Representative representative = representativeDAO.findById(section.getRepresentativeId()).orElseThrow(SQLException::new);

        Section sec = Section.builder()
                .name(section.getNameSection())
                .volume(section.getCapacity())
                .temperature(section.getTemperature())
                .description(section.getDescription())
                .warehouse(warehouseid)
                .productType(productid)
                .representative(representative)
                .build();
        return convertSectionInDTO(sectionDAO.save(sec));
    }

    private SectionInfoDTO convertSectionInDTO(Section section){

        return SectionInfoDTO.builder()
                .nameSection(section.getName())
                .capacity(section.getVolume())
                .temperature(section.getTemperature())
                .description(section.getDescription())
                .warehouse(WarehouseDTO.builder()
                        .nameWarehouse(section.getWarehouse().getName())
                        .build())
                .address(AddressDTO.builder()
                        .street(section.getWarehouse().getAddress().getStreet())
                        .city(section.getWarehouse().getAddress().getCity())
                        .state(section.getWarehouse().getAddress().getState())
                        .postalCode(section.getWarehouse().getAddress().getPostalCode())
                        .country(section.getWarehouse().getAddress().getCountry())
                        .build())
                .stores(ProductTypeDTO.builder()
                        .type(section.getProductType().getType())
                        .build())
                .representative(RepresentativeInfoDTO.builder()
                        .name(section.getRepresentative().getName())
                        .email(section.getRepresentative().getEmail())
                        .build())
                .build();
    }

    public void deleteSection(Long id){
        sectionDAO.deleteById(id);
    }

    public void calVolumeCheckin(Batch batch, Section section){

        section.calVolumeCheckin(batch);
        sectionDAO.save(section);
    }

    public void calVolumeCheckout(Batch batch, Section section){

        section.calVolumeCheckout(batch);
        sectionDAO.save(section);
    }

    public void verifyIfSectionHaveSpaceEnoughToAddBatches(Section section, List<Batch> batchs) {
        for (Batch batch : batchs) {
            if (section.calVolumeCheckin(batch) < 0) {
                throw new SectionSpaceNotAvailableException("Space not available in the section, please contact an administrator");
            }
        }
    }

    protected void verifyIfSectorExistsInWarehouse(Long sectionCode, Long warehouseCode) {
        if (!findById(sectionCode).getWarehouse().getId().equals(warehouseCode))
            throw new RepositoryException("The mentioned Section don't exists in mentioned Warehouse.");
    }

    protected void verifyIfRepresentativeWorksInSection(Long sectionCode, Long representativeCode) {
        if (!findById(sectionCode).getRepresentative().getId().equals(representativeCode)) {
            throw new DontMatchesException("Representative don't matches with mentioned Section.");
        }
    }
}