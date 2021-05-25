package edu.sdccd.cisc191.f;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * This class holds the structure for starting the withdraw requests within the socket.
 *
 * @author Oriel Sanchez
 * @author Eric Kim
 */
public class WithdrawRequest {
    private RequestType requestType;
    private long cardNumber;
    private double withdrawAmount;

    @JsonIgnore
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Creates the depositRequest object into json format to send within socket.
     *
     * @param  withdrawRequest withdrawRequest
     * @return withdrawRequest as a string
     */
    public static String toJSON(WithdrawRequest withdrawRequest) throws Exception {
        return objectMapper.writeValueAsString(withdrawRequest);
    }

    /**
     * Creates the withdrawRequest object into json format to send within socket.
     *
     * @param  input          reads an withdrawRequest formatted as a string
     * @return converts the input string into an withdrawRequest
     */
    public static WithdrawRequest fromJSON(String input) throws Exception {
        return objectMapper.readValue(input, WithdrawRequest.class);
    }

    protected WithdrawRequest(){}

    /**
     * Creates the withdrawRequest object that accepts 3 parameters.
     * This constructor is used to initialize the withdrawRequest object in order to create a withdraw request.
     *
     * @param requestType     one of four account request types: Account, Deposit, Withdraw, Transfer
     * @param cardNumber      cardNumber that acts as a bank account number
     * @param withdrawAmount  amount to deposit into bank account
     */
    public WithdrawRequest(RequestType requestType, long cardNumber, double withdrawAmount) {
        this.requestType = requestType;
        this.cardNumber = cardNumber;
        this.withdrawAmount = withdrawAmount;
    }

    /**
     * Gets the requestType
     *
     * @return The requestType as an object of requestType
     */
    public RequestType getRequestType() {
        return requestType;
    }

    /**
     * Sets the requestType
     */
    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
    }

    /**
     * Gets the card number from account
     *
     * @return the carbNumber as a long variable
     */
    public long getCardNumber() {
        return cardNumber;
    }

    /**
     * Sets the cardNumber for accountRequest
     */
    public void setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
    }

    /**
     * Gets the withdraw amount from withdrawRequest
     *
     * @return withdrawAmount as a double
     */
    public double getWithdrawAmount() {
        return withdrawAmount;
    }

    /**
     * Sets the withdrawAmount for withdrawRequest
     */
    public void setWithdrawAmount(double withdrawAmount) {
        this.withdrawAmount = withdrawAmount;
    }
}
