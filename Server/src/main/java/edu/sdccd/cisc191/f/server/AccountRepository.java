package edu.sdccd.cisc191.f.server;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

    Account findByID(long id);
    Account findAccountByCardNumber(long cardNumber);
    List<Account> findAccountsByBalanceEquals(double balance);
    List<Account> findAccountsByBalanceGreaterThan(double bigMoney);
}
