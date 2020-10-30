package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

// game status of the tic tac toe game
public class Games implements Writable {
    private Player p1;
    private Player p2;

    public boolean gameOver;

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

    public void setGameOver() {
        gameOver = true;
    }

    public Player getP1() {
        return p1;
    }

    public Player getP2() {
        return p2;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    @Override
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
