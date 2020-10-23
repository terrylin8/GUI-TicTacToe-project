package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

//test for Piece
public class PieceTest {
    private Piece piece;

    @Test
    void testConstructor() {
        piece = new Piece('@');
        assertEquals('@', piece.getIcon());
    }
}
