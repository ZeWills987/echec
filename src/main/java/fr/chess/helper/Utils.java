package fr.chess.helper;

import fr.chess.model.Board;
import fr.chess.model.Color;
import fr.chess.model.Coordinate;
import fr.chess.model.Piece;
import fr.chess.model.piece.*;

public class Utils {
    /**
     * Convert string coords to Coordinate.
     *
     * @param coordString: the string coordinate
     * @return the coordinate.
     */
    public static Coordinate strToCoords(String coordString) {
        if (coordString == null || coordString.length() != 2) {
            throw new IllegalArgumentException("Format invalide (ex: 'a8')");
        }

        // 1. Colonne : 'a' -> 0, 'b' -> 1, ..., 'h' -> 7
        char columnChar = coordString.toLowerCase().charAt(0);
        int col = columnChar - 'a';

        // 2. Ligne : '8' -> 0, '7' -> 1, ..., '1' -> 7
        // Calcul : 8 - le chiffre
        int rowDigit = Character.getNumericValue(coordString.charAt(1));
        int row = 8 - rowDigit;

        // 3. Vérification de sécurité (0 à 7)
        if (col < 0 || col > 7 || row < 0 || row > 7) {
            throw new IllegalArgumentException("Coordonnée hors plateau (0-7) : " + coordString);
        }

        return new Coordinate(row, col);
    }

    /**
     * Convert a message to Board.
     *
     * @param asciiBoard: the string board
     * @return the board.
     */
    public static Board strToBoard(String asciiBoard) {
        Board board = new Board();
        String[] lines = asciiBoard.split("\n");
        for (int rank = 0; rank < 8; rank++) {
            int stringLineIndex = 1 + (rank * 2);

            if (stringLineIndex >= lines.length) break;
            String line = lines[stringLineIndex];

            for (int file = 0; file < 8; file++) {
                int charIndex = 3 + (file * 4);

                if (charIndex + 3 <= line.length()) {
                    String pieceCode = line.substring(charIndex, charIndex + 3).trim();

                    if (!pieceCode.isEmpty()) {
                        Coordinate coord = new Coordinate(rank, file);
                        board.setPiece(parsePieceCode(pieceCode), coord);
                    }
                }
            }
        }
        return board;
    }

    /**
     * Convert string piece to real piece.
     *
     * @param code: the string piece
     * @return the piece.
     */
    public static Piece parsePieceCode(String code) {
        // Code attendu : "BW-" (Bishop White), "PB-" (Pawn Black), etc.
        if (code.length() < 2) return null;

        char typeChar = code.charAt(0);
        char colorChar = code.charAt(1);

        Color color = (colorChar == 'W') ? Color.WHITE : Color.BLACK;

        return switch (typeChar) {
            case 'P' -> new Pawn(color);
            case 'B' -> new Bishop(color);
            case 'R' -> new Rook(color);
            case 'N' -> new Knight(color);
            case 'Q' -> new Queen(color);
            case 'K' -> new King(color);
            default -> null;
        };
    }
}
