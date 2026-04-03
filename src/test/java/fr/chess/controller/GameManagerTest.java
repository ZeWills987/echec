package fr.chess.controller;

import fr.chess.helper.Utils;
import fr.chess.model.Board;
import fr.chess.model.Color;
import fr.chess.model.Coordinate;
import fr.chess.referee.Referee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class GameManagerTest {

    String strBoard = """
              +---+---+---+---+---+---+---+---+\s
            8 |   |   |   |   |KB-|   |   |   |
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

    @ParameterizedTest(name = "Test {index}: move {0} to {1} expected {2}")
    @CsvSource({
            // SOURCE, TARGET, EXPECTED, MESSAGE
            "d2, d4, false, 'Can't move pawn cause white king is in check'",
            "e1, e2, false, 'Can't move in this coordinate cause that in check'",
            "e1, f1, true, 'Is safe zone'",
            "e1, d1, true, 'Is safe zone'",
            "e1, f2, true, 'Is safe zone'",
    })
    void playMove(String src, String dest,boolean expected, String message) {
        Board board = Utils.strToBoard(strBoard);
        board.displayBoard();
        GameManager game = new GameManager(board);
        Coordinate source = Utils.strToCoords(src);
        Coordinate target = Utils.strToCoords(dest);

        assertEquals(expected, game.playMove(source,target), message);
    }
}