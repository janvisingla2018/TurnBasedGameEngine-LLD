import api.AIEngine;
import api.GameEngine;
import api.RuleEngine;
import boards.Board;
import game.Cell;
import game.Move;
import game.Player;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GameEngine gameEngine = new GameEngine();
        AIEngine aiEngine = new AIEngine();
        Board board = gameEngine.start("TicTacToe");
        RuleEngine ruleEngine = new RuleEngine();

        Player human = new Player("X");
        Player computer = new Player("O");

        //make moves
        while(!ruleEngine.getState(board).isOver()){
            System.out.println("Make you move");
            System.out.println(board);
            int row = sc.nextInt(), col = sc.nextInt();
            Move humanMove = new Move(new Cell(row, col), human);
            gameEngine.move(board, humanMove);
            System.out.println(board);

            if(!ruleEngine.getState(board).isOver()) {
                Move computerMove = aiEngine.suggestMove(board, computer);
                gameEngine.move(board, computerMove);
            }
        }

        System.out.println("Game Over : " +ruleEngine.getState(board));
    }
}
