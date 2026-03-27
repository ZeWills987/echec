import fr.chess.controller.GameManager;
import fr.chess.model.Board;
import fr.chess.model.Coordinate;
import fr.chess.model.Piece;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GameManager game = new GameManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            displayBoard(game);

            MovePair move = inputUser(scanner, game);
            if (move == null) break; // L'utilisateur a quitté

            boolean success = game.playMove(move.source(), move.target());

            if (!success) {
                System.out.println(">>> Coup refusé par l'arbitre !");
            }
        }
        System.out.println("Partie terminée.");
    }

    public static MovePair inputUser(Scanner scanner, GameManager game) {
        while (true) {
            System.out.print("\nEntrez votre coup (ex: e2 e4) ou 'quit' : ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("quit")) return null;

            String[] parts = input.split("\\s+"); // Gère un ou plusieurs espaces

            if (parts.length != 2) {
                System.out.println("Format invalide ! Tapez deux positions séparées par un espace.");
                continue;
            }

            Coordinate source = game.stringToCoordinate(parts[0]);
            Coordinate target = game.stringToCoordinate(parts[1]);

            if (source == null || target == null) {
                System.out.println("Position invalide (utilisez a1 à h8).");
                continue;
            }

            return new MovePair(source, target);
        }
    }

    public static void displayBoard(GameManager game){
        Board board = game.getBoard();
        String separator_col = "  +---+---+---+---+---+---+---+---+ \n";
        for(int row = 0;row<8;row++){
            System.out.print(separator_col);
            System.out.print((8-row)+" ");
            for(int col = 0;col<8;col++){
                Piece piece = board.getPiece(new Coordinate(row,col));
                String content = "";
                if(piece!=null){
                    content = piece.toString();
                }else{
                    content = "   ";
                }
                System.out.print("|"+content);
            }
            System.out.print("| \n");
        }
        System.out.println(separator_col);
        System.out.println("    a   b   c   d   e   f   g   h ");
    }

    public record MovePair(Coordinate source, Coordinate target) {}
}