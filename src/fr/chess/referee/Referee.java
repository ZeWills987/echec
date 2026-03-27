package fr.chess.referee;

import fr.chess.model.Board;
import fr.chess.model.Color;
import fr.chess.model.Coordinate;
import fr.chess.model.Piece;

import java.util.ArrayList;
import java.util.List;

import static fr.chess.referee.MoveValidator.isPathClear;

public class Referee {
    public final MoveValidator moveValidator;

    public Referee(){
        this.moveValidator = new MoveValidator();
    }

    /**
     * Check end game.
     *
     * @param color: the target player
     * @param board: the boardgame
     * @return if its checkmate.
     */
    public boolean isCheckMate(Color color, Board board){
        if(!isKingInCheck(color,board))return false;



        return hasNoLegalMoves(color,board);
    }

    /**
     * Check if player in check can move.
     *
     * @param color: the player in check
     * @param board: the boardgame
     * @return if player can move.
     */
    public boolean hasNoLegalMoves(Color color,Board board){
        List<Piece> activePieces = new ArrayList<>(board.getActivePieces(color));

        for(Piece p : activePieces){
            Coordinate start = p.getPosition();

            for(int r =0;r<8;r++){
                for (int c = 0; c < 8; c++) {
                    Coordinate target = new Coordinate(r, c);

                    if (isMoveLegal(start, target, board)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean isMoveLegal(Coordinate start, Coordinate target, Board board) {
        Piece p = board.getPiece(start);
        if (p == null) return false;

        if (!p.isValidMove(start, target) || !MoveValidator.isPathClear(start, target, board)) {
            return false;
        }

        Piece captured = board.getPiece(target);
        board.setPiece(p, target);
        board.deletePiece(start);

        boolean kingSafe = !isKingInCheck(p.getColor(), board);

        board.undoMove(p, start, target, captured);

        return kingSafe;
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
        List<Piece> enemies = board.getActivePieces(kingColor==Color.WHITE ? Color.BLACK:Color.WHITE);

        for(Piece enemy : enemies){
            if(enemy.isValidMove(enemy.getPosition(),kingPosition)&&
                isPathClear(enemy.getPosition(),kingPosition,board)){
                return true;
            }
        }
        return false;
    }

    /**
     * Check if is match null.
     *
     * @param color: the color
     * @param board: the board
     * @return if is null.
     */
    public boolean isStalemate(Color color, Board board) {
        return !isKingInCheck(color, board) && hasNoLegalMoves(color, board);
    }
}
