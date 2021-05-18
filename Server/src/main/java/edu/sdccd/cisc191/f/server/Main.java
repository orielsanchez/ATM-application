package edu.sdccd.cisc191.f.server;

import edu.sdccd.cisc191.f.server.view.MainMenu;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

public class Main {
    public static Scanner in = new Scanner(System.in);
    public static MainMenu mainMenu = new MainMenu();
    public static Database database = new Database();
    public static void main(String[] args) {

        // Server
        Server server = new Server();
        try {
            server.start(4444);
            server.stop();
        } catch(Exception e) {
            e.printStackTrace();
        }

        // Setup Database
        String filename = "main.db";
        Database.setDatabaseFileName(filename);
        Database.createNewTable(filename);

        // Show Menu
        mainMenu.show();
    }
}
