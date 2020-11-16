package model;

//creating a GameBoard with pieces on it
public class GameBoard {
    private int row;
    private int column;
    private Piece[][] board;

    //setting an empty board
    //REQUIRES: row and column are both both non negative int
    //MODIFIES:
    //EFFECTS: row and column on the game board are set to be blank
    public GameBoard(int row, int column) {
        this.row = row;
        this.column = column;
        board = new Piece[row][column];
        Piece blank = new Piece(' ');

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                board[i][j] = blank;
            }
        }
    }

    //USER STORY: I want to be able to add pieces to the game board
    //putting down pieces on the board
    //REQUIRES: row and column are both non negative int
    //MODIFIES: this
    //EFFECTS: put down the piece on to the board on (row, column)
    public void putPiece(int row, int column, char icon) {
        Piece piece = new Piece(icon);
        board[row][column] = piece;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    //return the size of the board
    public int getSize() {
        return column * row;
    }

    //return the char at a specific position on the board
    public char getChar(int row, int column) {
        return board[row][column].getIcon();
    }

}
