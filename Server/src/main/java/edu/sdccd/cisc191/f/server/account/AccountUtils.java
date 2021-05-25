package edu.sdccd.cisc191.f.server.account;

import edu.sdccd.cisc191.f.Account;

import java.util.Random;

/**
 * This class is a utility class to create Account objects,
 * generate 4 digit PINs. It contains the Luhn Algorithm,
 * which is used to check if a card number is valid via a checksum.
 * <p>
 * This class implements the Singleton design pattern.
 *
 * @author Oriel Sanchez
 * @author Jonathan Plata
 * @author Stanley Schwarz Jr.
 */

public class AccountUtils {

    // Creates an object of AccountUtils
    private static final AccountUtils accountUtils = new AccountUtils();

    // makes the constructor private to prevent instantiation
    private AccountUtils() {
    }

    /**
     * Gets the only object available
     *
     * @return accountUtils instance
     */
    public static AccountUtils getInstance() {
        return accountUtils;
    }

    // generates a random 4 digit PIN
    protected static String generatePIN() {
        Random random = new Random();
        int pin = random.nextInt(10000);
        String temp = Integer.toString(pin);
        if (temp.length() == 4) {
            return temp;
        }

        return "0".repeat(Math.max(0, 4 - temp.length())) + temp;
    }

    /**
     * Creates a new, empty account
     *
     * @return a new account object
     */
    public static Account createAccount() {
        long cardNumber = Long.parseLong(generateNumber());
        String PIN = generatePIN();
        return new Account(cardNumber, PIN, 0);
    }


    // Generates a random 16-digit number (as a string) that is valid with the Luhn Algorithm
    protected static String generateNumber() {
        Random random = new Random();
        String num = "400000" + (random.nextInt(899999999) + 100000000);
        return num + getLuhnNum(num);
    }

    /**
     * Uses the Luhn Algorithm to generate a checksum digit
     *
     * @param numForCheck the number we want to generate the checksum for
     * @return A valid card number as a string
     */
    public static String getLuhnNum(String numForCheck) {
        numForCheck += "0";
        int oddSum = 0;
        int evenSum = 0;
        for (int x = numForCheck.length() - 1; x >= 0; x--) {
            int currentNum = Character.getNumericValue(numForCheck.charAt(x));
            if (x % 2 != 0) {
                oddSum += currentNum;
            } else {
                if (currentNum * 2 > 9) {
                    evenSum += currentNum * 2 - 9;
                } else {
                    evenSum += currentNum * 2;
                }
            }
        }
        int controlSum = oddSum + evenSum;
        return String.valueOf(controlSum % 10 == 0 ? 0 : 10 - controlSum % 10);
    }
}
