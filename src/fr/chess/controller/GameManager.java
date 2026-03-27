package fr.chess.controller;

import fr.chess.model.*;
import fr.chess.model.piece.*;
import fr.chess.referee.MoveValidator;
import fr.chess.referee.Referee;

public class GameManager {
    private Board board;
    private Color currentTurn;
    private Referee referee;

    public GameManager(){
        this.board = new Board();
        this.currentTurn = Color.WHITE;
        this.referee = new Referee();
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
        if(!referee.isMoveLegal(source,target,board))return false;

        // Save target piece before action
        Piece capturedPiece = board.getPiece(target);

        // Move Piece
        board.setPiece(movingPiece,target);
        board.deletePiece(source);

        switchTurn();

        checkGameStatus();
        return true;
    }

    /**
     * Check if is check mate or pat.
     */
    private void checkGameStatus() {
        if (referee.isCheckMate(currentTurn, board)) {
            System.out.println("Check mate ! Winner is " + (currentTurn == Color.WHITE ? "NOIR" : "BLANC"));
        } else if (referee.isStalemate(currentTurn, board)) {
            System.out.println("Match nul (Pat) !");
        }
    }

    /**
     * Convert user move to coordinate.
     *
     * @param move: the move (e2-e3)
     * @return the initial position and there target.
     */
    public Coordinate stringToCoordinate(String move) {
        if (move == null || move.length() < 2) return null;

        char charCol = move.toLowerCase().charAt(0);
        int col = charCol - 'a';

        int inputRow = Character.getNumericValue(move.charAt(1));

        // Inversion : l'humain dit '1' pour le bas, mais le tableau commence à 0 en haut.
        // Formule : 8 - 1 = 7 (la dernière ligne de ton tableau Java)
        int row = 8 - inputRow;

        if (row < 0 || row > 7 || col < 0 || col > 7) {
            return null;
        }

        return new Coordinate(row, col);
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
            case PAWN -> new Pawn(color);
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
