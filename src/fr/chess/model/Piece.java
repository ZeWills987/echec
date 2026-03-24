package fr.chess.model;

public abstract class Piece {
    private final Color color;
    private final PieceType type;

    public Piece(Color color, PieceType type){
        this.color = color;
        this.type = type;
    }

    public Color getColor(){
        return color;
    }

    public PieceType getType(){
        return type;
    }

    public abstract boolean isValidMove(Coordinate start, Coordinate end);

    @Override
    public String toString() {
        String typeFisrtLetter = type.name().substring(0, 1).toUpperCase();

        return color == Color.WHITE ?
                typeFisrtLetter + "W-"
                : typeFisrtLetter + "B-";
    }
}
