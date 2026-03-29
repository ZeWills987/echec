package fr.chess.referee;

import fr.chess.Main;
import fr.chess.controller.GameManager;
import fr.chess.helper.Utils;
import fr.chess.model.Board;
import fr.chess.model.Coordinate;
import fr.chess.model.Piece;
import jdk.jshell.execution.Util;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MoveValidatorTest {

    String pathClearBoardTest = """
              +---+---+---+---+---+---+---+---+\s
            8 |RW-|   |   |   |   |   |   |   |
              +---+---+---+---+---+---+---+---+\s
            7 |   |PB-|PB-|   |   |   |NB-|   |
              +---+---+---+---+---+---+---+---+\s
            6 |   |   |PW-|   |BB-|   |   |   |
              +---+---+---+---+---+---+---+---+\s
            5 |   |   |   |PB-|   |   |   |   |
              +---+---+---+---+---+---+---+---+\s
            4 |   |   |   |   |PW-|   |   |   |
              +---+---+---+---+---+---+---+---+\s
            3 |   |   |   |   |   |   |   |   |
              +---+---+---+---+---+---+---+---+\s
            2 |   |   |   |RW-|   |   |   |   |
              +---+---+---+---+---+---+---+---+\s
            1 |   |   |   |   |   |   |   |BW-|
              +---+---+---+---+---+---+---+---+\s
                a   b   c   d   e   f   g   h\s            
            """;

    @ParameterizedTest(name = "Test {index}: at {0} to {1} expected {2} ({3})")
    @CsvSource({
            // SOURCE, TARGET, EXPECTED, MESSAGE
            "d2, d7, false, 'Block by pawn in d5'",
            "d2, d4, true,  'Vertical path clear before pawn'",
            "h1, b7, false, 'Block by Pawn in d5'",
            "h1, f3, true,  'Diagonal path clear before pawn'",
            "e4, d5, true,  'No path between source and target'",
            "e4, e5, true,  'Pawn path clear'",
            "c7, c5, false, 'Black pawn path block by white pawn'",
            "b7, b5, true,  'Black pawn path clear'",

    })
    void isPathClearTest(String src, String dest, boolean expected, String message) {
        Board board = Utils.strToBoard(pathClearBoardTest);
        board.displayBoard();
        Coordinate source = Utils.strToCoords(src);
        Coordinate target = Utils.strToCoords(dest);

        assertEquals(expected, MoveValidator.isPathClear(source, target, board), message);
    }

    @ParameterizedTest(name = "Test {index}: at {0} to {1} expected {2} ({3})")
    @CsvSource({
            // SOURCE, TARGET, EXPECTED, MESSAGE
            "d2, d7, false, 'Block by pawn in d5'",
            "d2, d4, true,  'Vertical path clear before pawn'",
            "h1, b7, false, 'Block by Pawn in d5'",
            "h1, f3, true,  'Diagonal path clear before pawn'",
            "e4, d5, true,  'No path between source and target'",
            "e4, e5, true,  'Pawn path clear'",
            "c7, c5, false, 'Black pawn path block by white pawn'",
            "b7, b5, true,  'Black pawn path clear'",

    })
    void isLegalMove(String src, String dest, boolean expected, String message) {
        Board board = Utils.strToBoard(pathClearBoardTest);
        MoveValidator validator = new MoveValidator();
        board.displayBoard();
        Coordinate source = Utils.strToCoords(src);
        Coordinate target = Utils.strToCoords(dest);

        assertEquals(expected, validator.isLegalMove(source, target, board), message);
    }
}