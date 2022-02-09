package com.mercadolibre.desafiofinalbootcampgrupo2.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tb_tracking")
public class Tracking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String observacao;

    @OneToOne
    private PurchaseOrder purchaseOrder;

    @ManyToOne
    private Vehicle vehicle;

}
