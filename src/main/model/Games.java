package model;

public class Games {

    public int matches;
    public boolean gameOver;

    public Games() {
        gameOver = false;
        matches = 1;
    }

    //USER STORY: I want to be able to restart the games, discarding all previous scores
    //REQUIRES:
    //MODIFIES: this
    //EFFECTS: start a new game, set player scores to 0
    public void startOver(Player p1, Player p2) {
        p1.score = 0;
        p2.score = 0;
        setGameOver();
    }

    public void setMatches(int matches) {
        this.matches = matches;
    }

    public void setGameOver() {
        gameOver = true;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public int getMatches() {
        return matches;
    }
}
