package ui;

import model.GameBoard;
import model.Games;
import model.Player;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

//GUI of tic tac toe app main menu
public class GUI implements ActionListener {
    private Player p1;
    private Player p2;
    private String name1;
    private String name2;
    private Games game;
    private GameBoard gameBoard;
    private static final int ROW_COL = 3;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/player.json";

    private JFrame frame;
    private JPanel panel;

    private JButton play;
    private JButton startOver;
    private JButton display;
    private JButton setIcon;
    private JButton save;
    private JButton load;
    private Font font = new Font("Courier New", Font.BOLD, 25);

    //constructor for GUI class
    public GUI() throws FileNotFoundException {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runApp();
    }

    // EFFECTS: runs the Tic Tac Toe app
    private void runApp() {
        frame = new JFrame();
        panel = new JPanel();

        frame.setSize(1000, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Time to Tic Tac Toe!");
        frame.add(panel);

        init();
        displayMenu();

        panel.setLayout(new GridLayout(3, 2));
        panel.setBackground(Color.BLACK);
        //frame.pack();
        frame.setVisible(true);

        UIManager ui = new UIManager();
        ui.put("OptionPane.messageFont", new Font("Courier New", Font.BOLD, 15));
        ui.put("OptionPane.background", Color.DARK_GRAY);
        ui.put("OptionPane.messageForeground", Color.orange);
    }

    // MODIFIES: this
    // EFFECTS: initializes Player, Games, and gameBoard
    private void init() {
        Icon image = new ImageIcon("./data/tictactoe.jpeg");
        UIManager ui = new UIManager();
        ui.put("OptionPane.messageFont", new Font("Courier New", Font.BOLD, 15));
        ui.put("OptionPane.background", Color.DARK_GRAY);
        ui.put("OptionPane.messageForeground", Color.BLACK);
        name1 = String.valueOf(JOptionPane.showInputDialog(null,
                "Enter name for player 1",
                "Player setting",
                JOptionPane.INFORMATION_MESSAGE,
                image,
                null,
                ""));
        name2 = String.valueOf(JOptionPane.showInputDialog(null,
                "Enter name for player 2",
                "Player setting",
                JOptionPane.INFORMATION_MESSAGE,
                image,
                null,
                ""));
        //initializes players
        p1 = new Player(name1, 'O', 0);
        p2 = new Player(name2, 'X', 0);

        //initializes games
        game = new Games(p1, p2);

        //initializes game board
        gameBoard = new GameBoard(ROW_COL, ROW_COL);
    }

    // EFFECTS: displays menu of buttons to user
    private void displayMenu() {
        panel.add(play = new JButton("Play game"));
        play.setActionCommand("play");
        play.addActionListener(this);

        panel.add(startOver = new JButton("Start over with same player"));
        startOver.setActionCommand("startOver");
        startOver.addActionListener(this);

        panel.add(display = new JButton("Display player status"));
        display.addActionListener(e -> printPlayerStatus(p1, p2));

        panel.add(setIcon = new JButton("Player icon setting"));
        setIcon.addActionListener(e -> doIconSetting());

        panel.add(save = new JButton("Save player information"));
        save.addActionListener(e -> saveGame());

        panel.add(load = new JButton("Load player information"));
        load.addActionListener(e -> loadGame());

        setFontColour(play);
        setFontColour(startOver);
        setFontColour(display);
        setFontColour(setIcon);
        setFontColour(save);
        setFontColour(load);
    }

    // MODIFIES: this
    // EFFECTS: change the font and colour of button
    private void setFontColour(JButton button) {
        button.setForeground(Color.orange);
        button.setFont(font);
        button.setBackground(Color.DARK_GRAY);
    }

    // code cited from the example repo JsonSerializationDemo, WorkRoomApp saveWorkRoom() method
    // USER STORY: I want to be able to save the player information (name, score, icon)
    // EFFECTS: save players in Game to file
    private void saveGame() {
        try {
            jsonWriter.open();
            jsonWriter.write(game);
            jsonWriter.close();
            popup("Saved player information to " + JSON_STORE + "  ", "");
        } catch (FileNotFoundException e) {
            popup("Unable to write from file: " + JSON_STORE + "  ", "error");
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
            popup("Loaded saved player information from " + JSON_STORE + "  ", "");
        } catch (IOException e) {
            popup("Unable to read from file: " + JSON_STORE + "  ", "error");
        }
    }

    // Modifies: this
    // EFFECTS: changes the players' icons
    private void doIconSetting() {
        p1.setIcon(JOptionPane.showInputDialog("Enter new icon for " + p1.getUsername()).charAt(0));
        p2.setIcon(JOptionPane.showInputDialog("Enter new icon for " + p2.getUsername()).charAt(0));
    }

    //EFFECTS: print the player's current status
    private void printPlayerStatus(Player p1, Player p2) {
        popup(p1.currentStatus() + "  " + "\n" + p2.currentStatus() + "  ", "Player Status");
    }

    //EFFECTS: create a pop up message box
    private void popup(String msg, String title) {
        JOptionPane.showMessageDialog(null, msg, "InfoBox: "
                + title, JOptionPane.INFORMATION_MESSAGE);
    }


    //EFFECTS: actions performed after buttons are clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "play":
                GameWindow gameWindow = new GameWindow(p1, p2, game, gameBoard);
                game = gameWindow.getGame();
                p1 = gameWindow.getP1();
                p2 = gameWindow.getP2();
                gameBoard = new GameBoard(ROW_COL, ROW_COL);
                break;
            case "startOver":
                popup("Scores has been reset! You can now play a new set of games  ", "Start Over!");
                game.startOver();
                break;
        }
    }
}
