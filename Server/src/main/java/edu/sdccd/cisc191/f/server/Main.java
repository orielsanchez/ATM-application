package edu.sdccd.cisc191.f.server;

import edu.sdccd.cisc191.f.server.view.MainMenu;

import java.util.Scanner;

public class Main {
    public static Scanner in = new Scanner(System.in);
    public static MainMenu mainMenu = new MainMenu();
    public static Database database = new Database();
    public static void main(String[] args) {
        String filename = "main.db";
        Database.createNewDatabase(filename);
        Database.createNewTable(filename);
        mainMenu.show();
    }
}
