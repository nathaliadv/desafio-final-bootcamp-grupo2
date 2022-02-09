package com.mercadolibre.desafiofinalbootcampgrupo2.controller;

import com.mercadolibre.desafiofinalbootcampgrupo2.utils.TypeOfUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.mercadolibre.desafiofinalbootcampgrupo2.utils.TokenGenerator.getUserToken;

@AutoConfigureMockMvc
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldListProductListByIdInWarehouse() throws Exception {
        String token = getUserToken(mockMvc, TypeOfUser.REPRESENTATIVE);

        mockMvc.perform(MockMvcRequestBuilders.get("/fresh-products/?productCode=4&warehouseCode=1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token)
                        .content(""))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andReturn();
    }

    @Test
    public void shouldReturnTheTotalQuantityOfSpecifyProduct() throws Exception {
        String token = getUserToken(mockMvc, TypeOfUser.REPRESENTATIVE);

        mockMvc.perform(MockMvcRequestBuilders.get("/fresh-products/warehouse/?productCode=3")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token)
                        .content(""))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andReturn();
    }

    @Test
    public void shouldReturnTheProductsByDueDate() throws Exception {
        String token = getUserToken(mockMvc, TypeOfUser.REPRESENTATIVE);

        mockMvc.perform(MockMvcRequestBuilders.get("/fresh-products/due-date/?sectionId=1&warehouseId=1&numberDays=700")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token)
                        .content(""))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andReturn();
    }

    @Test
    public void shouldReturnTheProductsByDueDateANDFilter() throws Exception {
        String token = getUserToken(mockMvc, TypeOfUser.REPRESENTATIVE);

        mockMvc.perform(MockMvcRequestBuilders.get("/fresh-products/due-date/list?productTypeId=1&numberDays=700&orderBy=asc")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token)
                        .content(""))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andReturn();
    }
}
