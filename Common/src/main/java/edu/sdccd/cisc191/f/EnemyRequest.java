package edu.sdccd.cisc191.f;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class EnemyRequest {
    private Integer id;

    @JsonIgnore
    private static final ObjectMapper objectMapper = new ObjectMapper();
    public static String toJSON(EnemyRequest enemy) throws Exception{
        return objectMapper.writeValueAsString(enemy);
    }

    public static EnemyRequest fromJSON(String input) throws Exception{
        return objectMapper.readValue(input, EnemyRequest.class);
    }

    protected EnemyRequest() {}

    public EnemyRequest(Integer id) {this.id = id;}

    @Override
    public String toString() {
        return String.format(
                "Enemy[id=%d]",
                id);
    }

    public Integer getId() {return id;}

    public void setId(Integer id) {this.id = id;}
}
