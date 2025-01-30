package api;

import boards.Board;
import boards.CellBoard;
import game.Cell;
import game.GameState;

import java.util.function.Function;

public class Rule{
    Function<CellBoard, GameState> condition;

    public Rule(Function<CellBoard, GameState> condition) {
        this.condition = condition;
    }
}
