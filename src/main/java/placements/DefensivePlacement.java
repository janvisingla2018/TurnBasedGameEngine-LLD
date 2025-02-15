package placements;

import Utils.Utils;
import boards.TicTacToeBoard;
import game.Cell;
import game.Move;
import game.Player;

import java.util.Optional;

public class DefensivePlacement implements Placement {
    public static DefensivePlacement defensivePlacement;

    private DefensivePlacement() {}

    public static synchronized DefensivePlacement get() {
        defensivePlacement = (DefensivePlacement) Utils.getIfNull(defensivePlacement, DefensivePlacement::new);
        return defensivePlacement;
    }

    @Override
    public Optional<Cell> place(TicTacToeBoard board, Player player) {
        return Optional.ofNullable(defense(player, board));
    }

    @Override
    public Placement next() {
        return ForkPlacement.get();
    }

    private Cell defense(Player player, TicTacToeBoard ticTacToeBoard) {
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(ticTacToeBoard.getSymbol(i, j) == null){
                    Move move = new Move(Cell.getCell(i, j), player.flip());
                    TicTacToeBoard boardCopy = ticTacToeBoard.move(move);
                    if(ruleEngine.getState(boardCopy).isOver()) {
                        return Cell.getCell(i, j);
                    }
                }
            }
        }
        return null;
    }
}
