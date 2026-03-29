package fr.chess.model.piece;

import fr.chess.model.Color;
import fr.chess.model.Coordinate;
import fr.chess.model.Piece;
import fr.chess.model.PieceType;

public class Pawn extends Piece {

    public Pawn(Color color){
        super(color, PieceType.PAWN);
    }

    @Override
    public boolean isValidMove(Coordinate source, Coordinate target) {
        if(source.equals(target)) return false;

        boolean isWhite = getColor()==Color.WHITE;

        int direction = (isWhite) ? -1 : 1;
        int rowDiff = target.row() - source.row();
        int colDiff = Math.abs(target.col() - source.col());

        boolean isMove = (rowDiff == direction && colDiff == 0);

        boolean isStartRow = (isWhite) ? source.row()==6 : source.row()==1 ;
        boolean doubleJump = (
                isStartRow &&
                rowDiff == 2 * direction &&
                colDiff == 0
        );

        boolean isCapture = (rowDiff == direction && colDiff == 1);

        return isMove || doubleJump || isCapture;
    }
}
