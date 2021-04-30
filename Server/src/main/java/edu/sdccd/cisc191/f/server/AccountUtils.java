package edu.sdccd.cisc191.f.server;

import edu.sdccd.cisc191.f.server.model.Account;
import java.util.Random;

public class AccountUtils {

    private static final AccountUtils accountUtils = new AccountUtils();

    private AccountUtils() {

    }

    public static AccountUtils getInstance() {
        return accountUtils;
    }

    protected static String generatePIN() {
        Random random = new Random();
        int pin = random.nextInt(10000);
        String temp = Integer.toString(pin);
        if (temp.length() == 4) {
            return temp;
        }

        return "0".repeat(Math.max(0, 4 - temp.length())) + temp;
    }

    public static Account createAccount() {
        long ID = Long.parseLong(generateNumber());
        String PIN = generatePIN();
        return new Account(ID, PIN, 0);
    }


    protected static String generateNumber() {
        Random random = new Random();
        String num = "400000" + (random.nextInt(899999999) + 100000000);
        return num + getLuhnNum(num);
    }

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
