package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.LinkedList;
import java.util.List;

// game status of the tic tac toe game
public class Games implements Writable {
    private Player p1;
    private Player p2;

    public boolean gameOver;

    //making a new game
    //EFFECTS: gameOver is not false in the newly created game
    public Games() {
        gameOver = false;
    }

    //making a new game
    //EFFECTS: gameOver is not false in the newly created game with players p1 p2
    public Games(Player p1, Player p2) {
        this.p1 = p1;
        this.p2 = p2;
        gameOver = false;
    }

    //USER STORY: I want to be able to restart the games, discarding all previous scores
    //REQUIRES:
    //MODIFIES: this
    //EFFECTS: start a new game, set player scores to 0
    public void startOver() {
        p1.score = 0;
        p2.score = 0;
        setGameOver();
    }

    //MODIFIES: this
    //EFFECTS: set gameOver = true when game is over
    public void setGameOver() {
        gameOver = true;
    }

    public Player getP1() {
        return p1;
    }

    public Player getP2() {
        return p2;
    }


    //REQUIRES:
    //MODIFIES: this
    //EFFECTS: make a list of players in this game
    public List<Player> getPlayers() {
        List<Player> players = new LinkedList<>();
        players.add(p1);
        players.add(p2);
        return players;
    }

    //EFFECTS: return true if game is over, false otherwise
    public boolean isGameOver() {
        return gameOver;
    }

    @Override
    // code cited from the example repo JsonSerializationDemo, workroom toJson() method
    // returns game as a JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Saved information of", "Game");
        json.put("players", playersToJson());
        return json;
    }

    // EFFECTS: returns players in this game as a JSON array
    private JSONArray playersToJson() {
        JSONArray jsonArray = new JSONArray();

        jsonArray.put(p1.toJson());
        jsonArray.put(p2.toJson());

        return jsonArray;
    }

}
