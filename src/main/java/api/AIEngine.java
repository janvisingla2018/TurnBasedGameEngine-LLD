package api;

import boards.TicTacToeBoard;
import game.*;

public class AIEngine {
    public Move suggestMove(Board board, Player computer) {
        if (board instanceof TicTacToeBoard) {
            TicTacToeBoard ticTacToeBoard = (TicTacToeBoard) board;
            Move suggestion;
            int threshold = 3;
            if (countMoves(ticTacToeBoard) < threshold) {
                suggestion = getBasicMove(computer, ticTacToeBoard);
            } else {
                suggestion = getSmartMove(computer, ticTacToeBoard);
            }
            if (suggestion != null)
                return suggestion;
            throw new IllegalStateException("No such move");
        } else {
            throw new IllegalArgumentException("Invalid type");
        }
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

    private Move getSmartMove(Player player, TicTacToeBoard ticTacToeBoard) {
        RuleEngine ruleEngine = new RuleEngine();

        //Attacking moves
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(ticTacToeBoard.getSymbol(i, j) == null){
                    Move move = new Move(new Cell(i, j), player);
                    TicTacToeBoard boardCopy = ticTacToeBoard.copy();
                    boardCopy.move(move);
                    if(ruleEngine.getState(boardCopy).isOver()) {
                        return move;
                    }
                }
            }
        }

        //Defensive moves
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(ticTacToeBoard.getSymbol(i, j) == null){
                    Move move = new Move(new Cell(i, j), player.flip());
                    TicTacToeBoard boardCopy = ticTacToeBoard.copy();
                    boardCopy.move(move);
                    if(ruleEngine.getState(boardCopy).isOver()) {
                        return new Move(new Cell(i, j), player);
                    }
                }
            }
        }

        return getBasicMove(player, ticTacToeBoard);
    }

    public Move getBasicMove(Player computer, TicTacToeBoard ticTacToeBoard) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (ticTacToeBoard.getSymbol(i, j) == null) {
                    return new Move(new Cell(i, j), computer);
                }
            }
        }
        return null;
    }
}
