package edu.sdccd.cisc191.f.server;

import edu.sdccd.cisc191.f.server.view.MainMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Scanner;

@SpringBootApplication
public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);
    public static Scanner in = new Scanner(System.in);
    public static MainMenu mainMenu = new MainMenu();
    public static Database database = new Database();

    public static void main(String[] args) {
        SpringApplication.run(Main.class);
    }
        @Bean
        public CommandLineRunner startServer(AccountRepository accountRepository) {
            return args -> {
                Server server = new Server();
                try {
                    server.start(4444, accountRepository);
                    server.stop();
                } catch(Exception e) {
                    e.printStackTrace();
                }
            };
        }

        @Bean
        public CommandLineRunner startDatabase() {
        return args -> {

        // Setup Database
        String filename = "main.db";
        Database.setDatabaseFileName(filename);
        Database.createNewTable(filename);

        };
        }
    }
