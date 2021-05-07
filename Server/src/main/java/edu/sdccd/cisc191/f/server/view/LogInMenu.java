package edu.sdccd.cisc191.f.server.view;

import edu.sdccd.cisc191.f.Account;
import edu.sdccd.cisc191.f.server.Main;
import edu.sdccd.cisc191.f.server.controller.AccountController;

public class LogInMenu {
    private Account logInAccount = null;

    public LogInMenu(Account account) {
        logInAccount = account;
    }

    public void displayMenu() {
        System.out.println("1. Show Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Transfer Funds");
        System.out.println("5. Close Account");
        System.out.println("6. Log out");
        System.out.println("0. Exit");
        System.out.println();
        choose(Main.in.nextInt());
    }

    public void choose(int command) {
        System.out.println();
        switch (command) {
            case 1:
                System.out.println("Balance: " + logInAccount.getBalance());
                System.out.println();
                displayMenu();
                break;
            case 2:
                System.out.println("How much would you like to deposit?");
                int depositAmount = -1;
                while (depositAmount < 0) {
                    depositAmount = Main.in.nextInt();
                }
                logInAccount.deposit(depositAmount);
                AccountController.updateAccount(logInAccount);
                System.out.println("\nFunds successfully added!");
                System.out.println("Your balance is now " + logInAccount.getBalance() + "\n");
                displayMenu();
                break;

            case 3:
                System.out.println("How much would you like to withdraw?");
                int withdrawalAmount = -1;
                while (withdrawalAmount < 0) {
                    withdrawalAmount = Main.in.nextInt();
                }

                logInAccount.withdraw(withdrawalAmount);
                AccountController.updateAccount(logInAccount);
                System.out.println("\nWithdrawal successful!");
                System.out.println("Your balance is now " + logInAccount.getBalance() + "\n");
                displayMenu();
                break;

            case 4:
                boolean fundsTransferred = AccountController.transferFunds(logInAccount);
                if (fundsTransferred) {
                    //TODO Parse long from String
                    logInAccount = Main.database.getAccount(logInAccount.getCardNumber());
                    System.out.println("Funds have been transferred!");
                }
                displayMenu();
                break;

            case 5:
                long accountNumber = logInAccount.getCardNumber();
                AccountController.closeAccount(logInAccount);
                logInAccount = null;
                System.out.println("Your account #" + accountNumber + " has been deleted.\n");
                break;
            case 6:
                logInAccount = null;
                System.out.println("You have successfully logged out!");
                break;
            case 0:
                System.out.println("Bye!");
                System.exit(0);
                break;
            default:
                break;
        }
    }
}