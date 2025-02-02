package placements;

import Utils.Utils;
import boards.TicTacToeBoard;
import game.Cell;
import game.Move;
import game.Player;

import java.util.Optional;

public class OffensivePlacement implements Placement {
    public static OffensivePlacement offensivePlacement;

    private OffensivePlacement() {}

    public static synchronized OffensivePlacement get() {
        offensivePlacement = (OffensivePlacement) Utils.getIfNull(offensivePlacement, OffensivePlacement::new);
        return offensivePlacement;
    }

    @Override
    public Optional<Cell> place(TicTacToeBoard board, Player player) {
        return Optional.ofNullable(offense(player, board));
    }

    @Override
    public Placement next() {
        return DefensivePlacement.get();
    }

    private Cell offense(Player player, TicTacToeBoard ticTacToeBoard) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(ticTacToeBoard.getSymbol(i, j) == null){
                    Move move = new Move(Cell.getCell(i, j), player);
                    TicTacToeBoard boardCopy = ticTacToeBoard.move(move);
                    if(ruleEngine.getState(boardCopy).isOver()) {
                        return move.getCell();
                    }
                }
            }
        }
        return null;
    }
}
