package command.builder;

import command.implementations.EmailCommand;
import events.Event;
import game.User;

public class EmailCommandBuilder {
    NotificationBuilder notificationBuilder = new NotificationBuilder();
    String link;
    String templateId;
    String templateString;

    public EmailCommandBuilder link(String link) {
        this.link = link;
        return this;
    }

    public EmailCommandBuilder user(User user) {
        notificationBuilder.user(user);
        return this;
    }

    public EmailCommandBuilder message(String message) {
        notificationBuilder.message(message);
        return this;
    }

    public EmailCommand build() {
        return new EmailCommand(notificationBuilder.build(), link);
    }
}
