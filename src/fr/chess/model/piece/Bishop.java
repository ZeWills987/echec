package fr.chess.model.piece;

import fr.chess.model.Color;
import fr.chess.model.Coordinate;
import fr.chess.model.Piece;
import fr.chess.model.PieceType;

public class Bishop extends Piece {

    public Bishop(Color color){
        super(color, PieceType.BISHOP);
    }

    @Override
    public boolean isValidMove(Coordinate source, Coordinate target) {
        if(source.equals(target)) return false;

        return source.deltaRow(target) == source.deltaCol(target);
    }
}
