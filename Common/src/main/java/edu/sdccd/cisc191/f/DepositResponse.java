package edu.sdccd.cisc191.f;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * This class holds the structure to respond to depositRequests
 *
 * @author Oriel Sanchez
 * @author Eric Kim
 */
public class DepositResponse {
    private long id;
    private long cardNumber;
    private String PIN;
    private double balance;

    @JsonIgnore
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Creates the depositResponse object into json format to send within socket.
     *
     * @param  depositResponse depositResponse
     * @return The depositResponse as a string.
     */
    public static String toJSON(DepositResponse depositResponse) throws Exception {
        return objectMapper.writeValueAsString(depositResponse);
    }

    /**
     * Creates the depositResponse object in json format from a string to send within socket.
     *
     * @param  input          reads an depositResponse formatted as a string
     * @return converts the input string into an depositResponse
     */
    public static DepositResponse fromJSON(String input) throws Exception {
        return objectMapper.readValue(input, DepositResponse.class);
    }

    protected DepositResponse() {

    }

    /**
     * Creates the depositResponse object that accepts 4 parameters.
     * This constructor is used to initialize the depositResponse object in order to respond to deposit account requests.
     *
     * @param id          number to identify within database
     * @param cardNumber  number that acts as a bank account number
     * @param PIN         secure 4 digit pin
     * @param balance     amount of money to deposit within account
     */
    public DepositResponse(long id, long cardNumber, String PIN, double balance) {
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
