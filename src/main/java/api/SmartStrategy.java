package api;

import boards.TicTacToeBoard;
import game.Cell;
import game.Player;
import placements.DefensivePlacement;
import placements.OffensivePlacement;
import placements.Placement;

import java.util.Optional;

public class SmartStrategy extends Strategy {
    @Override
    public Cell getOptimalMove(TicTacToeBoard ticTacToeBoard, Player player) {
        //Attacking moves
        Placement placement = OffensivePlacement.get();
        Optional<Cell> place =  placement.place(ticTacToeBoard, player);
        if(place.isPresent()){
            return place.get();
        }

        //Defensive moves
        placement = DefensivePlacement.get();
        place = placement.place(ticTacToeBoard, player);
        if(place.isPresent()){
            return place.get();
        }

        return new BasicStrategy().getOptimalMove(ticTacToeBoard, player);
    }
}
