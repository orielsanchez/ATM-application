package edu.sdccd.cisc191.f.server;

public class AccountController {

    private static AccountRepository accountRepository;


    public static void updateAccount(Account account) {
        accountRepository.save(account);
    }

    public static boolean transferFunds(Account sender, double transferAmount, Account recipient) {
        boolean transferSuccessful = false;

        boolean withdrawSuccessful = sender.withdraw(transferAmount);

        if (withdrawSuccessful) {
            boolean depositSuccessful = recipient.deposit(transferAmount);
            if (depositSuccessful) {
                accountRepository.save(sender);
                accountRepository.save(recipient);
                transferSuccessful = true;
            }
        }

        return transferSuccessful;
    }

    public static void closeAccount(Account account) {
        accountRepository.delete(account);
    }

    public static Account createAccount() {
        return AccountUtils.createAccount();
    }

    public static void addAccountToDatabase(Account account) {
        accountRepository.save(account);
    }

    public static void setAccountRepository(AccountRepository accountRepository) {
        AccountController.accountRepository = accountRepository;
    }
}
