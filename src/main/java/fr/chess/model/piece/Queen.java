package fr.chess.model.piece;

import fr.chess.model.Color;
import fr.chess.model.Coordinate;
import fr.chess.model.Piece;
import fr.chess.model.PieceType;

public class Queen extends Piece {

    public Queen(Color color){
        super(color, PieceType.QUEEN);
    }

    @Override
    public boolean isValidMove(Coordinate source, Coordinate target) {
        return new Rook(getColor()).isValidMove(source,target)||
                new Bishop(getColor()).isValidMove(source,target);
    }
}
