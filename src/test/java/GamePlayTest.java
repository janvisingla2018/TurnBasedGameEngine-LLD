import api.*;
import game.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GamePlayTest {

    GameEngine gameEngine;
    RuleEngine ruleEngine;

    @Before
    public void setUp() {
        gameEngine = new GameEngine();
        ruleEngine = new RuleEngine();
    }

    @Test
    public void checkForRowWin() {
        Board board = gameEngine.start("TicTacToe");
        int moves[][] = new int[][]{{1,0},{1,1}, {1,2}};
        int secondPlayerMoves[][] = new int[][]{{0,0},{0,1}, {0,2}};
        playGame(board, moves, secondPlayerMoves);

        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals(ruleEngine.getState(board).getWinner(), "X");
    }

    @Test
    public void checkForColWin() {
        Board board = gameEngine.start("TicTacToe");
        int moves[][] = new int[][]{{0,0},{1,0}, {2,0}};
        int secondPlayerMoves[][] = new int[][]{{0,1},{0,2}, {1,1}};
        playGame(board, moves, secondPlayerMoves);

        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals(ruleEngine.getState(board).getWinner(), "X");
    }

    @Test
    public void checkForDiagWin() {
        Board board = gameEngine.start("TicTacToe");
        int moves[][] = new int[][]{{0,0},{1,1}, {2,2}};
        int secondPlayerMoves[][] = new int[][]{{0,1},{0,2}, {1,0}};
        playGame(board, moves, secondPlayerMoves);

        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals(ruleEngine.getState(board).getWinner(), "X");
    }

    @Test
    public void checkForRevDiagWin() {
        Board board = gameEngine.start("TicTacToe");
        int moves[][] = new int[][]{{0,2},{1,1}, {2,0}};
        int secondPlayerMoves[][] = new int[][]{{0,0},{0,1}, {1,0}};
        playGame(board, moves, secondPlayerMoves);

        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals(ruleEngine.getState(board).getWinner(), "X");
    }

    @Test
    public void checkForComputerWin() {
        Board board = gameEngine.start("TicTacToe");
        int moves[][] = new int[][]{{1,0},{1,1}, {2,0}};
        int secondPlayerMoves[][] = new int[][]{{0,0},{0,1}, {0,2}};
        playGame(board, moves, secondPlayerMoves);

        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals(ruleEngine.getState(board).getWinner(), "O");
    }

    public void playGame(Board board, int[][] firstPlayerMoves, int[][] secondPlayerMoves) {
        Player human = new Player("X"), computer = new Player("O");
        int next = 0;

        while(!ruleEngine.getState(board).isOver()){
            int row = firstPlayerMoves[next][0], col = firstPlayerMoves[next][1];
            Move humanMove = new Move(new Cell(row, col), human);
            gameEngine.move(board, humanMove);

            if(!ruleEngine.getState(board).isOver()) {
                int sRow = secondPlayerMoves[next][0], sCol = secondPlayerMoves[next][1];
                Move computerMove = new Move(new Cell(sRow, sCol), computer);
                gameEngine.move(board, computerMove);
            }
            next++;
        }
    }
}