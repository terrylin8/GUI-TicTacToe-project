package model;


//pieces on GameBoard
public class Piece {
    public char icon;

    // EFFECTS: set an icon for this piece
    public Piece(char icon) {
        this.icon = icon;
    }

    public char getIcon() {
        return icon;
    }
}
