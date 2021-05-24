package edu.sdccd.cisc191.f.server;

import edu.sdccd.cisc191.f.server.account.AccountRepository;
import edu.sdccd.cisc191.f.server.server.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EntityScan("edu.sdccd.cisc191.f")
public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        SpringApplication.run(Main.class);
    }
        @Bean
        public CommandLineRunner startServer(AccountRepository accountRepository) {
            return args -> {
                Server server = new Server();
                try {
                    server.start(4444, accountRepository);
                } catch(Exception e) {
                    e.printStackTrace();
                }
            };
        }
    }
