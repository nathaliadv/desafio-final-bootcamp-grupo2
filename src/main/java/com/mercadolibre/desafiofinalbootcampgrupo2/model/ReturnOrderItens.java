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
@Table(name = "tb_return_order_itens")
public class ReturnOrderItens {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quantity;

    @OneToOne
    private PurchaseItens purchaseItem;

    @ManyToOne
    private ReturnOrder returnOrder;

}
