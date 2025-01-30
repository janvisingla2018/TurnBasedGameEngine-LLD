package placements;

import Utils.Utils;
import boards.TicTacToeBoard;
import game.Cell;
import game.Player;

import java.util.Optional;

public class CentrePlacement implements Placement {
    public static CentrePlacement centrePlacement;

    private CentrePlacement() {}

    public static synchronized Placement get() {
        centrePlacement = (CentrePlacement) Utils.getIfNull(centrePlacement, CentrePlacement::new);
        return centrePlacement;
    }

    @Override
    public Optional<Cell> place(TicTacToeBoard board, Player player) {
        return Optional.ofNullable(centre(player, board));
    }

    private Cell centre(Player player, TicTacToeBoard ticTacToeBoard) {
        Cell centre = null;
        if(ticTacToeBoard.getSymbol(1,1) == null){
            centre = new Cell(1, 1);
        }
        return centre;
    }

    @Override
    public Placement next() {
        return CornerPlacement.get();
    }
}
