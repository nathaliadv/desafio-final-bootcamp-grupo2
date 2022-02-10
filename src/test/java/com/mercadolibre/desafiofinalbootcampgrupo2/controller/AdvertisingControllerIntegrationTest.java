package com.mercadolibre.desafiofinalbootcampgrupo2.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.desafiofinalbootcampgrupo2.dto.AdvertisingDTO;
import com.mercadolibre.desafiofinalbootcampgrupo2.utils.ApplicationConfigTest;
import com.mercadolibre.desafiofinalbootcampgrupo2.utils.TypeOfUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.mercadolibre.desafiofinalbootcampgrupo2.utils.Factory.generateValidAdvertisingDTO;
import static com.mercadolibre.desafiofinalbootcampgrupo2.utils.TokenGenerator.getUserToken;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class AdvertisingControllerIntegrationTest extends ApplicationConfigTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    void shouldNotGetAllAdvertisingWhenUserIsNotAuthorize() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/fresh-products/in-stock")
                        .accept(MediaType.APPLICATION_JSON)
                        .header("Authorization", ""))
                .andExpect(status().isForbidden());
    }

    @Test
    void shouldReturnOKWhenRepresentativeIsAuthorizedAndListOfProductIsNotEmptyToGetAllAdvertising() throws Exception {
        String token = getUserToken(mockMvc, TypeOfUser.REPRESENTATIVE);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/fresh-products/in-stock")
                        .accept(MediaType.APPLICATION_JSON)
                        .header("Authorization", token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0].description").exists())
                .andExpect(jsonPath("[0].productName").exists())
                .andExpect(jsonPath("[0].productType").exists())
                .andExpect(jsonPath("[0].price").exists())
                .andExpect(jsonPath("[0].freeShipping").exists())
                .andReturn();
    }

    @Test
    void shouldReturnForbiddenWhenBuyerIsNotAuthorizedToGetAllAdvertising() throws Exception {
        String token = getUserToken(mockMvc, TypeOfUser.BUYER);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/fresh-products/in-stock")
                        .accept(MediaType.APPLICATION_JSON)
                        .header("Authorization", token))
                .andExpect(status().isForbidden());
    }

    @Test
    void shouldReturnForbiddenWhenSellerIsNotAuthorizedToGetAllAdvertising() throws Exception {
        String token = getUserToken(mockMvc, TypeOfUser.SELLER);

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

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/fresh-products/in-stock/by-type")
                        .queryParam("type", type)
                        .accept(MediaType.APPLICATION_JSON)
                        .header("Authorization", token))
                .andExpect(status().isForbidden());
    }

    @Test
    void shouldReturnOKWhenRepresentativeIsAuthorizedToGetProductByType() throws Exception {
        String token = getUserToken(mockMvc, TypeOfUser.REPRESENTATIVE);
        String type = "FS";

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/fresh-products/in-stock/by-type")
                        .queryParam("type", type)
                        .accept(MediaType.APPLICATION_JSON)
                        .header("Authorization", token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0].description").exists())
                .andExpect(jsonPath("[0].productName").exists())
                .andExpect(jsonPath("[0].productType").exists())
                .andExpect(jsonPath("[0].price").exists())
                .andExpect(jsonPath("[0].freeShipping").exists())
                .andReturn();
    }

    @Test
    void shouldReturnOKWhenSellerIsAuthorizedToGetAdvertisingById() throws Exception {
        String token = getUserToken(mockMvc, TypeOfUser.SELLER);
        Long existingId = 2L;

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/fresh-products/advertisings/{id}", existingId)
                        .accept(MediaType.APPLICATION_JSON)
                        .header("Authorization", token))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.productName").exists())
                .andExpect(jsonPath("$.productType").exists())
                .andExpect(jsonPath("$.description").exists())
                .andExpect(jsonPath("$.price").exists())
                .andExpect(jsonPath("$.freeShipping").exists())
                .andReturn();
    }

    @Test
    void shouldReturnNotFoundWhenSellerIsAuthorizedToGetAdvertisingByNonExistingId() throws Exception {
        String token = getUserToken(mockMvc, TypeOfUser.SELLER);
        Long existingId = 1100101001L;

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/fresh-products/advertisings/{id}", existingId)
                        .accept(MediaType.APPLICATION_JSON)
                        .header("Authorization", token))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$").value("Advertising not exists in the Database"))
                .andReturn();
    }

    @Test
    void shouldReturnForbidenWhenSellerIsNotAuthorizedToGetAdvertisingById() throws Exception {
        String token = getUserToken(mockMvc, TypeOfUser.REPRESENTATIVE);
        Long existingId = 1L;

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/fresh-products/advertisings/{id}", existingId)
                        .accept(MediaType.APPLICATION_JSON)
                        .header("Authorization", token))
                .andExpect(status().isForbidden())
                .andReturn();
    }

    @Test
    void shouldReturnOKWhenSellerIsAuthorizedToGetAdvertisingByFreeShipping() throws Exception {
        String token = getUserToken(mockMvc, TypeOfUser.SELLER);
        boolean isFreeShipping = true;

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/fresh-products/advertisings")
                        .queryParam("isFreeShipping", String.valueOf(isFreeShipping))
                        .accept(MediaType.APPLICATION_JSON)
                        .header("Authorization", token))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("[0].id").exists())
                .andExpect(jsonPath("[0].freeShipping").value(isFreeShipping))
                .andReturn();
    }

    @Test
    void shouldReturnForbidenWhenSellerIsNotAuthorizedToGetAdvertisingByFreeShipping() throws Exception {
        String token = getUserToken(mockMvc, TypeOfUser.REPRESENTATIVE);
        boolean isFreeShipping = true;

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/fresh-products/advertisings")
                        .queryParam("isFreeShipping", String.valueOf(isFreeShipping))
                        .accept(MediaType.APPLICATION_JSON)
                        .header("Authorization", token))
                .andExpect(status().isForbidden())
                .andReturn();
    }

    @Test
    public void registerAdvertisingShouldSaveAnAdvertisingWhenCorrectDataAndSellerLogged() throws Exception {
        AdvertisingDTO validAdvertisingDTO = generateValidAdvertisingDTO();
        String payload = mapper.writeValueAsString(validAdvertisingDTO);
        String token = getUserToken(mockMvc, TypeOfUser.SELLER);

        mockMvc.perform(MockMvcRequestBuilders.post("/fresh-products/advertisings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token)
                        .content(payload))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id").exists())
                .andReturn();
    }

    @Test
    public void registerAdvertisingShoulReturnBadRequestWhenIncorrectDataAndSellerLogged() throws Exception {
        AdvertisingDTO invalidAdvertisingDTO = generateValidAdvertisingDTO();
        invalidAdvertisingDTO.setFreeShipping(null);
        invalidAdvertisingDTO.setProductCode(2189821912L);

        String payload = mapper.writeValueAsString(invalidAdvertisingDTO);
        String token = getUserToken(mockMvc, TypeOfUser.SELLER);

        mockMvc.perform(MockMvcRequestBuilders.post("/fresh-products/advertisings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token)
                        .content(payload))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$").exists())
                .andReturn();
    }

    @Test
    public void registerAdvertisingShouldReturnForbidenWhenCorrectDataAndSellerIsNotLogged() throws Exception {
        AdvertisingDTO validAdvertisingDTO = generateValidAdvertisingDTO();
        String payload = mapper.writeValueAsString(validAdvertisingDTO);
        String token = getUserToken(mockMvc, TypeOfUser.REPRESENTATIVE);

        mockMvc.perform(MockMvcRequestBuilders.post("/fresh-products/advertisings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token)
                        .content(payload))
                .andExpect(status().isForbidden())
                .andReturn();
    }

    @Test
    public void editAdvertisingShouldEditAdvertisingWhenCorrectIdAndSellerIsLogged() throws Exception {
        AdvertisingDTO validAdvertisingDTO = generateValidAdvertisingDTO();
        String payload = mapper.writeValueAsString(validAdvertisingDTO);
        Long existingId = 1L;
        String token = getUserToken(mockMvc, TypeOfUser.SELLER);

        mockMvc.perform(MockMvcRequestBuilders.put("/fresh-products/advertisings/{id}", existingId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token)
                        .content(payload))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id").exists())
                .andReturn();
    }

    @Test
    public void editAdvertisingShouldReturnNotFoundWhenIncorrectIdAndSellerIsLogged() throws Exception {
        AdvertisingDTO invalidAdvertisingDTO = generateValidAdvertisingDTO();
        Long nonExistingId = 128921891282L;
        String payload = mapper.writeValueAsString(invalidAdvertisingDTO);
        String token = getUserToken(mockMvc, TypeOfUser.SELLER);

        mockMvc.perform(MockMvcRequestBuilders.put("/fresh-products/advertisings/{id}", nonExistingId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token)
                        .content(payload))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$").exists())
                .andReturn();
    }

    @Test
    public void editAdvertisingShouldReturnForbidenWhenCorrectDataAndSellerIsNotLogged() throws Exception {
        AdvertisingDTO validAdvertisingDTO = generateValidAdvertisingDTO();
        String payload = mapper.writeValueAsString(validAdvertisingDTO);
        Long existingId = 1L;
        String token = getUserToken(mockMvc, TypeOfUser.REPRESENTATIVE);

        mockMvc.perform(MockMvcRequestBuilders.put("/fresh-products/advertisings/{id}", existingId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token)
                        .content(payload))
                .andExpect(status().isForbidden())
                .andReturn();
    }

    @Test
    public void deleteAdvertisingShouldDeleteAdvertisingWhenCorrectIdAndSellerIsLogged() throws Exception {
        Long existingId = 3L;
        String token = getUserToken(mockMvc, TypeOfUser.SELLER);

        mockMvc.perform(MockMvcRequestBuilders.delete("/fresh-products/advertisings/{id}", existingId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void deleteAdvertisingShouldReturnNotFoundWhenIncorrectIdAndSellerIsLogged() throws Exception {
        Long nonExistingId = 128921891282L;
        String token = getUserToken(mockMvc, TypeOfUser.SELLER);

        mockMvc.perform(MockMvcRequestBuilders.delete("/fresh-products/advertisings/{id}", nonExistingId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$").exists())
                .andReturn();
    }

    @Test
    public void deleteAdvertisingShouldReturnForbidenWhenCorrectDataAndSellerIsNotLogged() throws Exception {
        Long existingId = 1L;
        String token = getUserToken(mockMvc, TypeOfUser.REPRESENTATIVE);

        mockMvc.perform(MockMvcRequestBuilders.delete("/fresh-products/advertisings/{id}", existingId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token))
                .andExpect(status().isForbidden())
                .andReturn();
    }
}