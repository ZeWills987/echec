package fr.chess.model;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final Piece[][] pieces;
    private final List<Piece> whitePieces = new ArrayList<>();
    private final List<Piece> blackPieces = new ArrayList<>();

    public Board(){
        pieces = new Piece[8][8];
    }

    /**
     * Get the piece at the target position.
     *
     * @param target: the position
     * @return the piece at the target position.
     */
    public Piece getPiece(Coordinate target){
        if (target == null || !target.isValid()) return null;
        return pieces[target.row()][target.col()];
    }

    /**
     * Set Piece at the target position in Board.
     *
     * @param piece : the piece
     * @param target: the position
     */
    public void setPiece(Piece piece,Coordinate target){
        if (!target.isValid()) return;
        pieces[target.row()][target.col()]=piece;

        if(piece!=null){
            piece.setPosition(target);
            List<Piece> activeList = (piece.getColor()==Color.WHITE ? whitePieces : blackPieces);
            if(!activeList.contains(piece)){
                activeList.add(piece);
            }
        }
    }

    /**
     * Delete the piece in board.
     *
     * @param target : the position of target Piece
     */
    public void deletePiece(Coordinate target){
        Piece pieceToRemove = getPiece(target);
        if(pieceToRemove != null){
            List<Piece> activeList = (pieceToRemove.getColor()==Color.WHITE ? whitePieces : blackPieces);
            activeList.remove(pieceToRemove);
        }
        pieces[target.row()][target.col()]=null;
    }

    /**
     * Get pieces list.
     *
     * @param color: the player color
     * @return a list all pieces of one player thansk to there color.
     */
    public List<Piece> getAtctivePieces(Color color){
        return (color==Color.WHITE ? whitePieces : blackPieces);
    }

    /**
     * Check if the target position is empty.
     *
     * @param target : the position in board
     * @return if position is empty.
     */
    public boolean isEmpty(Coordinate target){
        return getPiece(target)==null;
    }

    /**
     * Display board in terminal.
     */
    public void displayBoard() {
        String separator_col = "  +---+---+---+---+---+---+---+---+ \n";
        System.out.println("    a   b   c   d   e   f   g   h ");

        for (int i = 0; i < 8; i++) {
            System.out.print(separator_col);
            System.out.print(i + " ");

            for (int j = 0; j < 8; j++) {
                Piece piece = getPiece(new Coordinate(i,j));
                String content = (piece == null) ? "   " : piece.toString();
                System.out.print("|" + content);
            }
            System.out.print("|\n");
        }
        System.out.print(separator_col);
    }

    /**
     * Get the position of King.
     *
     * @param color: the color player
     * @return the position of King.
     */
    public Coordinate findKing(Color color){
        for(int r = 0;r<8;r++){
            for(int c=0;c<8;c++){
                Piece p = pieces[r][c];
                if(p!=null && p.getType()==PieceType.KING && p.getColor() == color){
                    return new Coordinate(r,c);
                }
            }
        }
        return null;
    }
}
