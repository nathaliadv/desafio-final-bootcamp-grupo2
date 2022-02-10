package com.mercadolibre.desafiofinalbootcampgrupo2.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.desafiofinalbootcampgrupo2.dto.VehicleDTO;
import com.mercadolibre.desafiofinalbootcampgrupo2.dto.VehicleUpdateDTO;
import com.mercadolibre.desafiofinalbootcampgrupo2.utils.ApplicationConfigTest;
import com.mercadolibre.desafiofinalbootcampgrupo2.utils.TypeOfUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.mercadolibre.desafiofinalbootcampgrupo2.utils.Factory.*;
import static com.mercadolibre.desafiofinalbootcampgrupo2.utils.TokenGenerator.getUserToken;

public class VehicleControllerTest extends ApplicationConfigTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;


    @Test
    public void shouldRegistervehicle() throws Exception {
        VehicleDTO VehicleDTO = generateListOfValidVehicleCreateDTO();
        String payload = mapper.writeValueAsString(VehicleDTO);
        String token = getUserToken(mockMvc, TypeOfUser.REPRESENTATIVE);

        mockMvc.perform(MockMvcRequestBuilders.post("/fleet/create/")
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
    public void shouldShowVehicleById() throws Exception {
        String token = getUserToken(mockMvc, TypeOfUser.REPRESENTATIVE);

        mockMvc.perform(MockMvcRequestBuilders.get("/fleet/detailById/?vehicleId=2")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", token)
                .content(""))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andReturn();
    }

    @Test
    public void shouldShowVehicleList() throws Exception {
        String token = getUserToken(mockMvc, TypeOfUser.REPRESENTATIVE);

        mockMvc.perform(MockMvcRequestBuilders.get("/fleet/list")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", token)
                .content(""))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andReturn();
    }

    @Test
    public void shouldShowVehicleListByModel() throws Exception {
        String token = getUserToken(mockMvc, TypeOfUser.REPRESENTATIVE);

        mockMvc.perform(MockMvcRequestBuilders.get("/fleet/detailAllByMaintenanceDate/?vehicleModel=mercedes")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", token)
                .content(""))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andReturn();
    }

    @Test
    public void shouldShowVehicleListByPlate() throws Exception {
        String token = getUserToken(mockMvc, TypeOfUser.REPRESENTATIVE);

        mockMvc.perform(MockMvcRequestBuilders.get("/fleet/detailByPlate/?vehiclePlate=Teste Placa")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", token)
                .content(""))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andReturn();
    }

    @Test
    public void shouldUpdateVehicle() throws Exception {
        VehicleUpdateDTO vehicleUpdateDTO = generateListOfValidVehicleUpdateDTO();
        String payload = mapper.writeValueAsString(vehicleUpdateDTO);
        String token = getUserToken(mockMvc, TypeOfUser.REPRESENTATIVE);

        mockMvc.perform(MockMvcRequestBuilders.put("/fleet/update")
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
    public void shouldDeleteVehicle() throws Exception {
        String token = getUserToken(mockMvc, TypeOfUser.REPRESENTATIVE);
        mockMvc.perform(MockMvcRequestBuilders.delete("/fleet/delete/?vehicleId=10")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", token)
                .content(""))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists())
                .andReturn();
    }
}
