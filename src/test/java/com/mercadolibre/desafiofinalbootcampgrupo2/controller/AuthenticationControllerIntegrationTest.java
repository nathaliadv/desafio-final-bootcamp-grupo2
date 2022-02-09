package com.mercadolibre.desafiofinalbootcampgrupo2.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthenticationControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldGetTokenWhenUserIsValid() throws Exception {
        String payload = "{\n" +
                "    \"user\":\"fulano@email.com\",\n" +
                "    \"password\": \"albertinho123\"\n" +
                "}";

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/auth")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String responseRequest = result.getResponse().getContentAsString();
        Assertions.assertTrue(responseRequest.contains("token"));
    }

    @Test
    void shouldNotGetTokenWhenUserIsNotValid() throws Exception {
        String payload = "{\n" +
                "    \"user\":\"invalid@email.com\",\n" +
                "    \"password\": \"invalidPassword\"\n" +
                "}";

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/auth")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(MockMvcResultMatchers.status().isForbidden())
                .andReturn();

        String responseRequest = result.getResponse().getContentAsString();
        Assertions.assertTrue(responseRequest.isEmpty());
    }
}
