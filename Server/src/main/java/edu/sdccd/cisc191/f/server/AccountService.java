package edu.sdccd.cisc191.f.server;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> getAllAccounts() {
        List<Account> accounts = new ArrayList<>();
        accountRepository.findAll().forEach(accounts::add);
        return accounts;
    }

    public Account getAccountById(int id) {
        return accountRepository.findByID(id);
    }

    public Account getAccountByCardNumber(long cardNumber) {
        return accountRepository.findAccountByCardNumber(cardNumber);
    }

    public void saveAccount(Account account) {
        accountRepository.save(account);
    }

    public void updateAccount(Account account) {
        accountRepository.save(account);
    }

    public void deleteAccount(long id) {
        accountRepository.deleteById(id);
    }

}
