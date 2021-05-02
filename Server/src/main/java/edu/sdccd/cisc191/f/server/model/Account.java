package edu.sdccd.cisc191.f.server.model;


import java.util.Random;

/**
 * This class represent a bank account at an ATM.
 *
 */

public class Account {
    private final long ID;      // This is the 16-digit card number
    private final String PIN;   // This is a 4-digit PIN
    private int balance;        // represented in cents

    /**
     * Create an account object by three arguments
     * @param ID card id
     * @param PIN account PIN
     * @param balance account balance
     */
    public Account(long ID, String PIN, int balance) {
        this.ID = ID;
        this.PIN = PIN;
        this.balance = balance;
    }

    public String getPIN() {
        return PIN;
    }

    public long getID() {
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