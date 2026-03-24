package fr.chess.model;

/**
 * Position immutable in board.
 *
 * @param row : the row (0-7)
 * @param col : the column (0-7)
 */
public record Coordinate(int row,int col) {

    public Coordinate{
    }

    /**
     * Check if position is in board.
     *
     * @return if is in board.
     */
    public boolean isValid(){
        return row >= 0 && row < 8 && col >= 0 && col < 8;
    }

    /**
     * Calculate the distance in row axis between two positions.
     *
     * @param other : the other position
     * @return the distance in row axis beetween two position.
     */
    public int deltaRow(Coordinate other){
        return Math.abs(this.row - other.row);
    }

    /**
     * Calculate the distance in columun axis between two positions.
     *
     * @param other : the other position
     * @return the distance in column axis beetween two position.
     */
    public int deltaCol(Coordinate other){
        return Math.abs(this.col - other.col);
    }
}
