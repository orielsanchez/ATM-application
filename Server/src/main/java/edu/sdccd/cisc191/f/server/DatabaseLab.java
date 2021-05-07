package edu.sdccd.cisc191.f.server;

import edu.sdccd.cisc191.f.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DatabaseLab {

    private static final Logger log = LoggerFactory.getLogger(DatabaseLab.class);

    public static void main(String[] args) {
        SpringApplication.run(DatabaseLab.class);
    }

    @Bean
    public CommandLineRunner insertEntitiesDemo(AccountRepository repository) {
        return (args) -> {
            repository.save(AccountUtils.createAccount());
            repository.save(AccountUtils.createAccount());
            repository.save(AccountUtils.createAccount());
            repository.save(AccountUtils.createAccount());
            repository.save(AccountUtils.createAccount());
        };
    }

    @Bean
    public CommandLineRunner readEntitiesDemo(AccountRepository repository) {
        return (args) -> {
          log.info("Accounts found with findAll():");
          log.info("------------------------------");
          for (Account account : repository.findAll()) {
              log.info(account.toString());
              log.info("");
              log.info("Accounts found by findBy:");
              log.info("-------------------------");
              repository.findAccountsByBalanceEquals(0).forEach(account1 -> {
                  log.info(account1.toString());
              });
              log.info("");
          }
        };
    }
}
