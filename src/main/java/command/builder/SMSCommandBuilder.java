package command.builder;

import command.implementations.SMSCommand;
import events.Event;
import game.User;

public class SMSCommandBuilder {
    NotificationBuilder notificationBuilder = new NotificationBuilder();
    String link;
    String templateId;
    String templateString;

    public SMSCommandBuilder user(User user) {
        notificationBuilder.user(user);
        return this;
    }

    public SMSCommandBuilder message(String message) {
        notificationBuilder.message(message);
        return this;
    }

    public SMSCommand build() {
        return new SMSCommand(notificationBuilder.build());
    }

}
