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
     * Convert a char to Piece.
     *
     * @param c: the piece type first letter
     * @return the piece type.
     */
    public static Piece charToPiece(char c) {
        boolean isWhite = Character.isUpperCase(c);
        char type = Character.toLowerCase(c);
        Color color = isWhite ? Color.WHITE : Color.BLACK;

        return switch (type) {
            case 'p' -> new Pawn(color);
            case 'r' -> new Rook(color);
            case 'n' -> new Knight(color);
            case 'b' -> new Bishop(color);
            case 'q' -> new Queen(color);
            case 'k' -> new King(color);
            default -> null;
        };
    }

    /**
     * Convert a message to Board.
     *
     * @param content: the content
     * @return the board.
     */
    public static Board stringToBoard(String content) {
        Board board = new Board();
        String cleanContent = content.replaceAll("\\s+", "");

        for (int i = 0; i < cleanContent.length() && i < 64; i++) {
            char c = cleanContent.charAt(i);
            int row = i / 8;
            int col = i % 8;
            Coordinate coord = new Coordinate(row, col);

            Piece piece = charToPiece(c);
            if (piece != null) {
                board.setPiece(piece, coord);
            }
        }
        return board;
    }

    public static Board strToBoard(String asciiBoard) {
        Board board = new Board();
        String[] lines = asciiBoard.split("\n");

        // On parcourt les 8 lignes d'échecs (8 à 1)
        // Dans ton String, la ligne '8' est à l'index 1 (la 2ème ligne)
        // Les lignes de pièces sont espacées de 2 dans ton ASCII
        for (int rank = 0; rank < 8; rank++) {
            int stringLineIndex = 1 + (rank * 2);
            String line = lines[stringLineIndex];

            for (int file = 0; file < 8; file++) {
                // Calcul de la position horizontale du texte dans la case
                // Le premier contenu est au caractère index 4, puis tous les 4 caractères
                int charIndex = 4 + (file * 4);

                if (charIndex + 2 < line.length()) {
                    String pieceCode = line.substring(charIndex, charIndex + 3).trim();

                    if (!pieceCode.isEmpty() && !pieceCode.equals("|")) {
                        Coordinate coord = new Coordinate(rank, file);
                        board.setPiece(parsePieceCode(pieceCode),coord);
                    }
                }
            }
        }
        return board;
    }

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

    public static Coordinate asciiToCoord(int rowLine, int colChar) {
        // 1. Calcul de la ligne (Row)
        // Dans ton dessin, les pièces sont sur les lignes impaires : 1, 3, 5...
        // On transforme l'index du String en index de tableau (0-7)
        int row = (rowLine - 1) / 2;

        // 2. Calcul de la colonne (Col)
        // Les pièces commencent au caractère 4, puis tous les 4 caractères (|   |)
        int col = (colChar - 4) / 4;

        // Sécurité pour ne pas sortir du tableau 8x8
        if (row < 0 || row > 7 || col < 0 || col > 7) {
            throw new IllegalArgumentException("Position ASCII hors limites");
        }

        return new Coordinate(row, col);
    }
}
