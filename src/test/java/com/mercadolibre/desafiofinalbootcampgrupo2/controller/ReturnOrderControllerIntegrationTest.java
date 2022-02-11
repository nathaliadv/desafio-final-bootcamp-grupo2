package com.mercadolibre.desafiofinalbootcampgrupo2.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.desafiofinalbootcampgrupo2.dto.PurchaseOrderDTO;
import com.mercadolibre.desafiofinalbootcampgrupo2.dto.ReturnOrderCreateDTO;
import com.mercadolibre.desafiofinalbootcampgrupo2.utils.ApplicationConfigTest;
import com.mercadolibre.desafiofinalbootcampgrupo2.utils.TypeOfUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.mercadolibre.desafiofinalbootcampgrupo2.utils.Factory.*;
import static com.mercadolibre.desafiofinalbootcampgrupo2.utils.TokenGenerator.getUserToken;


public class ReturnOrderControllerIntegrationTest extends ApplicationConfigTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void shouldRegisterReturnOrderWhenCorrectData() throws Exception {
        ReturnOrderCreateDTO validReturnOrderCreateDTO = generateValidReturnOrderCreateDTO();
        String token = getUserToken(mockMvc, TypeOfUser.BUYER);
        String payload = mapper.writeValueAsString(validReturnOrderCreateDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/fresh-products/returnorders/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token)
                        .content(payload))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.header().exists("Location"))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andReturn();
    }

    @Test
    public void shouldReturn403WhenUserNotAuthorizedToTryRegisterReturnOrderWhenCorrectData() throws Exception {
        ReturnOrderCreateDTO validReturnOrderCreateDTO = generateValidReturnOrderCreateDTO();
        String token = getUserToken(mockMvc, TypeOfUser.REPRESENTATIVE);
        String payload = mapper.writeValueAsString(validReturnOrderCreateDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/fresh-products/returnorders/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token)
                        .content(payload))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    public void shouldGetReturnOrderWhenCorrectData() throws Exception {
        String token = getUserToken(mockMvc, TypeOfUser.BUYER);

        mockMvc.perform(MockMvcRequestBuilders.get("/fresh-products/returnorders/")
                        .queryParam("returnOrderId", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void shouldNotFoundReturnOrderWhenReturnOrderIdIsInvalid() throws Exception {
        String token = getUserToken(mockMvc, TypeOfUser.BUYER);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/fresh-products/returnorders/")
                        .queryParam("returnOrderId", "20")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();

        String expect = "Return order not exists in the Database";
        Assertions.assertTrue(result.getResponse().getContentAsString().contains(expect));
    }

    @Test
    public void shouldUpdateReturnOrderWhenCorrectData() throws Exception {
        ReturnOrderCreateDTO validReturnOrderCreateDTO = generateValidReturnOrderCreateDTO();
        String payload = mapper.writeValueAsString(validReturnOrderCreateDTO);
        String token = getUserToken(mockMvc, TypeOfUser.BUYER);

        mockMvc.perform(MockMvcRequestBuilders.put("/fresh-products/returnorders/")
                        .queryParam("returnOrderId", "2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token)
                        .content(payload))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void shouldNotUpdateReturnOrderWhenInvalidCorrectData() throws Exception {
        ReturnOrderCreateDTO invalidReturnOrderCreateDTO = generateReturnOrderCreateWithInvalidCauseDTO();
        String payload = mapper.writeValueAsString(invalidReturnOrderCreateDTO);
        String token = getUserToken(mockMvc, TypeOfUser.BUYER);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/fresh-products/returnorders/")
                        .queryParam("returnOrderId", "2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token)
                        .content(payload))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();

        String expect = "Cause not exists in the Database";
        Assertions.assertTrue(result.getResponse().getContentAsString().contains(expect));

    }

    @Test
    public void shouldCancelReturnOrderWhenCorrectData() throws Exception {
        String token = getUserToken(mockMvc, TypeOfUser.BUYER);

        mockMvc.perform(MockMvcRequestBuilders.put("/fresh-products/returnorders/cancel/")
                        .queryParam("returnOrderId", "2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
