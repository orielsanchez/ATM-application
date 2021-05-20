package edu.sdccd.cisc191.f.server;

import edu.sdccd.cisc191.f.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

    Account findByID(long id);
    Account findAccountByCardNumber(long cardNumber);
    List<Account> findAccountsByBalanceEquals(int balance);
    List<Account> findAccountsByBalanceGreaterThan(int bigMoney);
}
