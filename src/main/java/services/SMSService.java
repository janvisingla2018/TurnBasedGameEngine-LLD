package services;

import command.implementations.SMSCommand;
import game.User;

public class SMSService {
    private void sendSMS(User user, String message) {

    }

    public Void send(SMSCommand command) {
        sendSMS(command.getReceiver(), command.getMessage());
        return null;
    }

}
