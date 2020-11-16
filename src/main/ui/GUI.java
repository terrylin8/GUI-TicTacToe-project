package ui;

import model.GameBoard;
import model.Games;
import model.Player;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class GUI implements ActionListener {
    private Player p1;
    private Player p2;
    private Games game;
    private GameBoard gameBoard;
    private static final int ROW_COL = 3;
    private Scanner input = new Scanner(System.in);
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/player.json";

    private JFrame frame;
    private JPanel panel;
    private JLabel label;
    private JTextField box1;
    private JTextField box2;

    private JButton play;
    private JButton startOver;
    private JButton display;
    private JButton setIcon;
    private JButton save;
    private JButton load;

    public GUI() throws FileNotFoundException {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runApp();
    }

    private void runApp() {
        frame = new JFrame();
        panel = new JPanel();


        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Time to Tic Tac Toe!");
        frame.add(panel);

        init();
        displayMenu();

        panel.setLayout(new FlowLayout());
        frame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: initializes Player, Games, and gameBoard
    private void init() {
        popup("Enter name of player 1 in box 1, name of player 2 in box 2", "Player setup");
        String name1;
        String name2;
        Scanner input1 = new Scanner(System.in);
        Scanner input2 = new Scanner(System.in);

        System.out.println("Enter name of player 1");
        name1 = input1.nextLine();
        System.out.println("Enter name of player 2");
        name2 = input2.nextLine();

        //initializes players
        p1 = new Player(name1, 'O', 0);
        p2 = new Player(name2, 'X', 0);

        //initializes games
        game = new Games(p1, p2);

        //initializes game board
        gameBoard = new GameBoard(ROW_COL, ROW_COL);
    }

    private void displayMenu() {
        panel.add(play = new JButton("Play game"));
        play.setActionCommand("play");
        play.addActionListener(this);

        panel.add(startOver = new JButton("Start over with same player"));
        startOver.setActionCommand("startOver");
        startOver.addActionListener(this);

        panel.add(display = new JButton("Display player status"));
        display.setActionCommand("display");
        display.addActionListener(this);

        panel.add(setIcon = new JButton("Player icon setting"));
        setIcon.setActionCommand("setIcon");
        setIcon.addActionListener(this);

        panel.add(save = new JButton("Save player information"));
        save.setActionCommand("save");
        save.addActionListener(this);

        panel.add(load = new JButton("Load player information"));
        load.setActionCommand("load");
        load.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "startOver":
                popup("Scores has been reset! You can now play a new set of games",
                        "Start Over!");
                game.startOver();
                break;
            case "display":
                printPlayerStatus(p1, p2);
                break;
            case "setIcon":
                doIconSetting();
                break;
            case "save":
                saveGame();
                break;
            case "load":
                loadGame();
                break;
            default:
        }
    }

    // code cited from the example repo JsonSerializationDemo, WorkRoomApp saveWorkRoom() method
    // USER STORY: I want to be able to save the player information (name, score, icon)
    // EFFECTS: save players in Game to file
    private void saveGame() {
        try {
            jsonWriter.open();
            jsonWriter.write(game);
            jsonWriter.close();
            popup("Saved player information to " + JSON_STORE, "");
        } catch (FileNotFoundException e) {
            popup("Unable to write from file: " + JSON_STORE, "error");
        }
    }

    // code cited from the example repo JsonSerializationDemo, WorkRoomApp loadWorkRoom() method
    // USER STORY: I want to be able to reload the player information stored
    // MODIFIES: this
    // EFFECTS: loads game of players from file
    private void loadGame() {
        try {
            game = jsonReader.read();
            p1 = game.getP1();
            p2 = game.getP2();
            popup("Loaded saved player information from " + JSON_STORE, "");
        } catch (IOException e) {
            popup("Unable to read from file: " + JSON_STORE, "error");
        }
    }

    private void doIconSetting() {
        System.out.println("Enter new icon for " + p1.getUsername());
        p1.setIcon(input.next().charAt(0));
        System.out.println("Enter new icon for " + p2.getUsername());
        p2.setIcon(input.next().charAt(0));
    }

    //EFFECTS: print the player's current status
    private void printPlayerStatus(Player p1, Player p2) {
        popup(p1.currentStatus() + "\n" + p2.currentStatus(), "Player Status");
    }

    //EFFECTS: create a pop up message box
    private void popup(String msg, String title) {
        JOptionPane.showMessageDialog(null, msg, "InfoBox: "
                + title, JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        try {
            new GUI();
        } catch (FileNotFoundException e) {
            System.out.println("Can not find file, failed to run app");
        }
    }
}
