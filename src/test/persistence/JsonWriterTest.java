package persistence;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import model.*;

import java.io.IOException;

// codes below cited from the example repo JsonSerializationDemo, JsonWriterTest class
// tests for JsonWriter class
public class JsonWriterTest extends JsonTest {
    private Games game;
    private Player p1;
    private Player p2;

    @Test
    void testWriterInvalidFile() {
        try {
            p1 = new Player("Sam", 'x', 3);
            p2 = new Player("James", 'o', 1);
            game = new Games(p1, p2);
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterStandardGame() {
        try {
            p1 = new Player("Sam", 'x', 3);
            p2 = new Player("James", 'o', 1);
            game = new Games(p1, p2);
            JsonWriter writer = new JsonWriter("./data/testWriterStandardGame.json");
            writer.open();
            writer.write(game);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterStandardGame.json");
            game = reader.read();
            assertEquals(2,game.getPlayers().size());
            checkPlayer(game.getPlayers().get(0),"Sam", 'x', 3);
            checkPlayer(game.getPlayers().get(1),"James", 'o', 1);


        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    /*@Test
    void testWriterEmptyGame() {
        try {
            Games game = new Games();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyGame.json");
            writer.open();
            writer.write(game);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyGame.json");
            game = reader.read();
            assertEquals(null, game.getP1());
            assertEquals(null, game.getP1());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }*/
}
