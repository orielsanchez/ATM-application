package edu.sdccd.cisc191.f;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DepositResponse {
    private long id;
    private long cardNumber;
    private String PIN;
    private double balance;

    @JsonIgnore
    private static final ObjectMapper objectMapper = new ObjectMapper();
    public static String toJSON(DepositResponse depositResponse) throws Exception {
        return objectMapper.writeValueAsString(depositResponse);
    }

    public static DepositResponse fromJSON(String input) throws Exception {
        return objectMapper.readValue(input, DepositResponse.class);
    }

    protected DepositResponse() {

    }

    public DepositResponse(long id, long cardNumber, String PIN, double balance) {
        this.id = id;
        this.cardNumber = cardNumber;
        this.PIN = PIN;
        this.balance = balance;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getPIN() {
        return PIN;
    }

    public void setPIN(String PIN) {
        this.PIN = PIN;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
