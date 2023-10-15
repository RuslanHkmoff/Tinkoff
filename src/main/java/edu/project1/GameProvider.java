package edu.project1;

import java.util.Scanner;

public class GameProvider {
    private final Scanner scanner;

    public GameProvider() {
        this.scanner = new Scanner(System.in);
    }

    public String getUserInput() {
        return scanner.nextLine();
    }

    public String getNumberOfMistakes() {
        return scanner.nextLine();
    }
}
