package edu.sdccd.cisc191.f;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.sdccd.cisc191.f.cards.Card;
import edu.sdccd.cisc191.f.cards.Deck;

public class PlayerRequest {
    private Integer id;

    @JsonIgnore
    private static final ObjectMapper objectMapper = new ObjectMapper();
    public static String toJSON(PlayerRequest player) throws Exception {
        return objectMapper.writeValueAsString(player);
    }
    public static PlayerRequest fromJSON(String input) throws Exception{
        return objectMapper.readValue(input, PlayerRequest.class);
    }
    protected PlayerRequest() {}

    public PlayerRequest(Integer id) {
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
