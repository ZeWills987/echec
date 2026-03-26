package fr.chess.controller;

import fr.chess.model.*;
import fr.chess.model.piece.*;
import fr.chess.referee.MoveValidator;

public class GameManager {
    private Board board;
    private Color currentTurn;
    private MoveValidator validator;

    public GameManager(){
        this.board = new Board();
        this.currentTurn = Color.WHITE;
        this.validator = new MoveValidator();
        setUpBoard();
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
     * Fill initial board.
     */
    private void setUpBoard(){
        PieceType[] setup = {
                PieceType.ROOK,PieceType.KNIGHT,
                PieceType.BISHOP,PieceType.QUEEN,
                PieceType.KING,PieceType.BISHOP,
                PieceType.KNIGHT,PieceType.ROOK
        };

        for(int col = 0;col<8;col++){
            // Black Pieces (row 0)
            board.setPiece(createPiece(Color.BLACK,setup[col]),
                    new Coordinate(0,col));
            // Black Pawns (row 1)
            board.setPiece(createPiece(Color.BLACK,PieceType.PAWN),
                    new Coordinate(1,col));
            // White Pawns (row 6)
            board.setPiece(createPiece(Color.WHITE,PieceType.PAWN),
                    new Coordinate(6,col));
            // White Pieces (row 7)
            board.setPiece(createPiece(Color.WHITE,setup[col]),
                    new Coordinate(7,col));
        }
    }

    /**
     * Factory function to create the good sub class thanks to PieceType.
     *
     * @param color: the player color
     * @param type: the piece type
     * @return the good piece class.
     */
    private Piece createPiece(Color color,PieceType type){
        return switch (type){
            case ROOK -> new Rook(color);
            case KNIGHT -> new Knight(color);
            case BISHOP -> new Bishop(color);
            case QUEEN -> new Queen(color);
            case KING -> new King(color);
            default -> throw new IllegalArgumentException("Unknow type");
        };
    }

    /**
     * Switch color turn.
     */
    private void switchTurn(){
        currentTurn = (currentTurn==Color.WHITE)?Color.BLACK:Color.WHITE;
    }

    /**
     * Get board.
     * @return the board.
     */
    public Board getBoard(){
        return board;
    }

    /**
     * Get color turn.
     * @return
     */
    public Color getCurrentTurn(){
        return currentTurn;
    }
}
