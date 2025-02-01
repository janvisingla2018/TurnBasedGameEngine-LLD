package command.builder;

import command.implementations.SendEmailCommand;
import game.User;

public class SendEmailCommandBuilder {
    User receiver;
    String message;
    String link;
    String templateId;
    String templateString;

    public SendEmailCommandBuilder user(User user) {
        this.receiver = user;
        return this;
    }

    public SendEmailCommandBuilder message(String message) {
        this.message = message;
        return this;
    }

    public SendEmailCommandBuilder link(String link) {
        this.link = link;
        return this;
    }

    public SendEmailCommandBuilder templateId(String templateId) {
        this.templateId = templateId;
        return this;
    }

    public SendEmailCommandBuilder templateString(String templateString) {
        this.templateString = templateString;
        return this;
    }

    public SendEmailCommand build() {
        return new SendEmailCommand(receiver, message, link, templateId, templateString);
    }
}
