package api;

import command.implementations.SendEmailCommand;
import game.User;

public class EmailService {

    public void sendEmail(User user, String message) {
        // logic to send an email
    }

    public void execute(SendEmailCommand sendEmailCommand) {
        sendEmail(sendEmailCommand.getReceiver(), sendEmailCommand.getMessage());
    }
}
