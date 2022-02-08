package com.mercadolibre.desafiofinalbootcampgrupo2.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.desafiofinalbootcampgrupo2.dto.InboundOrderDTO;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.BootstrapWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.security.RunAs;

import static com.mercadolibre.desafiofinalbootcampgrupo2.utils.Factory.generateValidInboundOrderDTO;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@AutoConfigureMockMvc
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    //REQ3
    @WithMockUser(value = "spring")
    @Test
    public void shouldListProductListByIdInWarehouse() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/fresh-products/?productCode=4&warehouseCode=2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(""))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andReturn();
    }

    //REQ4
    @Test
    public void shouldReturnTheTotalQuantityOfSpecifyProduct() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/fresh-products/warehouse/?productCode=4")
                .contentType(MediaType.APPLICATION_JSON)
                .content(""))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andReturn();
    }

    @Test
    public void shouldReturnTheProductsByDueDate() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/fresh-products/due-date/?sectionId=6&warehouseId=2&numberDays=900")
                .contentType(MediaType.APPLICATION_JSON)
                .content(""))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andReturn();
    }

    @Test
    public void shouldReturnTheProductsByDueDateANDFilter() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/fresh-products/due-date/list?productTypeId=1&numberDays=700&orderBy=asc")
                .contentType(MediaType.APPLICATION_JSON)
                .content(""))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andReturn();
    }
}
