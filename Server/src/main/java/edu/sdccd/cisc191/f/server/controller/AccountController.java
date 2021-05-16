package edu.sdccd.cisc191.f.server.controller;

import edu.sdccd.cisc191.f.server.AccountUtils;
import edu.sdccd.cisc191.f.server.Account;
import edu.sdccd.cisc191.f.server.Main;

import java.sql.SQLException;

public class AccountController {

    public static void updateAccount(Account account) {
        Main.database.update(String.valueOf(account.getCardNumber()), account.getBalance());
    }



    public static boolean transferFunds(Account account) {
        boolean keepGoing = true;
        String amt;
        String recipientCardNumber = null;
        double amount = 0;
        while (keepGoing) {

            System.out.println("Enter recipient's card number: ");
            recipientCardNumber = Main.in.next();

            boolean isRecipientCardNumberValid = false;
            while (!isRecipientCardNumberValid) {
                while (recipientCardNumber.length() != 16) {
                    System.out.println("Probably you made a mistake in the card number. Please try again!");
                    System.out.println("Enter recipient's card number: ");
                    recipientCardNumber = Main.in.next();
                }
                String numForCheck = recipientCardNumber.substring(0, 15);
                String checkNum = numForCheck + AccountUtils.getLuhnNum(numForCheck);
                if (recipientCardNumber.equals(String.valueOf(account.getCardNumber()))) {
                    System.out.println("You can't transfer money to the same account!");
                    System.out.println("Enter recipient's card number: ");
                    recipientCardNumber = Main.in.next();
                } else if (!recipientCardNumber.equals(checkNum)) {
                    System.out.println("You probably made a mistake in the card number. Please try again!");
                    System.out.println("Enter recipient's card number: ");
                    recipientCardNumber = Main.in.next();
                } else {
                    isRecipientCardNumberValid = true;
                }
            }

            Account recipientAccount = Main.database.getAccount(Long.parseLong(recipientCardNumber));
            if (recipientAccount == null) {
                System.out.println("Such a card does not exist.");
            } else {
                System.out.println("Enter amount to transfer from your account: ");

                try {
                    amt = Main.in.next();
                    if (Double.parseDouble(amt) > account.getBalance()) {
                        System.out.println("Not enough money!");
                        return false;
                    }

                    amount = Double.parseDouble(amt);
                } catch (NumberFormatException e) {
                    e.getMessage();
                }


                keepGoing = false;
            }
        }

        try {
            Main.database.transfer(String.valueOf(account.getCardNumber()), recipientCardNumber, amount);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static void closeAccount(Account account) {
        Main.database.delete(String.valueOf(account.getCardNumber()));
    }

    public static void createAccount() {
        //Create the account
        Account account = AccountUtils.createAccount();

        //Add the account to the bank database
        Main.database.insert(String.valueOf(account.getCardNumber()), account.getPIN(), account.getBalance());

        //Print account information
        System.out.println("Your card has been created!");
        System.out.println("Your card number:");
        System.out.println(account.getCardNumber());
        System.out.println("\nYour card PIN:");
        System.out.println(account.getPIN());

        System.out.println();
        account = null;
        Main.mainMenu.show();
    }
}
