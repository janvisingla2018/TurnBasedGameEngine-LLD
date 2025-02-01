package services;

import command.implementations.EmailCommand;
import game.User;

public class EmailService {
    private void sendEmail(User user, String message) {
    // logic to send email
    }

    public Void send(EmailCommand command) {
        sendEmail(command.getReceiver(), command.getMessage());
        return null;
    }
}
