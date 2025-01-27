package game;

import boards.TicTacToeBoard;

public interface Board {
    public abstract void move(Move move);

    public abstract TicTacToeBoard copy();
}
