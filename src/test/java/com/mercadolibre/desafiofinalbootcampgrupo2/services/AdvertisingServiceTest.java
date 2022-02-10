package com.mercadolibre.desafiofinalbootcampgrupo2.services;

import com.mercadolibre.desafiofinalbootcampgrupo2.exception.RepositoryException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AdvertisingServiceTest {

    @Test
    void shouldConvertAndValidateType() {
        AdvertisingService advertisingService = new AdvertisingService();
        String freshResult = advertisingService.convertAndValidateType("FS");
        String coldResult = advertisingService.convertAndValidateType("RF");
        String freezeResult = advertisingService.convertAndValidateType("FF");

        Assertions.assertEquals("FRESH", freshResult);
        Assertions.assertEquals("COLD", coldResult);
        Assertions.assertEquals("FREEZE", freezeResult);
        Assertions.assertThrows(RepositoryException.class, () -> advertisingService.convertAndValidateType("FRESH"));
    }

}
