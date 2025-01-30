package api;

import boards.Board;
import boards.TicTacToeBoard;
import game.*;
import placements.DefensivePlacement;
import placements.OffensivePlacement;
import placements.Placement;

import java.util.Optional;

public class AIEngine {
    RuleEngine ruleEngine = new RuleEngine();

    public Move suggestMove(Board board, Player player) {
        if (board instanceof TicTacToeBoard) {
            TicTacToeBoard ticTacToeBoard = (TicTacToeBoard) board;
            Cell suggestion;
            int threshold = 3;
            if (countMoves(ticTacToeBoard) < threshold) {
                suggestion = getBasicMove(ticTacToeBoard);
            } else if(countMoves(ticTacToeBoard) < threshold + 1) {
                suggestion = getCellToPlay(player, ticTacToeBoard);
            }else {
                suggestion = getOptimalMove(player, ticTacToeBoard);
            }
            if (suggestion != null)
                return new Move(suggestion, player);
            throw new IllegalStateException("No such move");
        } else {
            throw new IllegalArgumentException("Invalid type");
        }
    }

    private Cell getOptimalMove(Player player, TicTacToeBoard ticTacToeBoard) {
        Placement placement = OffensivePlacement.get();
        while(placement.next() != null){
            Optional<Cell> place = placement.place(ticTacToeBoard, player);
            if(place.isPresent()){
                return place.get();
            }
            placement = placement.next();
        }
        return null;
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

    private Cell getCellToPlay(Player player, TicTacToeBoard ticTacToeBoard) {
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


        return getBasicMove(ticTacToeBoard);
    }

    public Cell getBasicMove(TicTacToeBoard ticTacToeBoard) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (ticTacToeBoard.getSymbol(i, j) == null) {
                    return new Cell(i, j);
                }
            }
        }
        return null;
    }
}
