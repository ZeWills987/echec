package fr.chess.controller;

import fr.chess.model.Board;
import fr.chess.model.Color;
import fr.chess.model.Coordinate;
import fr.chess.model.Piece;
import fr.chess.referee.MoveValidator;

public class GameManager {
    private Board board;
    private Color currentTurn;
    private MoveValidator validator;

    public GameManager(){
        this.board = new Board();
        this.currentTurn = Color.WHITE;
        this.validator = new MoveValidator();
    }

    /**
     * Move piece between two position if already exist in initial position.
     *
     * @param source: the initial position
     * @param target: the final position
     * @return if the piece move.
     */
    public boolean playMove(Coordinate source, Coordinate target){
        Piece movingPiece = board.getPiece(source);

        // Check if piece exist in initial position and if it is the create color
        if(movingPiece==null || movingPiece.getColor()!= currentTurn) return false;

        // Check if referee valid move
        if(!validator.isLegalMove(source,target,board))return false;

        // Move Piece
        board.setPiece(movingPiece,target);
        board.deletePiece(source);


        switchTurn();
        return false;
    }

    /**
     * Switch color turn.
     */
    private void switchTurn(){
        currentTurn = (currentTurn==Color.WHITE)?Color.BLACK:Color.WHITE;
    }

    /**
     * Get color turn.
     * @return
     */
    public Color getCurrentTurn(){
        return currentTurn;
    }
}
