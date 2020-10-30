package persistence;

import static org.junit.jupiter.api.Assertions.*;
import model.*;

//json test for Player
public class JsonTest {
    protected void checkPlayer(Player player, String username, char icon, int score) {
        assertEquals(username, player.getUsername());
        assertEquals(icon, player.getIcon());
        assertEquals(score, player.getScore() );
    }
}
