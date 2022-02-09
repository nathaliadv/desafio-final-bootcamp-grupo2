package com.mercadolibre.desafiofinalbootcampgrupo2.utils;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public class TokenGenerator {
    private static final String representative = "{\"user\": \"fulano@email.com\",\"password\": \"albertinho123\"}";
    private static final String buyer = "{\"user\": \"rod@email.com\",\"password\": \"nat123\"}";
    private static final String seller = "{\"user\": \"aderson@email.com\",\"password\": \"nat123\"}";

    public static String getUserToken(MockMvc mockMvc, TypeOfUser typeOfUser) throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.post("/auth")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getUserPayload(typeOfUser)))
                .andReturn().getResponse().getContentAsString();

        return convertTokenToString(response);
    }

    private static String convertTokenToString(String token) throws JSONException {
        JSONObject jsonObject = new JSONObject(token);
        String typeToken = jsonObject.get("tipo").toString();
        String tokenToken = jsonObject.get("token").toString();

        return typeToken + " " + tokenToken;
    }

    private static String getUserPayload(TypeOfUser typeOfUser) {
        switch (typeOfUser) {
            case REPRESENTATIVE:
                return representative;
            case BUYER:
                return buyer;
            case SELLER:
                return seller;
            default:
                return "";
        }
    }
}
