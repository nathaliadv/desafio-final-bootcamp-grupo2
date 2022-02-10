package com.mercadolibre.desafiofinalbootcampgrupo2.controller;

import com.mercadolibre.desafiofinalbootcampgrupo2.controller.advices.dao.AdvertisingDAO;
import com.mercadolibre.desafiofinalbootcampgrupo2.controller.advices.dao.ProductTypeDAO;
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
import static java.lang.String.format;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AdvertisingControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AdvertisingDAO advertisingDAO;

    @MockBean
    private ProductTypeDAO productTypeDAO;

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

        List<AdvertisingDAO.AdvertisingDTO> advertising = new ArrayList<>();
        advertising.add(new AdvertisingDAO.AdvertisingDTO() {
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

        System.out.println(advertising);
        Mockito.when(advertisingDAO.findAllInStock()).thenReturn(advertising);

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

    @Test
    void shouldReturnForbiddenWhenBuyerIsNotAuthorizedToGetProductByType() throws Exception {
        String token = getUserToken(mockMvc, TypeOfUser.BUYER);
        String type = "FS";
        Mockito.when(productTypeDAO.advertisingList(type)).thenReturn(new ArrayList<>());

        mockMvc.perform(MockMvcRequestBuilders
                        .get(format("/fresh-products/in-stock/by-type?type=%s", type))
                        .accept(MediaType.APPLICATION_JSON)
                        .header("Authorization", token))
                .andExpect(status().isForbidden());
    }

    @Test
    void shouldReturnNotFundWhenRepresentativeIsAuthorizedAndListOfProductIsEmptyToGetProductByType() throws Exception {
        String token = getUserToken(mockMvc, TypeOfUser.REPRESENTATIVE);
        String type = "FS";
        Mockito.when(productTypeDAO.advertisingList(type)).thenReturn(new ArrayList<>());

        mockMvc.perform(MockMvcRequestBuilders
                        .get(format("/fresh-products/in-stock/by-type?type=%s", type))
                        .accept(MediaType.APPLICATION_JSON)
                        .header("Authorization", token))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnOKWhenRepresentativeIsAuthorizedToGetProductByType() throws Exception {
        String token = getUserToken(mockMvc, TypeOfUser.REPRESENTATIVE);
        String type = "FRESH";
        List<ProductTypeDAO.AdvertisingDTO> advertising = new ArrayList<>();
        advertising.add(new ProductTypeDAO.AdvertisingDTO() {
            @Override
            public String getName() {
                return "Melancia";
            }

            @Override
            public String getDescription() {
                return "Melancia gostosinha nham nham";
            }

            @Override
            public BigDecimal getPrice() {
                return new BigDecimal("13.50");
            }

            @Override
            public Integer getQuantity() {
                return 10;
            }
        });

        Mockito.when(productTypeDAO.advertisingList(type)).thenReturn(advertising);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .get(format("/fresh-products/in-stock/by-type?type=%s", "FS"))
                        .accept(MediaType.APPLICATION_JSON)
                        .header("Authorization", token))
                .andExpect(status().isOk())
                .andReturn();

        String expected = "Melancia gostosinha nham nham";
        Assertions.assertTrue(result.getResponse().getContentAsString().contains(expected));
    }
}
