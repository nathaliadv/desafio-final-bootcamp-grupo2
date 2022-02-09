package com.mercadolibre.desafiofinalbootcampgrupo2.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "tb_section")
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double volume;
    private double temperature;
    private String description;

    @ManyToOne
    private Representative representative;

    @ManyToOne
    private Warehouse warehouse;

    @OneToMany(mappedBy = "section")
    private List<InboundOrder> orders;

    @ManyToOne
    private ProductType productType;

    public double calVolumeCheckin(Batch batch){
        return this.volume - (batch.getCurrentQuantity() * batch.getAdvertising().getProduct().getVolume());
    }

    public double calVolumeCheckout(Batch batch){
        return this.volume + (batch.getCurrentQuantity() * batch.getAdvertising().getProduct().getVolume());
    }
}
