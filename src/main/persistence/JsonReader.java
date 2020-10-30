package persistence;

import model.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import org.json.*;

// codes cited from the example repo JsonSerializationDemo, JsonReader class
// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;


    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads game from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Games read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseGames(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }


    // EFFECTS: parses game from JSON object with information of both players and returns it
    private Games parseGames(JSONObject jsonObject) {
        List<Player> players;
        players = addPlayers(jsonObject);
        Games game = new Games(players.get(0), players.get(1));
        return game;
    }

    // MODIFIES: this
    // EFFECTS: parses players from JSON object and returns list of players
    private List<Player> addPlayers(JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("players");
        List<Player> players = new LinkedList<>();
        for (Object json : jsonArray) {
            JSONObject nextPlayer = (JSONObject) json;
            players.add(addPlayer(nextPlayer));
        }
        return players;
    }

    // MODIFIES: this
    // EFFECTS: parses player from JSON object and returns it
    private Player addPlayer(JSONObject jsonObject) {
        String username = jsonObject.getString("username");
        Character icon = jsonObject.getString("icon").charAt(0);
        Integer score = jsonObject.getInt("score");
        Player player = new Player(username, icon, score);

        return player;
    }
}
