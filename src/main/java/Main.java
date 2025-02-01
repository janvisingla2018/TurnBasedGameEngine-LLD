import api.*;
import boards.Board;

import command.implementations.EmailCommand;
import command.implementations.SMSCommand;
import events.*;
import game.*;
import services.EmailService;
import services.SMSService;

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
        SMSService smsService = new SMSService();

        Player human = new Player("X");
        Player computer = new Player("O");

        EventBus eventBus = new EventBus();
        eventBus.subscribe(new Subscriber((event -> emailService.send(new EmailCommand(event)))));
        eventBus.subscribe(new Subscriber(event -> smsService.send(new SMSCommand(event))));

        GameFactory gameFactory = new GameFactory();
        if(human.getUser().activeAfter(10, DAYS)) {
            eventBus.publish(new Event(human.getUser(), "Congratulations!", "https://interviewready.io", "ACTIVITY"));
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
            eventBus.publish(new Event(human.getUser()));

        System.out.println("Game Over : " +ruleEngine.getState(board));
    }
}
