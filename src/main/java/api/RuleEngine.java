package api;

import boards.TicTacToeBoard;
import game.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

public class RuleEngine {

    Map<String, List<Rule<TicTacToeBoard>>> ruleMap = new HashMap<>();

    public RuleEngine() {
        String key = TicTacToeBoard.class.getName();
        ruleMap.put(key, new ArrayList<>());
        ruleMap.get(key).add(new Rule<>((board) -> outerTraversals((i, j) -> board.getSymbol(i, j))));
        ruleMap.get(key).add(new Rule<>((board) -> outerTraversals((i, j) -> board.getSymbol(j, i))));
        ruleMap.get(key).add(new Rule<>((board) -> traverse(i -> board.getSymbol(i, i))));
        ruleMap.get(key).add(new Rule<>((board) -> traverse(i -> board.getSymbol(i, 2 - i))));
        ruleMap.get(key).add(new Rule<>((board) -> {
            int countOfFilledCells = 0;
            for(int i=0; i<3 ; i++){
                for(int j=0; j<3 ; j++){
                    if(board.getSymbol(i, j) != null){
                        countOfFilledCells++;
                    }
                }
            }
            if(countOfFilledCells == 9){
                return new GameState(true, "-");
            }
            return new GameState(false, "-");
        }));
    }

    public GameInfo getInfo(Board board){
        if(board instanceof TicTacToeBoard){
            GameState gameState = getState(board);
            final String[] players = new String[]{"X", "O"};
            for(int index = 0; index < players.length; index++){
                for(int i=0; i<3; i++){
                    for(int j=0; j<3; j++){
                        Board b = board.copy();
                        Player player = new Player(players[index]);
                        b.move(new Move(new Cell(i, j), player));
                        boolean canStillWin = false;
                        for(int k=0; k<3; k++){
                            for(int l=0; l<3; l++){
                                Board b1 = b.copy();
                                b1.move(new Move(new Cell(k, l), player.flip()));
                                if(getState(b1).getWinner().equals(player.flip().symbol())){
                                    canStillWin = true;
                                    break;
                                }
                            }
                            if(canStillWin)
                                break;
                        }
                        if(canStillWin)
                            return new GameInfoBuilder()
                                    .isOver(gameState.isOver())
                                    .winner(gameState.getWinner())
                                    .hasFork(true)
                                    .player(player.flip())
                                    .build();
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
            for(Rule<TicTacToeBoard> r : ruleMap.get(TicTacToeBoard.class.getName())){
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

    private GameState outerTraversals(BiFunction<Integer, Integer, String> next) {
        GameState result = new GameState(false, "-");
        for(int i=0; i<3 ; i++){
            final int ii = i;
            GameState traversal = traverse(j -> next.apply(ii, j));
            if(traversal.isOver()) {
                result = traversal;
                break;
            }
        }
        return result;
    }

    private GameState traverse(Function<Integer, String> traversal) {
        GameState result = new GameState(false, "-");
        boolean possibleStreak = true;
        for(int j = 0; j <3 ; j++) {
            if (traversal.apply(j) == null || !traversal.apply(0).equals(traversal.apply(j))) {
                possibleStreak = false;
                break;
            }
        }
        if(possibleStreak)
            result = new GameState(true, traversal.apply(0));
        return result;
    }
}


class Rule<T extends Board> {
    Function<T, GameState> condition;

    public Rule(Function<T, GameState> condition) {
        this.condition = condition;
    }
}
