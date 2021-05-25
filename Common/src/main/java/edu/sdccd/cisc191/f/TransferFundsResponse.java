package edu.sdccd.cisc191.f;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 *
 * @author Oriel Sanchez
 * @author Eric Kim
 */
public class TransferFundsResponse {
    private boolean transferSuccessful;
    private double balance;

    @JsonIgnore
    private static final ObjectMapper objectMapper = new ObjectMapper();
    public static String toJSON(TransferFundsResponse transferFundsResponse) throws Exception {
        return objectMapper.writeValueAsString(transferFundsResponse);
    }

    public static TransferFundsResponse fromJSON(String input) throws Exception {
        return objectMapper.readValue(input, TransferFundsResponse.class);
    }

    protected TransferFundsResponse() {}

    public TransferFundsResponse(boolean transferSuccessful, double balance) {
        this.transferSuccessful = transferSuccessful;
        this.balance = balance;
    }

    public boolean isTransferSuccessful() {
        return transferSuccessful;
    }

    public void setTransferSuccessful(boolean transferSuccessful) {
        this.transferSuccessful = transferSuccessful;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
