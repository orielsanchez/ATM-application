package edu.sdccd.cisc191.f;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * This class represent a bank account at an ATM.
 *
 * @author Oriel Sanchez
 */

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ID;
    private long cardNumber;
    private String PIN;
    private double balance;


    protected Account() {
    }

    /**
     * Create an account object by three arguments.
     * This constructor is used when the ID of the account
     * has not been determined yet or is unknown.
     *
     * @param cardNumber 16-digit card number
     * @param PIN        account PIN
     * @param balance    account balance
     */
    public Account(long cardNumber, String PIN, double balance) {
        this.cardNumber = cardNumber;
        this.PIN = PIN;
        this.balance = balance;
    }

    /**
     * Creates an account object by four arguments.
     * This constructor is used when the ID of the account
     * is known.
     *
     * @param ID         the id of the account
     * @param cardNumber the 16-digit card number
     * @param PIN        the account PIN
     * @param balance    the account balance
     */
    public Account(long ID, long cardNumber, String PIN, double balance) {
        this.ID = ID;
        this.cardNumber = cardNumber;
        this.PIN = PIN;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "ID=" + ID +
                ", cardNumber=" + cardNumber +
                ", PIN='" + PIN + '\'' +
                ", balance=" + balance +
                '}';
    }

    /**
     * Gets the PIN of the account
     *
     * @return The 4-digit PIN as a string
     */
    public String getPIN() {
        return PIN;
    }

    /**
     * Gets the card number of the account
     *
     * @return the 16-digit card number as a long
     */
    public long getCardNumber() {
        return cardNumber;
    }

    /**
     * Gets the balance of the account.
     * This method is synchronized.
     *
     * @return the balance of the account
     */
    public synchronized double getBalance() {
        return balance;
    }

    /**
     * Gets the ID of the account.
     *
     * @return the ID of the account
     */
    public long getID() {
        return ID;
    }

    /**
     * Adds the amount argument to the balance if the amount is greater than or equal to zero.
     * This method is synchronized.
     *
     * @param amount the double to be added to the balance
     * @return true if the amount is added to the balance; false otherwise.
     */
    public synchronized boolean deposit(double amount) {
        if (amount >= 0) {
            this.balance += amount;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Subtracts the amount argument from the balance if the balance is greater than the amount.
     * This method is synchronized.
     *
     * @param amount the double to be subtracted from the balance.
     * @return true if the amount is subtracted from the balance; false otherwise.
     */
    public synchronized boolean withdraw(double amount) {
        if (balance < amount) {
            return false;
        } else {
            balance -= amount;
            return true;
        }
    }
}