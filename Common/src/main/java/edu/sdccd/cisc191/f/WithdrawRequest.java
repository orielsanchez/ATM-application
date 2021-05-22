package edu.sdccd.cisc191.f;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;

public class WithdrawRequest {
    private RequestType requestType;
    private long cardNumber;
    private double withdrawAmount;

    @JsonIgnore
    private static final ObjectMapper objectMapper = new ObjectMapper();
    public static String toJSON(WithdrawRequest withdrawRequest) throws Exception {
        return objectMapper.writeValueAsString(withdrawRequest);
    }

    public static WithdrawRequest fromJSON(String input) throws Exception {
        return objectMapper.readValue(input, WithdrawRequest.class);
    }

    protected WithdrawRequest(){}

    public WithdrawRequest(RequestType requestType, long cardNumber, double withdrawAmount) {
        this.requestType = requestType;
        this.cardNumber = cardNumber;
        this.withdrawAmount = withdrawAmount;
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

    public double getWithdrawAmount() {
        return withdrawAmount;
    }

    public void setWithdrawAmount(double withdrawAmount) {
        this.withdrawAmount = withdrawAmount;
    }
}
