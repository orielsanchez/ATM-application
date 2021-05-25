package edu.sdccd.cisc191.f.server;

import edu.sdccd.cisc191.f.server.account.AccountRepository;
import edu.sdccd.cisc191.f.server.server.Server;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

/**
 * The main class starts the server and runs this class as the SpringBoot Application.
 *
 * @author Oriel Sanchez
 */
@SpringBootApplication
@EntityScan("edu.sdccd.cisc191.f")
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class);
    }

    @Bean
    public CommandLineRunner startServer(AccountRepository accountRepository) {
        return args -> {
            Server server = new Server();
            try {
                server.start(4444, accountRepository);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }
}