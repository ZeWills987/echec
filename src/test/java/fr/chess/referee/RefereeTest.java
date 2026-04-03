package fr.chess.referee;

import fr.chess.controller.GameManager;
import fr.chess.helper.Utils;
import fr.chess.model.Board;
import fr.chess.model.Color;
import fr.chess.model.Coordinate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class RefereeTest {
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
            2 |   |   |   |PW-|   |   |   |   |
              +---+---+---+---+---+---+---+---+\s
            1 |   |   |   |   |KW-|   |   |   |
              +---+---+---+---+---+---+---+---+\s
                a   b   c   d   e   f   g   h\s            
            """;

    @ParameterizedTest(name = "Test {index}: expected {1})")
    @CsvSource({
            // EXPECTED, MESSAGE
            "true, 'White king is in check'",
    })
    void isCheckMate(boolean expected, String message) {
        Board board = Utils.strToBoard(strBoard);
        board.displayBoard();
        Referee ref = new Referee();

        assertEquals(expected, ref.isCheckMate(Color.WHITE,board), message);
    }
    
    @ParameterizedTest(name = "Test {index}: at {0} to {1} expected {2} ({3})")
    @CsvSource({
            // SOURCE, TARGET, EXPECTED, MESSAGE
            "d2, d3, false, 'White king is in check'",

    })
    void isMoveLegal(String src, String dest, boolean expected, String message) {
        Board board = Utils.strToBoard(strBoard);
        board.displayBoard();
        Referee ref = new Referee();
        Coordinate source = Utils.strToCoords(src);
        Coordinate target = Utils.strToCoords(dest);

        assertEquals(expected, ref.isMoveLegal(source,target,board), message);

    }

    @ParameterizedTest(name = "Test {index}: at {0} to {1} expected {3} ({4})")
    @CsvSource({
            // SOURCE, TARGET, isWhite, EXPECTED, MESSAGE
            "true, true, 'Black queen make check'",
    })
    void isKingInCheck(boolean isWhite, boolean expected, String message) {
        Board board = Utils.strToBoard(strBoard);
        board.displayBoard();
        Referee ref = new Referee();

        Color color;
        if(isWhite){
            color = Color.WHITE;
        }else{
            color = Color.BLACK;
        }

        assertEquals(expected, ref.isKingInCheck(color, board), message);
    }
}