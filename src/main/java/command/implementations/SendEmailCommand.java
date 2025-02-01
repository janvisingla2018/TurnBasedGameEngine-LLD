package command.implementations;

import game.User;

public class SendEmailCommand {
    private User receiver;
    private String message;
    private String link;
    private String templateId;
    private String templateString;

    public SendEmailCommand(User receiver, String message, String link, String templateId, String templateString) {
        this.receiver = receiver;
        this.message = message;
        this.link = link;
        this.templateId = templateId;
        this.templateString = templateString;
    }

    public String getMessage() {
        return message;
    }

    public User getReceiver() {
        return receiver;
    }
}
