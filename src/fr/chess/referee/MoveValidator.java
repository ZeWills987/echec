package fr.chess.referee;

import fr.chess.model.*;

import java.util.List;

public class MoveValidator {

    /**
     * Check if the way between source and target position was clear.
     *
     * @param source: initial position
     * @param target: final position
     * @param board: board game
     * @return if the path is clear.
     */
    public static  boolean isPathClear(Coordinate source, Coordinate target,Board board){
        Piece sourcePiece = board.getPiece(source);

        if(sourcePiece==null) return false;

        if(sourcePiece.getType()== PieceType.KNIGHT) return true;

        int stepRow = Integer.compare(target.row(),source.row());
        int stepCol = Integer.compare(target.col(),source.col());

        int currentRow = source.row() + stepRow;
        int currentCol = source.col() +stepCol;

        while(currentRow!=target.row() || currentCol!=target.col()){
            Coordinate current = new Coordinate(currentRow,currentCol);

            if(!board.isEmpty(current)){
                return false;
            }

            currentRow += stepRow;
            currentCol += stepCol;
        }

        return true;
    }

    /**
     * Check if target position is a legal move.
     *
     * @param source: the initial position
     * @param target: the target position
     * @param board: the board game
     * @return if it's a legal move.
     */
    public boolean isLegalMove(Coordinate source, Coordinate target,Board board){
        Piece movingPiece = board.getPiece(source);
        Piece targetPiece = board.getPiece(target);

        if(movingPiece == null)return false;

        if(targetPiece!=null && targetPiece.getColor() == movingPiece.getColor())return false;

        if(!movingPiece.isValidMove(source,target))return false;

        if(!isPathClear(source,target,board)) return false;

        if(movingPiece.getType()==PieceType.PAWN){
            boolean isDiagonal = (source.col() != target.col());

            if(isDiagonal){
                if (targetPiece == null) return false;
            }else{
                if(targetPiece!=null) return false;
            }
        }
        return true;
    }


    /**
     * Check if King in check.
     *
     * @param kingColor: the color of King
     * @param board: the boardgame
     * @return if king is in check.
     */
    public boolean isKingInCheck(Color kingColor,Board board){
        Coordinate kingPosition = board.findKing(kingColor);
        List<Piece> enemies = board.getAtctivePieces(kingColor==Color.WHITE ? Color.BLACK:Color.WHITE);

        for(Piece enemy : enemies){
            if(enemy.isValidMove(enemy.getPosition(),kingPosition)){
                isPathClear(enemy.getPosition(),kingPosition,board);
                return true;
            }
        }
        return false;
    }
}

