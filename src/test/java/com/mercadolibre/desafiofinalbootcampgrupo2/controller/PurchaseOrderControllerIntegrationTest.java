package com.mercadolibre.desafiofinalbootcampgrupo2.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.desafiofinalbootcampgrupo2.dto.PurchaseOrderCreateDTO;
import com.mercadolibre.desafiofinalbootcampgrupo2.dto.PurchaseOrderDTO;
import com.mercadolibre.desafiofinalbootcampgrupo2.utils.ApplicationConfigTest;
import com.mercadolibre.desafiofinalbootcampgrupo2.utils.TypeOfUser;
import org.junit.jupiter.api.Assertions;
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

import static com.mercadolibre.desafiofinalbootcampgrupo2.utils.Factory.generateListOfValidPurchaseOrderCreateDTO;
import static com.mercadolibre.desafiofinalbootcampgrupo2.utils.Factory.generateListOfValidPurchaseOrderDTO;
import static com.mercadolibre.desafiofinalbootcampgrupo2.utils.TokenGenerator.getUserToken;


public class PurchaseOrderControllerIntegrationTest extends ApplicationConfigTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void shouldRegisterPurchaseOrderWhenCorrectData() throws Exception {
        PurchaseOrderCreateDTO validPurchaseOrderCreateDTO = generateListOfValidPurchaseOrderCreateDTO();
        String payload = mapper.writeValueAsString(validPurchaseOrderCreateDTO);
        String token = getUserToken(mockMvc, TypeOfUser.BUYER);

        mockMvc.perform(MockMvcRequestBuilders.post("/fresh-products/orders/")
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
    public void shouldReturn403WhenRepresentativeIsNotAuthorize() throws Exception {
        PurchaseOrderCreateDTO validPurchaseOrderCreateDTO = generateListOfValidPurchaseOrderCreateDTO();
        String payload = mapper.writeValueAsString(validPurchaseOrderCreateDTO);
        String token = getUserToken(mockMvc, TypeOfUser.REPRESENTATIVE);

        mockMvc.perform(MockMvcRequestBuilders.post("/fresh-products/orders/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token)
                        .content(payload))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    public void shouldUpdatePurchaseOrderWhenCorrectData() throws Exception {
        PurchaseOrderDTO validPurchaseOrderDTO = generateListOfValidPurchaseOrderDTO();
        String payload = mapper.writeValueAsString(validPurchaseOrderDTO);
        String token = getUserToken(mockMvc, TypeOfUser.BUYER);

        mockMvc.perform(MockMvcRequestBuilders.put("/fresh-products/orders/")
                        .queryParam("purchaseOrderId", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token)
                        .content(payload))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void shouldGetPurchaseOrderWhenCorrectData() throws Exception {
        String token = getUserToken(mockMvc, TypeOfUser.BUYER);

        mockMvc.perform(MockMvcRequestBuilders.get("/fresh-products/orders/")
                        .queryParam("purchaseOrderId", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void shouldNotFoundPurchaseOrderWhenPurchaseOrderIdIsInvalid() throws Exception {
        String token = getUserToken(mockMvc, TypeOfUser.BUYER);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/fresh-products/orders/")
                        .queryParam("purchaseOrderId", "2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();

        String expect = "Purchase order not exists in the Database";
        Assertions.assertTrue(result.getResponse().getContentAsString().contains(expect));
    }
}
