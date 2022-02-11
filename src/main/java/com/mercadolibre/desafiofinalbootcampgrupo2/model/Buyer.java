package com.mercadolibre.desafiofinalbootcampgrupo2.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Buyer extends User {

    @OneToMany(mappedBy = "buyer")
    private List<PurchaseOrder> purchaseOrder;

    @OneToMany(mappedBy = "buyer")
    private List<ReturnOrder> returnOrder;
}
