package com.mercadolibre.desafiofinalbootcampgrupo2.services;


import com.mercadolibre.desafiofinalbootcampgrupo2.model.Representative;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.Section;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class InboundOrderServiceTests {

    @Autowired
    private InboundOrderService inboundOrderService;

    @Test
    public void verifyIfSectorExistsInWarehouseShouldValidSectorInWarehouse() {
        Long sectorId = 1L;
        Long warehouseId = 1L;

        Assertions.assertDoesNotThrow(() -> inboundOrderService.verifyIfSectorExistsInWarehouse(sectorId, warehouseId));
    }

    @Test
    public void verifyIfRepresentativeWorksInSectionShouldThrowRepositoryExceptionWhenIncorrectRepresentativeId() {
        Long representativeId = 100L;
        Section section = Mockito.mock(Section.class);
        Representative representative = Mockito.mock(Representative.class);
        representative.setId(1L);
        section.setRepresentative(representative);

        Assertions.assertThrows(RuntimeException.class, () -> inboundOrderService.verifyIfRepresentativeWorksInSection(section, representativeId));
    }

    @Test
    public void verifyIfProductsAreTheSameTypeOfSectionShouldThrowRepositoryExceptionWhenIncorrectSectorId() {
        Long sectorId = 100L;
        Long warehouseId = 100L;

        Assertions.assertThrows(RuntimeException.class, () -> inboundOrderService.verifyIfSectorExistsInWarehouse(sectorId, warehouseId));
    }
}
