package ui;

import java.util.*;

import model.*;

//UI
public class TicTacToeApp {
    private Player p1;
    private Player p2;
    private Games game;
    private GameBoard gameBoard;
    private Scanner input = new Scanner(System.in);

    // EFFECTS: runs the Tic Tac Toe app
    public TicTacToeApp() {
        runApp();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runApp() {
        boolean keepGoing = true;
        String command;

        init();

        while (keepGoing) {
            displayTopMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye" + p1.getUsername() + "! " + p1.currentStatus());
        System.out.println("\nGoodbye" + p2.getUsername() + "! " + p2.currentStatus());
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("1")) {
            game.gameOver = false;
            gameBoard = new GameBoard(3, 3);
            doGame();
        } else if (command.equals("2")) {
            doGameSetting();
        } else if (command.equals("3")) {
            printPlayerStatus(p1);
            printPlayerStatus(p2);
        } else if (command.equals("4")) {
            doIconSetting();
        } else {
            System.out.println("Invalid input!");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes Player, Games, and gameBoard
    private void init() {
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
        game = new Games();

        //initializes game board
        gameBoard = new GameBoard(3, 3);
    }

    // EFFECTS: displays menu of options to user
    private void displayTopMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\t1 -> Play game");
        System.out.println("\t2 -> Game setting");
        System.out.println("\t3 -> Display player status");
        System.out.println("\t4 -> Player icon setting");
        System.out.println("\tq -> quit");
    }

    //MODIFIES: this
    //EFFECTS: do game setting like startOver
    private void doGameSetting() {
        boolean keepGoing = true;
        Scanner input = new Scanner(System.in);
        String command;

        while (keepGoing) {
            displayGameMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else if (command.equals("1")) {
                System.out.println("Nothing here");
            } else if (command.equals("2")) {
                System.out.println("Scores has been reset! You can now play a new set of games");
                game.startOver(p1, p2);
            } else {
                System.out.println("Invalid input!");
            }
        }
    }

    // EFFECTS: displays menu of options for game setting to user
    private void displayGameMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\t1 -> Nothing for now");
        System.out.println("\t2 -> Start over with same player");
        System.out.println("\tq -> Back to main menu");
    }

    // Modifies: this
    // EFFECTS: changes the players' icons
    private void doIconSetting() {
        System.out.println("Enter new icon for " + p1.getUsername());
        p1.setIcon(input.next().charAt(0));
        System.out.println("Enter new icon for " + p2.getUsername());
        p2.setIcon(input.next().charAt(0));
    }

    // MODIFIES: this
    // EFFECTS: Let players alternately put pieces on the game board and stop when someone has won
    private void doGame() {
        while (!game.gameOver) {
            displayBoard();
            makeMove(p1);
            if (winCheck(p1)) {
                game.setGameOver();
                p1.addScore();
                System.out.println(p1.getUsername() + " you win!");
                break;
            }

            makeMove(p2);
            if (winCheck(p2)) {
                game.setGameOver();
                p2.addScore();
                System.out.println(p2.getUsername() + " you win!");
            }
        }
    }

    //EFFECTS: displays the current board, not implemented in this phase yet since no graphic approach
    private void displayBoard() {
        System.out.println("board");
    }

    //EFFECTS: place the icon of player onto gameBoard at the coordinate
    private void makeMove(Player p) {
        int row;
        int col;
        System.out.println(p.username + " please enter the row of your next move");
        row = input.nextInt();
        System.out.println(p.username + " please enter the column of your next move");
        col = input.nextInt();
        gameBoard.putPiece(row, col, p.getIcon());
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
        for (int diag = 0; (diag + 1) < gameBoard.getRow(); diag++) {
            if ((gameBoard.getChar(diag, diag) == gameBoard.getChar(diag + 1, diag + 1))
                    && (gameBoard.getChar(diag, diag) == icon)) {
                hasWon = true;
            }
        }
        //check top right-bottom left
        for (int diag = 0; (diag + 1) < gameBoard.getRow(); diag++) {
            if (gameBoard.getChar(diag, (gameBoard.getRow()) - (diag + 1))
                    == gameBoard.getChar(diag + 1, (gameBoard.getRow()) - ((diag + 1) + 1))
                    && (gameBoard.getChar(diag, (gameBoard.getRow()) - (diag + 1)) == icon)) {
                hasWon = true;
            }
        }
        return hasWon;
    }

    //EFFECTS: print the player's current status
    private void printPlayerStatus(Player p) {
        System.out.println(p.currentStatus());
    }
}
