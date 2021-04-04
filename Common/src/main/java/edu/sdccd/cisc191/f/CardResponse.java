package edu.sdccd.cisc191.f;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CardResponse {
    private Integer id;
    private String name;
    private int cost;
    private String cardType;
    private String description;
    private int damageTarget;
    private int damageAll;
    private int armorSelf;

    @JsonIgnore
    private static final ObjectMapper objectMapper = new ObjectMapper();
    public static String toJSON(CardResponse player) throws Exception {
        return objectMapper.writeValueAsString(player);
    }

    public static CardResponse fromJSON(String input) throws Exception{
        return objectMapper.readValue(input, CardResponse.class);
    }

    protected CardResponse() {}

    public CardResponse(Integer id, String name, int cost, String cardType, String description, int damageTarget, int damageAll, int armorSelf) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.cardType = cardType;
        this.description = description;
        this.damageTarget = damageTarget;
        this.damageAll = damageAll;
        this.armorSelf = armorSelf;
    }

    @Override
    public String toString() {
        return String.format(
                "Player[id=%d, name='%s', cost='%d', cardType='%s', description='%s', damageTarget='%d', damageAll='%d', armorSelf='%d']",
                id, name, cost, cardType, description, damageTarget, damageAll, armorSelf);
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() { return name; }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getCardType() { return cardType; }

    public void setCardType(String CardType) {
        this.cardType = name;
    }

    public String getDescription() { return description; }

    public void setDescriptions(String description) {
        this.description = description;
    }

    public int getDamageTarget() {
        return damageTarget;
    }

    public void setDamageTarget(int damageTarget) {
        this.damageTarget = damageTarget;
    }

    public int getDamageAll() {
        return damageAll;
    }

    public void setDamageAll(int damageAll) { this.damageAll = damageAll; }

    public int getArmorSelf() {
        return armorSelf;
    }

    public void setArmorSelf(int armorSelf) {
        this.armorSelf = armorSelf;
    }

}
