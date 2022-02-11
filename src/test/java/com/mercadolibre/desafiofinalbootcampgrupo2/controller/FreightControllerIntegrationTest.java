package com.mercadolibre.desafiofinalbootcampgrupo2.controller;

import com.mercadolibre.desafiofinalbootcampgrupo2.utils.ApplicationConfigTest;
import com.mercadolibre.desafiofinalbootcampgrupo2.utils.TypeOfUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.mercadolibre.desafiofinalbootcampgrupo2.utils.TokenGenerator.getUserToken;

public class FreightControllerIntegrationTest extends ApplicationConfigTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnShippingCost() throws Exception {
        String token = getUserToken(mockMvc, TypeOfUser.BUYER);

        mockMvc.perform(MockMvcRequestBuilders.get("/freight/cost/SP")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token)
                        .content(""))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists());
    }

    @Test
    public void shouldReturn403WhenUserDontHavePermission() throws Exception {
        String token = getUserToken(mockMvc, TypeOfUser.REPRESENTATIVE);

        mockMvc.perform(MockMvcRequestBuilders.get("/freight/cost/SP")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token)
                        .content(""))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    public void shouldReturn201WhenInsertfreight() throws Exception {
        String token = getUserToken(mockMvc, TypeOfUser.REPRESENTATIVE);
        String payload = "{\n" +
                "    \"state\" : \"PARANÁ\",\n" +
                "    \"initial\": \"PR\",\n" +
                "    \"capital\": \"CURITIBA\",\n" +
                "    \"region\": \"SUL\",\n" +
                "    \"shippingCost\": 58 \n" +
                "\n" +
                "}";

        mockMvc.perform(MockMvcRequestBuilders.post("/freight/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token)
                        .content(payload))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }
}
