package edu.sdccd.cisc191.f;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;


public class DepositRequest {
    private RequestType requestType;
    private long cardNumber;
    private double depositAmount;

    @JsonIgnore
    private static final ObjectMapper objectMapper = new ObjectMapper();
    public static String toJSON(DepositRequest depositRequest) throws Exception {
        return objectMapper.writeValueAsString(depositRequest);
    }

    public static DepositRequest fromJSON(String input) throws Exception {
        return objectMapper.readValue(input, DepositRequest.class);
    }

    protected DepositRequest(){}

    public DepositRequest(RequestType requestType, long cardNumber, double depositAmount) {
        this.requestType = requestType;
        this.cardNumber = cardNumber;
        this.depositAmount = depositAmount;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public double getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(double depositAmount) {
        this.depositAmount = depositAmount;
    }
}
