package com.mercadolibre.desafiofinalbootcampgrupo2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "purchase_order")
public class PurchaseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;

    @OneToOne
    private PurchaseStatus purchaseStatus;

    @OneToOne
    private Buyer buyer;

    @OneToMany(mappedBy = "purchaseOrder")
    private List<Advertising> advertising;
}
