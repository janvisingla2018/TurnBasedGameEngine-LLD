package boards;

import api.Rule;
import api.RuleSet;
import game.Cell;
import game.GameState;
import game.Move;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

public class TicTacToeBoard implements CellBoard {
    private String cells[][] = new String[3][3];
    private History history = new History();

    @Override
    public String getSymbol(int row, int col) {
        return cells[row][col];
    }

    public void setCell(Cell cell, String symbol) {
        if(cells[cell.getRow()][cell.getCol()] == null) {
            cells[cell.getRow()][cell.getCol()] = symbol;
        }else {
            throw new IllegalArgumentException("Cell is already occupied");
        }
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                result += cells[i][j] + " ";
            }
            result += "\n";
        }
        return result;
    }

    @Override
    public TicTacToeBoard move(Move move) {
        history.add(this);
        TicTacToeBoard board = copy();
        board.setCell(move.getCell(), move.getPlayer().symbol());
        return board;
    }

    @Override
    public TicTacToeBoard copy() {
        TicTacToeBoard board = new TicTacToeBoard();
        for (int i = 0; i < 3; i++) {
            System.arraycopy(cells[i], 0, board.cells[i], 0, 3);
        }
        return board;
    }

    public static RuleSet getRules() {
        RuleSet rules = new RuleSet();
        rules.add(new Rule((board) -> outerTraversals((i, j) -> board.getSymbol(i, j))));
        rules.add(new Rule((board) -> outerTraversals((i, j) -> board.getSymbol(j, i))));
        rules.add(new Rule((board) -> traverse(i -> board.getSymbol(i, i))));
        rules.add(new Rule((board) -> traverse(i -> board.getSymbol(i, 2 - i))));
        rules.add(new Rule((board) -> {
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
        return rules;
    }

    private static GameState outerTraversals(BiFunction<Integer, Integer, String> next) {
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

    private static GameState traverse(Function<Integer, String> traversal) {
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

class History {
    List<Board> boards = new ArrayList<>();

    public Board getBoardAtMove(int moveIndex) {
        moveIndex = moveIndex - 1;
        int initialSize = boards.size();
        for(int i = 0; i < initialSize - (moveIndex + 1); i++) {
            boards.remove(boards.size() - 1);
        }
        return boards.get(moveIndex);
    }

    public Board undo() {
        boards.remove(boards.size() - 1);
        return boards.get(boards.size() - 1);
    }

    public void add(Board board) {
        boards.add(board);
    }
}