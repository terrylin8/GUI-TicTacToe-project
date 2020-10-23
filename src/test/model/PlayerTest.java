package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

//test for Player
public class PlayerTest {
    private Player player;

    @BeforeEach
    void runBefore() {
        player = new Player("Tom", '*', 0);
    }


    @Test
    void testConstructor() {
        assertEquals("Tom", player.getUsername());
        assertEquals('*', player.getIcon());
        assertEquals(0, player.getScore());
    }

    @Test
    void testSetIcon() {
        player.setIcon('%');
        assertEquals('%', player.getIcon());
    }

    @Test
    void testCurrentStatus() {
        assertEquals("Tom, your icon is: *, your score is: 0", player.currentStatus());
    }

    @Test
    void testAddScore() {
        player.addScore();
        assertEquals(1, player.getScore());
    }
}
