package edu.sdccd.cisc191.f;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * This class holds the structure for starting the deposit requests within the socket.
 *
 * @author Oriel Sanchez
 * @author Eric Kim
 */
public class DepositRequest {
    private RequestType requestType;
    private long cardNumber;
    private double depositAmount;

    @JsonIgnore
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Creates the depositRequest object into json format to send within socket.
     *
     * @param  depositRequest depositRequest
     * @return depositRequest as a string
     */
    public static String toJSON(DepositRequest depositRequest) throws Exception {
        return objectMapper.writeValueAsString(depositRequest);
    }

    /**
     * Creates the depositRequest object into json format to send within socket.
     *
     * @param  input          reads an depositRequest formatted as a string
     * @return converts the input string into an depositRequest
     */
    public static DepositRequest fromJSON(String input) throws Exception {
        return objectMapper.readValue(input, DepositRequest.class);
    }

    protected DepositRequest(){}

    /**
     * Creates the depsitRequest object that accepts 3 parameters.
     * This constructor is used to initialize the depositRequest object in order to create a deposit request.
     *
     * @param requestType    one of four account request types: Account, Deposit, Withdraw, Transfer
     * @param cardNumber     cardnumber that acts as a bank account number
     * @param depositAmount  amount to deposit into bank account
     */
    public DepositRequest(RequestType requestType, long cardNumber, double depositAmount) {
        this.requestType = requestType;
        this.cardNumber = cardNumber;
        this.depositAmount = depositAmount;
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
     * Gets the deposit amount from depositRequest
     *
     * @return depositAmount as a double
     */
    public double getDepositAmount() {
        return depositAmount;
    }

    /**
     * Sets the depositAmount for depositRequest
     */
    public void setDepositAmount(double depositAmount) {
        this.depositAmount = depositAmount;
    }
}
