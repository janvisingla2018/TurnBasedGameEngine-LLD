import api.*;
import boards.Board;
import command.builder.SendEmailCommandBuilder;
import game.*;

import java.util.Scanner;

import static java.util.concurrent.TimeUnit.DAYS;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GameEngine gameEngine = new GameEngine();
        AIEngine aiEngine = new AIEngine();
        Board board = gameEngine.start("TicTacToe");
        RuleEngine ruleEngine = new RuleEngine();
        EmailService emailService = new EmailService();

        Player human = new Player("X");
        Player computer = new Player("O");

        GameFactory gameFactory = new GameFactory();
        Game game = gameFactory.createGame();
        if(human.getUser().activeAfter(10, DAYS)) {
            emailService.execute(new SendEmailCommandBuilder()
                                .user(human.getUser())
                                .message("We are glad you are back!")
                                .build()
            );
        }
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

        if(ruleEngine.getState(board).getWinner().equals(human.symbol()))
            emailService.execute(new SendEmailCommandBuilder()
                    .user(human.getUser())
                    .message("Congratulations on the win!!")
                    .build()
            );

        System.out.println("Game Over : " +ruleEngine.getState(board));
    }
}
