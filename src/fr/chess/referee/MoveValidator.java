package fr.chess.referee;

import fr.chess.model.Board;
import fr.chess.model.Coordinate;
import fr.chess.model.Piece;
import fr.chess.model.PieceType;

public class MoveValidator {

    public static  boolean isPathClear(Coordinate source, Coordinate target,Board board){
        Piece sourcePiece = board.getPiece(source);

        if(sourcePiece==null) return false;

        if(sourcePiece.getType()== PieceType.KNIGHT) return true;

        int stepRow = Integer.compare(target.row(),source.row());
        int stepCol = Integer.compare(target.col(),source.col());

        int currentRow = source.row() + stepRow;
        int currentCol = source.col() +stepCol;

        while(currentRow!=source.row() || currentCol!=source.col()){

        }

        return true;
    }
}

