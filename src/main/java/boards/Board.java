package boards;

import game.Move;

public interface Board {
    public abstract Board move(Move move);

    public abstract TicTacToeBoard copy();
}
