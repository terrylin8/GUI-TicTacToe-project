package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

//test for GameBoard
class GameBoardTest {
    private GameBoard gameBoard;

//    @BeforeEach
//    void runBefore(){
//        gameBoard = new GameBoard(3,3);
//    }

    @Test
    void testConstructor() {
        gameBoard = new GameBoard(3, 3);
        assertEquals(3, gameBoard.getRow());
        assertEquals(3, gameBoard.getColumn());
        assertEquals(9, gameBoard.getSize());

        for (int i = 0; i < gameBoard.getRow(); i++) {
            for (int j = 0; j < gameBoard.getColumn(); j++) {
                assertEquals(' ',gameBoard.getChar(i, j));
            }
        }

    }

    @Test
    void testPutPiece() {
        gameBoard = new GameBoard(3, 3);
        gameBoard.putPiece(0, 0, 'x');
        assertEquals('x',gameBoard.getChar(0,0));
    }

}