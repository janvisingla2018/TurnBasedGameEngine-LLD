package api;

import boards.TicTacToeBoard;
import game.Player;

public class StrategyFactory {
    public Strategy getStrategy(TicTacToeBoard ticTacToeBoard, Player player) {
        Strategy strategy = null;
        int threshold = 3;
        if (countMoves(ticTacToeBoard) < threshold) {
            strategy = new BasicStrategy();
        } else if(countMoves(ticTacToeBoard) < threshold + 1) {
            strategy = new SmartStrategy();
        } else if(player.getTimeUsedInMillis() > 100000){
            strategy = new BasicStrategy();
        }
        else {
            strategy = new OptimalStrategy();
        }
        return strategy;
    }

    private int countMoves(TicTacToeBoard ticTacToeBoard) {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (ticTacToeBoard.getSymbol(i, j) != null)
                    count++;
            }
        }
        return count;
    }
}
