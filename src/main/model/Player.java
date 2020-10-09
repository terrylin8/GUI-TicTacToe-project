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
}
