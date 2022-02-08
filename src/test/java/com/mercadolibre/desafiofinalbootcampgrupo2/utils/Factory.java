package com.mercadolibre.desafiofinalbootcampgrupo2.utils;

import com.mercadolibre.desafiofinalbootcampgrupo2.dto.BatchDTO;
import com.mercadolibre.desafiofinalbootcampgrupo2.dto.InboundOrderDTO;
import com.mercadolibre.desafiofinalbootcampgrupo2.dto.SectionDTO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

public class Factory {

    public static InboundOrderDTO generateValidInboundOrderDTO() {
        return InboundOrderDTO.builder()
                .creationDate(LocalDate.now())
                .section(generateValidSectionDTO())
                .batchs(generateListOfValidBatchDTOs())
                .representative(2L)
                .build();
    }

    private static SectionDTO generateValidSectionDTO() {
        return SectionDTO.builder().sectionCode(6L).warehouseCode(2L).build();
    }

    private static List<BatchDTO> generateListOfValidBatchDTOs() {
        return Arrays.asList(
                BatchDTO.builder()
                        .currentTemperature(10.0)
                        .currentQuantity(5)
                        .initialQuantity(0)
                        .expirationDate(LocalDate.of(2022, 03, 21))
                        .manufacturingDate(LocalDate.of(2022, 01, 21))
                        .manufacturingTime(LocalTime.now())
                        .minimumTemperature(1.0)
                        .advertsimentId(5L)
                        .build(),

                BatchDTO.builder()
                        .currentTemperature(10.0)
                        .currentQuantity(5)
                        .initialQuantity(0)
                        .expirationDate(LocalDate.of(2022, 03, 21))
                        .manufacturingDate(LocalDate.of(2022, 01, 21))
                        .manufacturingTime(LocalTime.now())
                        .minimumTemperature(1.0)
                        .advertsimentId(5L)
                        .build()
        );
    }
}
