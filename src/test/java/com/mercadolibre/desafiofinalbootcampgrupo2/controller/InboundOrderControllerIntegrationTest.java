package com.mercadolibre.desafiofinalbootcampgrupo2.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.desafiofinalbootcampgrupo2.dto.InboundOrderDTO;
import com.mercadolibre.desafiofinalbootcampgrupo2.utils.ApplicationConfigTest;
import com.mercadolibre.desafiofinalbootcampgrupo2.utils.TypeOfUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.mercadolibre.desafiofinalbootcampgrupo2.utils.Factory.generateInvalidInboundOrderDTO;
import static com.mercadolibre.desafiofinalbootcampgrupo2.utils.Factory.generateValidInboundOrderDTO;
import static com.mercadolibre.desafiofinalbootcampgrupo2.utils.TokenGenerator.getUserToken;

public class InboundOrderControllerIntegrationTest extends ApplicationConfigTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void registerInboundOrderShouldSaveAnInboundOrderWhenCorrectData() throws Exception {
        InboundOrderDTO validInboundOrderDTO = generateValidInboundOrderDTO();
        String payload = mapper.writeValueAsString(validInboundOrderDTO);
        String token = getUserToken(mockMvc, TypeOfUser.REPRESENTATIVE);

        mockMvc.perform(MockMvcRequestBuilders.post("/fresh-products/inboundorder/")
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
    public void registerInboundOrderShouldReturn400WhenIncorrectDate() throws Exception {
        InboundOrderDTO inboundOrderDTO = generateValidInboundOrderDTO();
        inboundOrderDTO.setBatchs(null);
        String token = getUserToken(mockMvc, TypeOfUser.REPRESENTATIVE);

        String payload = mapper.writeValueAsString(inboundOrderDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/fresh-products/inboundorder/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token)
                        .content(payload))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andReturn();
    }

    @Test
    public void shouldReturn404WhenRegisterInboundOrderSectionIsNotValid() throws Exception {
        InboundOrderDTO inboundOrderDTO = generateInvalidInboundOrderDTO();
        inboundOrderDTO.setBatchs(null);
        String token = getUserToken(mockMvc, TypeOfUser.REPRESENTATIVE);

        String payload = mapper.writeValueAsString(inboundOrderDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/fresh-products/inboundorder/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token)
                        .content(payload))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andReturn();
    }

    @Test
    public void getInboundOrderByIdShouldReturnAnInboundOrderDTOWhenExistingId() throws Exception {
        String token = getUserToken(mockMvc, TypeOfUser.REPRESENTATIVE);
        Long existingID = 1L;

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/fresh-products/inboundorder/{id}", existingID)
                                .header("Authorization", token))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andReturn();
    }

    @Test
    public void getInboundOrderByIdShouldReturn404WhenNonExistingId() throws Exception {
        String token = getUserToken(mockMvc, TypeOfUser.REPRESENTATIVE);
        Long nonExistingID = 1000L;

        MvcResult result = mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/fresh-products/inboundorder/{id}", nonExistingID)
                                .header("Authorization", token))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    public void putInboundOrderShouldPutAnInboundOrderWhenCorrectData() throws Exception {
        Long existingID = 1L;
        InboundOrderDTO validInboundOrderDTO = generateValidInboundOrderDTO();
        String payload = mapper.writeValueAsString(validInboundOrderDTO);
        String token = getUserToken(mockMvc, TypeOfUser.REPRESENTATIVE);

        mockMvc.perform(MockMvcRequestBuilders.put("/fresh-products/inboundorder/{id}", existingID)
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
    public void putInboundOrderShouldReturn400WhenIncorrectDate() throws Exception {
        Long existingID = 1L;
        InboundOrderDTO inboundOrderDTO = generateValidInboundOrderDTO();
        inboundOrderDTO.setBatchs(null);

        String payload = mapper.writeValueAsString(inboundOrderDTO);
        String token = getUserToken(mockMvc, TypeOfUser.REPRESENTATIVE);
        mockMvc.perform(MockMvcRequestBuilders.put("/fresh-products/inboundorder/{id}", existingID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token)
                        .content(payload))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andReturn();
    }

    @Test
    public void putInboundOrderShouldReturn404WhenIncorrectDate() throws Exception {
        String token = getUserToken(mockMvc, TypeOfUser.REPRESENTATIVE);
        Long nonExistingID = 1L;
        InboundOrderDTO inboundOrderDTO = generateValidInboundOrderDTO();
        inboundOrderDTO.setBatchs(null);

        String payload = mapper.writeValueAsString(inboundOrderDTO);

        mockMvc.perform(MockMvcRequestBuilders.put("/fresh-products/inboundorder/{id}", nonExistingID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token)
                        .content(payload))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andReturn();
    }
}