package fr.chess.model;

import fr.chess.helper.Utils;
import fr.chess.referee.Referee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    String strBoard = """
              +---+---+---+---+---+---+---+---+\s
            8 |   |   |   |   |RB-|   |   |   |
              +---+---+---+---+---+---+---+---+\s
            7 |   |   |   |   |QB-|   |   |   |
              +---+---+---+---+---+---+---+---+\s
            6 |   |   |   |   |   |   |   |   |
              +---+---+---+---+---+---+---+---+\s
            5 |   |   |   |   |   |   |   |   |
              +---+---+---+---+---+---+---+---+\s
            4 |   |   |   |   |   |   |   |   |
              +---+---+---+---+---+---+---+---+\s
            3 |   |   |   |   |   |   |   |   |
              +---+---+---+---+---+---+---+---+\s
            2 |   |   |   |   |   |   |   |   |
              +---+---+---+---+---+---+---+---+\s
            1 |   |   |   |   |KW-|   |   |   |
              +---+---+---+---+---+---+---+---+\s
                a   b   c   d   e   f   g   h\s            
            """;


    @Test
    void getPiece() {
    }

    @Test
    void setPiece() {
    }

    @Test
    void deletePiece() {
    }

    @Test
    void getActivePieces() {
    }

    @Test
    void isEmpty() {

    }


    @ParameterizedTest(name = "Test {index}: expected {1})")
    @CsvSource({
            // IsWhite, EXPECTED, MESSAGE
            "true ,e1,  'White king exist'",
            "false ,, 'Black king doesn't exist'",

    })
    void findKing(boolean isWhite,String expected, String message) {
        Board board = Utils.strToBoard(strBoard);
        board.displayBoard();
        Color color;
        if(isWhite){
            color = Color.WHITE;
        }else{
            color = Color.BLACK;
        }
        Coordinate expectedCoord = Utils.strToCoords(expected);

        assertEquals(expectedCoord, board.findKing(color), message);
    }

    @Test
    void undoMove() {
    }
}