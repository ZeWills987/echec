package fr.chess.model;

public abstract class Piece {
    private final Color color;
    private final PieceType type;
    private Coordinate position;

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

    public Coordinate getPosition(){
        return position;
    }

    public void setPosition(Coordinate target){
        this.position = target;
    }

    public abstract boolean isValidMove(Coordinate start, Coordinate end);

    @Override
    public String toString() {
        char letter = switch (type) {
            case PAWN -> 'P';
            case ROOK -> 'R';
            case KNIGHT -> 'N';
            case BISHOP -> 'B';
            case QUEEN -> 'Q';
            case KING -> 'K';
        };

        String colorSuffix = (color == Color.WHITE) ? "W" : "B";

        return letter + colorSuffix + "-";
    }
}
