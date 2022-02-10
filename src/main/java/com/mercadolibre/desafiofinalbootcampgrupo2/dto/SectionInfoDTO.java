package com.mercadolibre.desafiofinalbootcampgrupo2.dto;


import com.mercadolibre.desafiofinalbootcampgrupo2.model.Section;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SectionInfoDTO {

    private String nameSection;
    private double capacity;
    private double temperature;
    private String description;
    private WarehouseDTO warehouse;
    private AddressDTO address;
    private ProductTypeDTO stores;
    private RepresentativeInfoDTO representative;


    public SectionInfoDTO(Section section){
        this.nameSection = section.getName();
        this.capacity = section.getVolume();
        this.temperature = section.getTemperature();
        this.description = section.getDescription();

        this.warehouse = WarehouseDTO.builder()
                .nameWarehouse(section.getWarehouse().getName())
                .build();

        this.address = AddressDTO.builder()
                .street(section.getWarehouse().getAddress().getStreet())
                .city(section.getWarehouse().getAddress().getCity())
                .state(section.getWarehouse().getAddress().getState())
                .postalCode(section.getWarehouse().getAddress().getPostalCode())
                .country(section.getWarehouse().getAddress().getCountry())
                .build();

        this.stores = ProductTypeDTO.builder()
                .type(section.getProductType().getType())
                .build();

        this.representative = RepresentativeInfoDTO.builder()
                .name(section.getRepresentative().getName())
                .email(section.getRepresentative().getEmail())
                .build();

    }
}
