package edu.sdccd.cisc191.f;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * @author Oriel Sanchez
 * @author Eric Kim
 */
public class TransferFundsRequest {
    private RequestType requestType;
    private long senderCardNumber;
    private double transferAmount;
    private long recipientCardNumber;

    @JsonIgnore
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String toJSON(TransferFundsRequest transferFundsRequest) throws Exception {
        return objectMapper.writeValueAsString(transferFundsRequest);
    }

    public static TransferFundsRequest fromJSON(String input) throws Exception {
        return objectMapper.readValue(input, TransferFundsRequest.class);
    }

    protected TransferFundsRequest() {}

    public TransferFundsRequest(RequestType requestType, long senderCardNumber, double transferAmount, long recipientCardNumber) {
        this.requestType = requestType;
        this.senderCardNumber = senderCardNumber;
        this.transferAmount = transferAmount;
        this.recipientCardNumber = recipientCardNumber;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
    }

    public long getSenderCardNumber() {
        return senderCardNumber;
    }

    public void setSenderCardNumber(long senderCardNumber) {
        this.senderCardNumber = senderCardNumber;
    }

    public double getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(double transferAmount) {
        this.transferAmount = transferAmount;
    }

    public long getRecipientCardNumber() {
        return recipientCardNumber;
    }

    public void setRecipientCardNumber(long recipientCardNumber) {
        this.recipientCardNumber = recipientCardNumber;
    }
}
