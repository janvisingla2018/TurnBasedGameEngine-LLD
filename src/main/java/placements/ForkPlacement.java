package placements;

import Utils.Utils;
import boards.TicTacToeBoard;
import game.Cell;
import game.GameInfo;
import game.Player;

import java.util.Optional;

public class ForkPlacement implements Placement {
    public static ForkPlacement forkPlacement;

    private ForkPlacement() {}

    public static synchronized ForkPlacement get() {
        forkPlacement = (ForkPlacement) Utils.getIfNull(forkPlacement, ForkPlacement::new);
        return forkPlacement;
    }
    
    @Override
    public Optional<Cell> place(TicTacToeBoard board, Player player) {
        return Optional.ofNullable(fork(player, board));
    }

    @Override
    public Placement next() {
        return CentrePlacement.get();
    }

    private Cell fork(Player player, TicTacToeBoard ticTacToeBoard) {
        Cell best = null;
        GameInfo gameInfo = ruleEngine.getInfo(ticTacToeBoard);
        if(gameInfo.hasAFork()){
            best = gameInfo.getForkCell();
        }
        return best;
    }

}
