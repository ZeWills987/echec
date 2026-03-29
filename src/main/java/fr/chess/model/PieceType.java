package fr.chess.model;

public enum PieceType {
    PAWN(1),
    KNIGHT(3),
    BISHOP(3),
    ROOK(5),
    QUEEN(9),
    KING(100);

    private final int value;

    PieceType(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}
