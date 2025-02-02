package game;

import boards.TicTacToeBoard;

public class GameFactory {
    public Game createGame(Integer maxTimePerMove, Integer maxTimePerPlayer) {
        return  new Game(new GameConfig(3, maxTimePerPlayer!=null),
                new TicTacToeBoard(),
                null,
                0,
                maxTimePerPlayer,
                maxTimePerMove);

    }

    public Game createGame(Integer maxTimePerMove, Integer maxTimePerPlayer, TicTacToeBoard startingBoard) {
        return  new Game(new GameConfig(3, maxTimePerPlayer!=null),
                startingBoard,
                null,
                0,
                maxTimePerPlayer,
                maxTimePerMove);

    }

    public Game createGame(int maxTimePerPlayer) {
        return  new Game(new GameConfig(3, true),
                new TicTacToeBoard(),
                null,
                0,
                maxTimePerPlayer,
                null);
    }

    public Game createGame() {
        return  new Game(new GameConfig(null, true),
                new TicTacToeBoard(),
                null,
                0,
                null,
                null);
    }
}
