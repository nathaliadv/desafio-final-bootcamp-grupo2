package com.mercadolibre.desafiofinalbootcampgrupo2.model;

import com.mercadolibre.desafiofinalbootcampgrupo2.dto.ProductDTO;
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
@Table(name = "tb_purchase_itens")
public class PurchaseItens {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quantity;

    @ManyToOne
    private PurchaseOrder purchaseOrder;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Advertising advertising;


    public PurchaseItens(ProductDTO product) {
        this.id = product.getAdvertisingId();
        this.quantity = product.getQuantity();
    }
}
