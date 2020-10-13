package model;

public class Player {
    public String username;
    public char icon;
    public int score;

    public Player(String name, char icon, int score) {
        username = name;
        this.icon = icon;
        this.score = score;
    }

    //USER STORY: I want to be able to set the icon of my piece
    //REQUIRES:
    //MODIFIES: this
    //EFFECTS: set the piece icon for this user
    public char setIcon(char icon) {
        this.icon = icon;
        return this.icon;
    }

    //REQUIRES: score >0
    //MODIFIES: this
    //EFFECTS: add 1 to the user's score
    public void addScore() {
        score++;
    }

    //USER STORY: I want to be able to see the current status(name,icon,score) of players
    //REQUIRES:
    //MODIFIES:
    //EFFECTS: return a string of username icon and score
    public String currentStatus() {
        return (username + icon + " your score is: " + score);
    }

    public String getUsername() {
        return username;
    }

    public char getIcon() {
        return icon;
    }

    public int getScore() {
        return score;
    }
}
