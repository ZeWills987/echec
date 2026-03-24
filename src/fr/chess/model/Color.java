package fr.chess.model;

public enum Color {
    WHITE,BLACK;


    /**
     * Get opposite of color.
     *
     * @return the opposite color.
     */
    public Color opposite(){
        return (this == WHITE) ? BLACK : WHITE;
    }
}
