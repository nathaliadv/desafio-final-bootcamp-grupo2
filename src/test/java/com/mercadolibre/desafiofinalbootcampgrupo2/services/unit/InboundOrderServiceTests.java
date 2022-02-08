package com.mercadolibre.desafiofinalbootcampgrupo2.services.unit;

import com.mercadolibre.desafiofinalbootcampgrupo2.exception.DateInvalidException;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.Batch;
import com.mercadolibre.desafiofinalbootcampgrupo2.model.InboundOrder;
import com.mercadolibre.desafiofinalbootcampgrupo2.services.InboundOrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static com.mercadolibre.desafiofinalbootcampgrupo2.utils.Factory.generateListOfValidBatchs;
import static com.mercadolibre.desafiofinalbootcampgrupo2.utils.Factory.generateValidInboundOrder;

public class InboundOrderServiceTests {

    @Test
    public void verifyCreationDateShouldVerifyIfCreationDateWasCreated() {
        InboundOrder inboundOrder = generateValidInboundOrder();
        InboundOrderService inboundOrderService = new InboundOrderService(null);
        inboundOrder.setCreationDate(LocalDate.now());

        Assertions.assertDoesNotThrow(() -> inboundOrderService.verifyCreationDate(inboundOrder));
    }

    @Test
    public void verifyCreationDateShouldThrowDateInvalidException() throws DateInvalidException {
        InboundOrder inboundOrder = generateValidInboundOrder();
        InboundOrderService inboundOrderService = new InboundOrderService(null);
        inboundOrder.setCreationDate(LocalDate.now().plusDays(2));

        Assertions.assertThrows(DateInvalidException.class, () -> inboundOrderService.verifyCreationDate(inboundOrder));
    }

    @Test
    public void verifyExpirationDateShouldVerifyIfExpirationDate() {
        List<Batch> batchList = generateListOfValidBatchs();
        InboundOrderService inboundOrderService = new InboundOrderService(null);
        batchList.get(0).setExpirationDate(LocalDate.now().plusDays(22));

        Assertions.assertDoesNotThrow(() -> inboundOrderService.verifyExpirationDate(batchList));
    }

    @Test
    public void verifyExpirationDateShouldThrowDateInvalidException() throws DateInvalidException {
        List<Batch> batchList = generateListOfValidBatchs();
        InboundOrderService inboundOrderService = new InboundOrderService(null);
        batchList.get(0).setExpirationDate(LocalDate.now());

        Assertions.assertThrows(DateInvalidException.class, () -> inboundOrderService.verifyExpirationDate(batchList));
    }

    @Test
    public void verifyManufactureDateShouldVerifyManufactureDate() {
        List<Batch> batchList = generateListOfValidBatchs();
        InboundOrderService inboundOrderService = new InboundOrderService(null);
        batchList.get(0).setManufacturingDate(LocalDate.now());

        Assertions.assertDoesNotThrow(() -> inboundOrderService.verifyManufactureDate(batchList));
    }

    @Test
    public void verifyManufactureDateShouldThrowDateInvalidException() throws DateInvalidException {
        List<Batch> batchList = generateListOfValidBatchs();
        InboundOrderService inboundOrderService = new InboundOrderService(null);
        batchList.get(0).setManufacturingDate(LocalDate.now().plusDays(23));

        Assertions.assertThrows(DateInvalidException.class, () -> inboundOrderService.verifyManufactureDate(batchList));
    }

}