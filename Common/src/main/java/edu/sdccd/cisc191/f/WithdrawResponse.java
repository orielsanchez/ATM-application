package edu.sdccd.cisc191.f;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * This class holds the structure to respond to withdrawRequests
 *
 * @author Oriel Sanchez
 * @author Eric Kim
 */
public class WithdrawResponse {
    private long id;
    private long cardNumber;
    private String PIN;
    private double balance;

    @JsonIgnore
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Creates the withdrawResponse object into json format to send within socket.
     *
     * @param  withdrawResponse withdrawResponse
     * @return The withdrawResponse as a string.
     */
    public static String toJSON(WithdrawResponse withdrawResponse) throws Exception {
        return objectMapper.writeValueAsString(withdrawResponse);
    }

    /**
     * Creates withdrawResponse object in json format from a string to send within socket.
     *
     * @param  input          reads an withdrawResponse formatted as a string
     * @return converts the input string into an withdrawResponse
     */
    public static WithdrawResponse fromJSON(String input) throws Exception {
        return objectMapper.readValue(input, WithdrawResponse.class);
    }

    protected WithdrawResponse() {

    }

    /**
     * Creates the withdrawResponse object that accepts 4 parameters.
     * This constructor is used to initialize the withdrawResponse object in order to respond to withdraw account requests.
     *
     * @param id          number to identify within database
     * @param cardNumber  number that acts as a bank account number
     * @param PIN         secure 4 digit pin
     * @param balance     amount of money to deposit within account
     */
    public WithdrawResponse(long id, long cardNumber, String PIN, double balance) {
        this.id = id;
        this.cardNumber = cardNumber;
        this.PIN = PIN;
        this.balance = balance;
    }

    /**
     * Gets the accountId
     *
     * @return The database id as a long variable
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the accountId
     *
     * @param id Accepts the id for the account
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets the cardNumber associated with the account
     *
     * @return The cardNumber as a long variable
     */
    public long getCardNumber() {
        return cardNumber;
    }

    /**
     * Sets the cardNumber associated with the account
     *
     * @param cardNumber Accepts the cardNumber for the account
     */
    public void setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
    }

    /**
     * Gets the PIN associated with the account
     *
     * @return The PIN as a String
     */
    public String getPIN() {
        return PIN;
    }

    /**
     * Sets the PIN associated with the account
     *
     * @param PIN Accepts the PIN as a string
     */
    public void setPIN(String PIN) {
        this.PIN = PIN;
    }

    /**
     * Gets the account balance
     *
     * @return The account balance as a double variable
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Sets the account balance
     *
     * @param balance Accepts the account balance as a double variable
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }
}
