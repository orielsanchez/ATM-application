package edu.sdccd.cisc191.f;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CardRequest {
    private Integer id;

    @JsonIgnore
    private static final ObjectMapper objectMapper = new ObjectMapper();
    public static String toJSON(CardRequest player) throws Exception {
        return objectMapper.writeValueAsString(player);
    }
    public static CardRequest fromJSON(String input) throws Exception{
        return objectMapper.readValue(input, CardRequest.class);
    }
    protected CardRequest() {}

    public CardRequest(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format(
                "Player[id=%d]",
                id);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
