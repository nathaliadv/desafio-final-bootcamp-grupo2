package com.mercadolibre.desafiofinalbootcampgrupo2.model;

import com.mercadolibre.desafiofinalbootcampgrupo2.util.ProductType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "section")
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double volume;
    private double temperature;
    private String description;
    private String typeSection;
    @ManyToOne
    private Representative representative;
    @ManyToOne
    private Warehouse warehouse;
    @OneToMany(mappedBy = "section")
    private List<InboundOrder> orders;
    private ProductType productType;


    //TODO verificar calculo
    public double calVolume(Batch batch){

        return this.volume - (batch.getCurrentQuantity() * batch.getAdvertising().getProduct().getVolume());
    }
}
