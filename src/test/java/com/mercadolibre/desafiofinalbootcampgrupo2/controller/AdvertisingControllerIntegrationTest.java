package com.mercadolibre.desafiofinalbootcampgrupo2.controller;

import com.mercadolibre.desafiofinalbootcampgrupo2.dao.AdvertisingDAO;
import com.mercadolibre.desafiofinalbootcampgrupo2.utils.TypeOfUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.mercadolibre.desafiofinalbootcampgrupo2.utils.TokenGenerator.getUserToken;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AdvertisingControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AdvertisingDAO advertisingDAO;

    @Test
    void shouldNotGetAllAdvertisingWhenUserIsNotAuthorize() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/fresh-products/in-stock")
                        .accept(MediaType.APPLICATION_JSON)
                        .header("Authorization", ""))
                .andExpect(status().isForbidden());
    }

    @Test
    void shouldReturnNotFoundWhenRepresentativeIsAuthorizedAndListOfProductIsEmptyToGetAllAdvertising() throws Exception {
        String token = getUserToken(mockMvc, TypeOfUser.REPRESENTATIVE);
        Mockito.when(advertisingDAO.findAllInStock()).thenReturn(new ArrayList<>());

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/fresh-products/in-stock")
                        .accept(MediaType.APPLICATION_JSON)
                        .header("Authorization", token))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnOKWhenRepresentativeIsAuthorizedAndListOfProductIsNotEmptyToGetAllAdvertising() throws Exception {
        String token = getUserToken(mockMvc, TypeOfUser.REPRESENTATIVE);

        List<AdvertisingDAO.AdvertisingDTO> advertisings = new ArrayList<>();
        advertisings.add(new AdvertisingDAO.AdvertisingDTO() {
            @Override
            public String getName() {
                return "Maçã";
            }

            @Override
            public String getDescription() {
                return "Maçazinha gostosinha nham nham";
            }

            @Override
            public BigDecimal getPrice() {
                return new BigDecimal("2.50");
            }

            @Override
            public Integer getQuantity() {
                return 25;
            }
        });

        System.out.println(advertisings);
        Mockito.when(advertisingDAO.findAllInStock()).thenReturn(advertisings);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .get("/fresh-products/in-stock")
                        .accept(MediaType.APPLICATION_JSON)
                        .header("Authorization", token))
                .andExpect(status().isOk())
                .andReturn();
        String expected = "MaÃ§azinha gostosinha nham nham";
        Assertions.assertTrue(result.getResponse().getContentAsString().contains(expected));
    }

    @Test
    void shouldReturnForbiddenWhenBuyerIsNotAuthorizedToGetAllAdvertising() throws Exception {
        String token = getUserToken(mockMvc, TypeOfUser.BUYER);
        Mockito.when(advertisingDAO.findAllInStock()).thenReturn(new ArrayList<>());

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/fresh-products/in-stock")
                        .accept(MediaType.APPLICATION_JSON)
                        .header("Authorization", token))
                .andExpect(status().isForbidden());
    }

    @Test
    void shouldReturnForbiddenWhenSellerIsNotAuthorizedToGetAllAdvertising() throws Exception {
        String token = getUserToken(mockMvc, TypeOfUser.SELLER);
        Mockito.when(advertisingDAO.findAllInStock()).thenReturn(new ArrayList<>());

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/fresh-products/in-stock")
                        .accept(MediaType.APPLICATION_JSON)
                        .header("Authorization", token))
                .andExpect(status().isForbidden());
    }
}
