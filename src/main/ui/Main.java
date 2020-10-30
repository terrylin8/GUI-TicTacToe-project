package ui;

import java.io.FileNotFoundException;

//runnable application
public class Main {
    public static void main(String[] args) {
        try {
            new TicTacToeApp();
        } catch (FileNotFoundException e) {
            System.out.println("Can not find file, failed to run app");
        }
    }
}
