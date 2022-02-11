package com.mercadolibre.desafiofinalbootcampgrupo2.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tb_return_order")
public class ReturnOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;

    @OneToOne
    private ReturnStatus returnStatus;

    @ManyToOne
    private Buyer buyer;

    @OneToMany(mappedBy = "returnOrder", cascade = CascadeType.ALL)
    private List<ReturnOrderItens> returnItens;

    @OneToOne
    private ReturnCause returnsCause;
}
