package api;

import boards.Board;
import boards.TicTacToeBoard;
import game.*;

public class AIEngine {
    StrategyFactory strategyFactory = new StrategyFactory();

    public Move suggestMove(Board board, Player player) {
        if (board instanceof TicTacToeBoard) {
            TicTacToeBoard ticTacToeBoard = (TicTacToeBoard) board;
            Cell suggestion = strategyFactory.getStrategy(ticTacToeBoard, player).getOptimalMove(ticTacToeBoard, player);
            if (suggestion != null)
                return new Move(suggestion, player);
            throw new IllegalStateException("No such move");
        } else {
            throw new IllegalArgumentException("Invalid type");
        }
    }
}
