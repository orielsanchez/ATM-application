package edu.sdccd.cisc191.f.server;

import edu.sdccd.cisc191.f.Account;

import java.util.Scanner;

public class ThreadingLab {
    public static final int NUM_THREADS = 100;
    public static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        Account account = new Account(1234567890123456L, "3434", 1000);
        Account transferAccount = new Account(1259048479387439L, "3445", 500);
        ThreadDemo[] workers = new ThreadDemo[NUM_THREADS];

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < NUM_THREADS; i++) {
            workers[i] = new ThreadDemo(account, transferAccount);
            workers[i].start();
        }

        for (int i = 0; i < NUM_THREADS; i++) {
            while (workers[i].isAlive()) {
                try {
                    workers[i].join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }


        long elapsedTime = System.currentTimeMillis() - startTime;
        System.out.println("\nThe account balance is " + account.getBalance());
        System.out.println("\nThe transfer account balance is " + transferAccount.getBalance());
        System.out.println("\nTotal elapsed time: " + (elapsedTime/1000.0) + " seconds.\n");
    }
}
