package com.mercadolibre.desafiofinalbootcampgrupo2.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.desafiofinalbootcampgrupo2.dto.InboundOrderDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.mercadolibre.desafiofinalbootcampgrupo2.utils.Factory.generateValidInboundOrderDTO;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class InboundOrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void registerInboundOrderShouldSaveAnInboundOrderWhenCorrectData() throws Exception {
        InboundOrderDTO validInboundOrderDTO = generateValidInboundOrderDTO();
        String payload = mapper.writeValueAsString(validInboundOrderDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/fresh-products/inboundorder/")
                        .contentType(MediaType.APPLICATION_JSON)
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

        String payload = mapper.writeValueAsString(inboundOrderDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/fresh-products/inboundorder/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andReturn();
    }

    @Test
    public void getInboundOrderByIdShouldReturnAnInboundOrderDTOWhenExistingId() throws Exception {
        Long existingID = 1L;

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/fresh-products/inboundorder/{id}", existingID))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    public void getInboundOrderByIdShouldReturn404WhenNonExistingId() throws Exception {
        Long nonExistingID = 1000L;

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/fresh-products/inboundorder/{id}", nonExistingID))
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

        mockMvc.perform(MockMvcRequestBuilders.put("/fresh-products/inboundorder/{id}", existingID)
                        .contentType(MediaType.APPLICATION_JSON)
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

        mockMvc.perform(MockMvcRequestBuilders.put("/fresh-products/inboundorder/{id}", existingID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andReturn();
    }

    @Test
    public void putInboundOrderShouldReturn404WhenIncorrectDate() throws Exception {
        Long nonExistingID = 1L;
        InboundOrderDTO inboundOrderDTO = generateValidInboundOrderDTO();
        inboundOrderDTO.setBatchs(null);

        String payload = mapper.writeValueAsString(inboundOrderDTO);

        mockMvc.perform(MockMvcRequestBuilders.put("/fresh-products/inboundorder/{id}", nonExistingID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andReturn();
    }
}