package fr.chess.model.piece;

import fr.chess.model.Color;
import fr.chess.model.Coordinate;
import fr.chess.model.Piece;
import fr.chess.model.PieceType;

public class Knight extends Piece {

    public Knight(Color color){
        super(color, PieceType.KNIGHT);
    }

    @Override
    public boolean isValidMove(Coordinate source, Coordinate target) {
        if(source.equals(target)) return false;

        return (source.deltaRow(target) ==2 && source.deltaCol(target)==1)
        || (source.deltaRow(target)==1 && source.deltaCol(target)==2);
    }
}
