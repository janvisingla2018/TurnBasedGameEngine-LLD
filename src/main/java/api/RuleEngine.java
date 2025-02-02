package api;

import boards.Board;
import boards.CellBoard;
import boards.TicTacToeBoard;
import game.*;
import placements.DefensivePlacement;
import placements.OffensivePlacement;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class RuleEngine {

    Map<String, RuleSet> ruleMap = new HashMap<>();

    public RuleEngine() {
        ruleMap.put(TicTacToeBoard.class.getName(), TicTacToeBoard.getRules());
    }

    public GameInfo getInfo(CellBoard board){
        if(board instanceof TicTacToeBoard){
            TicTacToeBoard ticTacToeBoard = (TicTacToeBoard) board;
            GameState gameState = getState(board);
            Cell forkCell = null;
            for(TicTacToeBoard.Symbol symbol : TicTacToeBoard.Symbol.values()){
                Player player = new Player(symbol.marker());
                for(int i=0; i<3; i++){
                    for(int j=0; j<3; j++) {
                        if (ticTacToeBoard.getSymbol(i, j) != null) {
                            TicTacToeBoard b = ticTacToeBoard.move(new Move(Cell.getCell(i, j), player));
                            // force opponent to make a defensive move
                            //we still win after that move
                            DefensivePlacement defense = DefensivePlacement.get();
                            Optional<Cell> defensiveCell = defense.place(b, player.flip());
                            if(defensiveCell.isPresent()){
                                b = b.move(new Move(defensiveCell.get(), player.flip()));
                                OffensivePlacement offense = OffensivePlacement.get();
                                Optional<Cell> offensiveCell = offense.place(b, player);
                                if(offensiveCell.isPresent()){
                                    return new GameInfoBuilder()
                                            .isOver(gameState.isOver())
                                            .winner(gameState.getWinner())
                                            .hasFork(true)
                                            .forkCell(Cell.getCell(i, j))
                                            .player(player.flip())
                                            .build();
                                }
                            }
                        }
                    }
                }
            }
            return new GameInfoBuilder()
                    .isOver(gameState.isOver())
                    .winner(gameState.getWinner())
                    .hasFork(false)
                    .build();
        }else {
            throw new IllegalArgumentException("Board is not TicTacToeBoard");
        }
    }

    public GameState getState(Board board) {
        if(board instanceof TicTacToeBoard) {
            TicTacToeBoard b = (TicTacToeBoard) board;
            for(Rule r : ruleMap.get(TicTacToeBoard.class.getName()).ruleList){
                GameState gameState = r.condition.apply(b);
                if(gameState.isOver()){
                    return gameState;
                }
            }

            return new GameState(false, "-");
        }
        else {
            return new GameState(false, "-");
        }
    }
}


