package edu.sdccd.cisc191.f.server.account;

import edu.sdccd.cisc191.f.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

    Account findByID(long id);
    Account findAccountByCardNumber(long cardNumber);
}
