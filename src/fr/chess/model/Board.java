package fr.chess.model;

public class Board {
    private final Piece[][] pieces;

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
    }

    /**
     * Delete the piece in board.
     *
     * @param target : the position of target Piece
     */
    public void deletePiece(Coordinate target){
        pieces[target.row()][target.col()]=null;
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
}
