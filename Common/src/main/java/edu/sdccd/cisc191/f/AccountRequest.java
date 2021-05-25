package edu.sdccd.cisc191.f;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * This class holds the structure for starting the account requests within the socket.
 *
 * @author Oriel Sanchez
 * @author Eric Kim
 */

public class AccountRequest {
    private RequestType requestType;
    private long cardNumber;
    private String PIN;

    @JsonIgnore
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Creates the accountRequest object into json format to send within socket.
     *
     * @param  accountRequest accountRequest
     * @return accountRequest as a string
     */
    public static String toJSON(AccountRequest accountRequest) throws Exception {
        return objectMapper.writeValueAsString(accountRequest);
    }

    /**
     * Creates the accountRequest object into json format to send within socket.
     *
     * @param  input          reads an accountRequest formatted as a string
     * @return converts the input string into an accountRequest
     */
    public static AccountRequest fromJSON(String input) throws Exception {
        return objectMapper.readValue(input, AccountRequest.class);
    }

    protected AccountRequest() {

    }

    /**
     * Creates the accountRequest object that accepts 3 parameters.
     * This constructor is used to initialize the accountRequest object in order to request account data.
     *
     * @param requestType one of four account request types: Account, Deposit, Withdraw, Transfer
     * @param cardNumber  cardnumber that acts as a bank account number
     * @param PIN         secure 4 digit pin
     */
    public AccountRequest(RequestType requestType, long cardNumber, String PIN) {
        this.requestType = requestType;
        this.cardNumber = cardNumber;
        this.PIN = PIN;
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
     * Gets the pin from accountRequest
     *
     * @return the pin as a string
     */
    public String getPIN() {
        return PIN;
    }

    /**
     * Sets the PIN for accountRequest
     */
    public void setPIN(String PIN) {
        this.PIN = PIN;
    }
}
