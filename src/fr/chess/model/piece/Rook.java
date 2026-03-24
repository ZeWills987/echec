package fr.chess.model.piece;

import fr.chess.model.Color;
import fr.chess.model.Coordinate;
import fr.chess.model.Piece;
import fr.chess.model.PieceType;

public class Rook extends Piece {

    public Rook(Color color){
        super(color, PieceType.ROOK);
    }

    @Override
    public boolean isValidMove(Coordinate source, Coordinate target) {
        if(source.equals(target)) return false;

        boolean sameRow = (source.row()==target.row());
        boolean sameCol = (source.col()==target.col());

        return sameRow || sameCol;
    }
}
