package model;

import static org.junit.jupiter.api.Assertions.*;

import exceptions.NotInBoardException;
import org.junit.jupiter.api.Test;

//test for GameBoard
class GameBoardTest {
    private GameBoard gameBoard;

    @Test
    void testConstructor() {
        gameBoard = new GameBoard(3, 3);
        assertEquals(3, gameBoard.getRow());
        assertEquals(3, gameBoard.getColumn());
        assertEquals(9, gameBoard.getSize());

        for (int i = 0; i < gameBoard.getRow(); i++) {
            for (int j = 0; j < gameBoard.getColumn(); j++) {
                assertEquals(' ', gameBoard.getChar(i, j));
            }
        }

    }

    @Test
    void testPutPiecePassed() {
        try {
            gameBoard = new GameBoard(3, 3);
            gameBoard.putPiece(0, 0, 'x');
            assertEquals('x', gameBoard.getChar(0, 0));
        } catch (NotInBoardException e) {
            fail("Exception not expected");
        }
    }

    @Test
    void testPutPieceExceptionRow() {
        try {
            gameBoard = new GameBoard(3, 3);
            gameBoard.putPiece(4, 3, 'x');
            fail("NotInBoardException expected");
        } catch (NotInBoardException e) {
            //expected
        }

    }

    @Test
    void testPutPieceExceptionColumn() {
        gameBoard = new GameBoard(3, 3);
        try {
            gameBoard.putPiece(3, 4, 'x');
            fail("NotInBoardException expected");
        } catch (NotInBoardException e) {
            //expected
        }

    }


}