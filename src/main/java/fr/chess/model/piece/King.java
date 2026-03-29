package fr.chess.model.piece;

import fr.chess.model.Color;
import fr.chess.model.Coordinate;
import fr.chess.model.Piece;
import fr.chess.model.PieceType;

public class King extends Piece {

    public King(Color color){
        super(color, PieceType.KING);
    }

    @Override
    public boolean isValidMove(Coordinate source, Coordinate target) {
        if(source.equals(target)) return false;

        return target.deltaRow(source)<=1 &&
                target.deltaCol(source)<=1;
    }
}
