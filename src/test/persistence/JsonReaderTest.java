package persistence;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import model.*;

import java.io.IOException;
import java.util.List;

// codes below cited from the example repo JsonSerializationDemo, JsonReaderTest class
// tests for JsonReader
public class JsonReaderTest extends JsonTest{
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Games game = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyWorkRoom.json");
        try {
            Games game = reader.read();
            fail("Can load game with no players");
        } catch (IOException e) {
            //expected
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderStandardGame.json");
        try {
            Games game = reader.read();
            List<Player> players = game.getPlayers();
            assertEquals(2, game.getPlayers().size());
            checkPlayer(game.getPlayers().get(0),"A", '%', 0);
            checkPlayer(game.getPlayers().get(1),"B", '$', 10);
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
