package model;

import java.util.*;

public class GameBoard {
    private int row;
    private int column;
    private char[][] board;

    //setting an empty board
    //REQUIRES: row and column are both both non negative int
    //MODIFIES:
    //EFFECTS: row and column on the game board are set to be blank
    public GameBoard(int row, int column) {
        this.row = row;
        this.column = column;
        board = new char[row][column];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                board[i][j] = ' ';
            }
        }
    }

    //USER STORY: I want to be able to add pieces to the game board
    //putting down pieces on the board
    //REQUIRES: row and column are both non negative int
    //MODIFIES: this
    //EFFECTS: put down the piece, which is a char, on to the board on (row, column)
    public void putPiece(int row, int column, char piece) {
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
        return board[row][column];
    }
}
