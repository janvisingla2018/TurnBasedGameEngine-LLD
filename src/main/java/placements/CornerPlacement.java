package placements;

import Utils.Utils;
import boards.TicTacToeBoard;
import game.Cell;
import game.Player;

import java.util.Optional;

public class CornerPlacement implements Placement {
    public static CornerPlacement cornerPlacement;

    private CornerPlacement() {}

    public static synchronized Placement get() {
        cornerPlacement = (CornerPlacement) Utils.getIfNull(cornerPlacement, CornerPlacement::new);
        return cornerPlacement;
    }

    @Override
    public Optional<Cell> place(TicTacToeBoard board, Player player) {
        return Optional.ofNullable(corner(player, board));
    }

    private Cell corner(Player player, TicTacToeBoard ticTacToeBoard) {
        final int[][] corners = new int[][]{{0,0},{0,2},{2,0},{2,2}};
        Cell corner = null;
        for(int i = 0; i < corners.length; i++){
            if(ticTacToeBoard.getSymbol(corners[i][0], corners[i][1]) == null){
                corner = new Cell(corners[i][0], corners[i][1]);
            }
        }
        return corner;
    }

    @Override
    public Placement next() {
        return null;
    }
}
