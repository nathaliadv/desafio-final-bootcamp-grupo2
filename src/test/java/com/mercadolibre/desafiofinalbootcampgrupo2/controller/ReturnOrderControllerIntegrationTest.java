package com.mercadolibre.desafiofinalbootcampgrupo2.controller;

import com.mercadolibre.desafiofinalbootcampgrupo2.utils.TypeOfUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static com.mercadolibre.desafiofinalbootcampgrupo2.utils.TokenGenerator.getUserToken;

@SpringBootTest
@AutoConfigureMockMvc
public class ReturnOrderControllerIntegrationTest {

    String token = getUserToken(mockMvc, TypeOfUser.REPRESENTATIVE);

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnTheReturnOrderById() throws Exception{

    }
}
