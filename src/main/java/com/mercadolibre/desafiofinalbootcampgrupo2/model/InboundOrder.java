package com.mercadolibre.desafiofinalbootcampgrupo2.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "inbound_order")
public class InboundOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate creationDate;
    @ManyToOne
    private Section section;
    @OneToMany(mappedBy = "order")
    private List<Batch> batchs;
}
