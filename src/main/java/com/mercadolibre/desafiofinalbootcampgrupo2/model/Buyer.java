package com.mercadolibre.desafiofinalbootcampgrupo2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;


@Entity
@Table(name = "buyer")
public class Buyer extends User {

    @OneToMany(mappedBy = "buyer")
    private List<PurchaseOrder> purchaseOrder;

}
