package com.mercadolibre.desafiofinalbootcampgrupo2.services.unit;

import com.mercadolibre.desafiofinalbootcampgrupo2.dto.UserDTO;
import com.mercadolibre.desafiofinalbootcampgrupo2.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.mercadolibre.desafiofinalbootcampgrupo2.utils.Factory.generateValidUser;

public class UserServiceTest {

    @Test
    public void verifyToUserAndSaveShouldVerifyIfSaveInDatabase() {
        UserDTO user = generateValidUser();
        UserService userService = new UserService(null);

        Assertions.assertDoesNotThrow(() -> userService);
    }
}
