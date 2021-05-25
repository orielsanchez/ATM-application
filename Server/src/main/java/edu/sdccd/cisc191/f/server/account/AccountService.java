package edu.sdccd.cisc191.f.server.account;

import edu.sdccd.cisc191.f.Account;
import org.springframework.stereotype.Service;

/**
 * This class is used as a service to access the accounts in the AccountRepository.
 * It contains several CRUD methods used for the application.
 *
 * @author Oriel Sanchez
 * @author Jonathan Plata
 * @author Stanley Schwarz Jr.
 */

@Service
public class AccountService {

    // Used instead of @Autowire
    private static AccountRepository accountRepository;

    public static void setAccountRepository(AccountRepository accountRepository) {
        AccountService.accountRepository = accountRepository;
    }

    /**
     * Updates the account to the accountRepository.
     *
     * @param account the account to be updated
     */
    public static void updateAccount(Account account) {
        accountRepository.save(account);
    }

    /**
     * Transfers the transferAmount argument from the sender balance to the recipient balance.
     *
     * @param sender the sender account
     * @param transferAmount the transfer amount
     * @param recipient the recipient account
     * @return true if the transfer was successful; false otherwise.
     */
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

    /**
     * Deletes the account from the accountRepository
     *
     * @param account the account to be deleted.
     */
    public static void closeAccount(Account account) {
        accountRepository.delete(account);
    }

    /**
     * Uses the AccountUtils class method createAccount() to generate a new account object.
     *
     * @return a new account
     */
    public static Account createAccount() {
        return AccountUtils.createAccount();
    }

    /**
     * Saves the account to the accountRepository.
     *
     * @param account the account to be saved
     */
    public static void addAccountToDatabase(Account account) {
        accountRepository.save(account);
    }
}
