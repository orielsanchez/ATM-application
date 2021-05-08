package edu.sdccd.cisc191.f.server;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * This class represent a bank account at an ATM.
 *
 */

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long ID;
    private long cardNumber; // This is the 16-digit card number
    private String PIN;   // This is a 4-digit PIN
    private int balance;        // represented in cents


    protected Account() { }

    /**
     * Create an account object by three arguments
     * @param cardNumber 16-digit card number
     * @param PIN account PIN
     * @param balance account balance
     */

    public Account(long cardNumber, String PIN, int balance) {
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

    public String getPIN() {
        return PIN;
    }

    public long getCardNumber() {
        return ID;
    }

    public synchronized int getBalance() {
        return balance;
    }

    public synchronized boolean deposit(int amount) {
        if (amount >= 0) {
            this.balance += amount;
            return true;
        } else {
            return false;
        }
    }

    public synchronized boolean withdraw(int amount) {
        if (balance <= amount) {
            return false;
        } else {
            balance -= amount;
            return true;
        }
    }


}