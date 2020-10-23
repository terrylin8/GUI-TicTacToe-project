package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;


//test for Games
public class GamesTest {
    private Games games;
    private Player p1;
    private Player p2;

    @Test
    void testConstructor() {
        games = new Games();
        assertEquals(1, games.getMatches());
        assertFalse(games.isGameOver());
    }

    @Test
    void testStartOver() {
        games = new Games();
        p1 = new Player("Sam", 'x',3);
        p2 = new Player("James", 'o',1);
        games.startOver(p1, p2);
        assertEquals(0, p1.score);
        assertEquals(0, p2.score);
        assertTrue(games.isGameOver());

    }

    @Test
    void testSetMatches(){
        games = new Games();
        games.setMatches(5);
        assertEquals(5, games.getMatches());
        assertFalse(games.isGameOver());
    }

}
