package edu.sdccd.cisc191.f.server;

import edu.sdccd.cisc191.f.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface AccountRepository extends CrudRepository<Account, Long> {

    List<Account> findAccountByCardNumber(long cardNumber);
    List<Account> findAccountsByBalanceEquals(int balance);
    List<Account> findAccountsByBalanceGreaterThan(int bigMoney);


    Account findByID(long id);
}
