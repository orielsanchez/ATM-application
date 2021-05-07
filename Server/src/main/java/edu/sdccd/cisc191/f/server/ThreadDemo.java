package edu.sdccd.cisc191.f.server;

import edu.sdccd.cisc191.f.Account;

public class ThreadDemo extends Thread {
    private static final int NUM_TRANSACTIONS = 10;
    private Account account;
    private Account transferAccount;
    private int randomAmount;

    public ThreadDemo(Account account, Account transferAccount) {
        this.account = account;
        this.transferAccount = transferAccount;
        this.randomAmount = 5;
    }

    @Override
    public void run() {
        for (int i = 0; i < NUM_TRANSACTIONS; i++) {
            if (i % 2 == 0) {
                transferAccount.withdraw(randomAmount);
                System.out.println("transfer account balance: " + transferAccount.getBalance());
                account.deposit(randomAmount);
                System.out.println("account balance: " + account.getBalance());

            } else {
                account.withdraw(randomAmount);
                System.out.println("account balance: " + account.getBalance());
                transferAccount.deposit(randomAmount);
                System.out.println("transfer account balance: " + transferAccount.getBalance());
            }
        }
    }
}