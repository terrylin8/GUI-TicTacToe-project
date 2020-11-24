package ui;

import exceptions.NotInBoardException;
import model.Game;
import model.GameBoard;
import model.Player;

import java.awt.*;
import javax.swing.*;

//GUI of tic tac toe app game window
public class GameWindow {
    private int count = 0;
    private Player p1;
    private Player p2;
    private Game game;
    private GameBoard gameBoard;
    private static final int ROW_COL = 3;
    Font font = new Font("Courier New", Font.BOLD, 25);
    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
//    JLabel label = new JLabel("Hello!");

    //making a new window to play the tic tac toe game
    public GameWindow(Player p1, Player p2, Game game, GameBoard gameBoard) {
        this.p1 = p1;
        this.p2 = p2;
        this.game = game;
        this.gameBoard = gameBoard;
        game.gameOver = false;

        frame = new JFrame();
        panel = new JPanel();
        panel.setLayout(new java.awt.GridLayout(3, 3));
        panel.setBackground(Color.BLACK);

        frame.add(panel);
        frame.setTitle("Tic Tac Toe!");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setSize(400, 400);
        makeButtons(gameBoard);
        frame.setVisible(true);

        UIManager ui = new UIManager();
        ui.put("OptionPane.messageFont", new Font("Courier New", Font.BOLD, 15));
        ui.put("OptionPane.background", Color.DARK_GRAY);
        ui.put("OptionPane.messageForeground", Color.orange);


    }

    // MODIFIES: this
    // EFFECTS: change the font and colour of button
    private void setFontColour(JButton button) {
        button.setForeground(Color.orange);
        button.setFont(font); //Sets the font
        button.setBackground(Color.DARK_GRAY);
    }

    // MODIFIES: this
    // EFFECTS: make 9 buttons for the tic tac toe game, initialize each on as blank
    private void makeButtons(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                final JButton button = new JButton("");
                String string = i + "" + j;
                button.setName(string);
                button.addActionListener(e -> buttonClicked(button));
                button.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                setFontColour(button);
                panel.add(button);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: use count to keep tract of which player's turn it is and let the player make a move

    private void buttonClicked(JButton button) {
        if (button.getText().equals("") && !game.gameOver) {
            count++;
            int row = Character.getNumericValue(button.getName().charAt(0));
            int col = Character.getNumericValue(button.getName().charAt(1));
            if (count % 2 == 0) {
                makeMove(row, col, p2, button);
            }
            if (count % 2 == 1) {
                makeMove(row, col, p1, button);
            }
        }
    }

    // EFFECTS: place a piece onto the appropriate position on the board and check if a player has won
    private void makeMove(int row, int col, Player p, JButton button) {
        try {
            gameBoard.putPiece(row, col, p.getIcon());
        } catch (NotInBoardException e) {
            e.printStackTrace();
        }
        button.setText(String.valueOf(p.getIcon()));
        if (winCheck(p)) {
            popup(p.getUsername() + " you win!", "Congratulations!");
            game.setGameOver();
            p.addScore();
        }
    }


    // EFFECTS: returns true if player with the same piece has won, false otherwise
    private boolean winCheck(Player p) {
        return (winAcross(p.getIcon()) || winVertical(p.getIcon()) || winDiagonal(p.getIcon()));
    }

    // EFFECTS: check if win condition is matched across
    private boolean winAcross(char icon) {
        boolean hasWon = false;
        for (int row = 0; row < gameBoard.getRow(); row++) {
            if ((gameBoard.getChar(row, 0) == gameBoard.getChar(row, 1))
                    && (gameBoard.getChar(row, 1) == gameBoard.getChar(row, 2))
                    && (gameBoard.getChar(row, 0) == icon)) {
                hasWon = true;
            }
        }
        return hasWon;
    }

    /// EFFECTS: check if win condition is matched vertically
    private boolean winVertical(char icon) {
        boolean hasWon = false;
        for (int col = 0; col < gameBoard.getColumn(); col++) {
            if ((gameBoard.getChar(0, col) == gameBoard.getChar(1, col))
                    && (gameBoard.getChar(1, col) == gameBoard.getChar(2, col))
                    && (gameBoard.getChar(0, col) == icon)) {
                hasWon = true;
            }
        }
        return hasWon;
    }

    // EFFECTS: check if win condition is matched diagonally
    private boolean winDiagonal(char icon) {
        boolean hasWon = false;

        // assume row = col!!!
        if ((gameBoard.getChar(0, 0) == gameBoard.getChar(1, 1))
                && (gameBoard.getChar(1, 1) == gameBoard.getChar(2, 2))
                && (gameBoard.getChar(1, 1) == icon)) {
            hasWon = true;
        }

        //check top right-bottom left
        if ((gameBoard.getChar(0, 2) == gameBoard.getChar(1, 1))
                && (gameBoard.getChar(1, 1) == gameBoard.getChar(2, 0))
                && (gameBoard.getChar(1, 1) == icon)) {
            hasWon = true;
        }
        return hasWon;
    }

    //EFFECTS: create a pop up message box
    private void popup(String msg, String title) {
        JOptionPane.showMessageDialog(null, msg, "InfoBox: "
                + title, JOptionPane.INFORMATION_MESSAGE);
    }

    public Player getP2() {
        return p2;
    }

    public Player getP1() {
        return p1;
    }

    public Game getGame() {
        return game;
    }
}
