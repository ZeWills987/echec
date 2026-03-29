package fr.chess;

import fr.chess.controller.GameManager;
import fr.chess.model.Board;
import fr.chess.model.Coordinate;
import fr.chess.model.Piece;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class ChessApp extends Application {
    private static final int TILE_SIZE = 80;
    private GameManager gameManager = new GameManager();
    private GridPane boardGrid = new GridPane();
    private Coordinate selectedSource = null;

    @Override
    public void start(Stage primaryStage) {
        renderBoard();

        Scene scene = new Scene(boardGrid, TILE_SIZE * 8, TILE_SIZE * 8);
        primaryStage.setTitle("Mon Jeu d'Échecs - JavaFX");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private void renderBoard() {
        boardGrid.getChildren().clear();

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                StackPane cell = new StackPane();
                Coordinate currentCoord = new Coordinate(row, col);

                // 1. Le fond (case)
                Rectangle square = new Rectangle(TILE_SIZE, TILE_SIZE);
                square.setFill((row + col) % 2 == 0 ? Color.BEIGE : Color.SADDLEBROWN);

                // Si la case est celle qu'on vient de sélectionner, on met une bordure
                if (selectedSource != null && selectedSource.equals(currentCoord)) {
                    square.setStroke(Color.CYAN);
                    square.setStrokeWidth(3);
                }

                cell.getChildren().add(square);

                // 2. La pièce
                Piece piece = gameManager.getBoard().getPiece(currentCoord);
                //displayBoard(gameManager);
                if (piece != null) {
                    String imgName = piece.getColor() + "_" + piece.getType() + ".png";

                    // On enlève le slash initial si on utilise getClass().getResourceAsStream()
                    // ou on s'assure que le chemin est exact par rapport à la racine des resources
                    var resource = getClass().getResourceAsStream("/images/" + imgName);

                    if (resource == null) {
                        // Log pour debug : si ça affiche ça, c'est que le nom de fichier est faux
                        System.err.println("Tentative échouée pour : /images/" + imgName);
                        //continue; // Passe à la case suivante au lieu de crash
                    }

                    Image img = new Image(resource);
                    ImageView iv = new ImageView(img);
                    iv.setFitWidth(TILE_SIZE * 0.8);
                    iv.setFitHeight(TILE_SIZE * 0.8);
                    cell.getChildren().add(iv);
                }

                // 3. Gestion du clic
                cell.setOnMouseClicked(e -> handleMouseClick(currentCoord));

                boardGrid.add(cell, col, row);
            }
        }
    }

    private void handleMouseClick(Coordinate clickedCoord) {
        if (selectedSource == null) {
            // Premier clic : on vérifie s'il y a une pièce à nous
            Piece p = gameManager.getBoard().getPiece(clickedCoord);
            if (p != null && p.getColor() == gameManager.getCurrentTurn()) {
                selectedSource = clickedCoord;
                renderBoard(); // Pour afficher la sélection
            }
        } else {
            // Deuxième clic : on tente le mouvement
            boolean moved = gameManager.playMove(selectedSource, clickedCoord);

            if (!moved) {
                System.out.println("Coup invalide !");
            }

            selectedSource = null; // Reset sélection dans tous les cas
            renderBoard(); // Mise à jour visuelle
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

    public static void main(String[] args) {
        launch(args);
    }
}