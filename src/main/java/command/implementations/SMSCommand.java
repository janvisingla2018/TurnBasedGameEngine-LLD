package command.implementations;

import events.Event;
import game.User;

public class SMSCommand{
    NotificationDetails details;
    private String link;
    private String templateId;
    private String templateString;

    public SMSCommand(NotificationDetails details) {
        this.details = details;
    }

    public SMSCommand(Event event) {
        this.details = new NotificationDetails(event.getUser(), event.getMessage());
    }

    public String getMessage() {
        return details.getMessage();
    }

    public User getReceiver() {
        return details.getReceiver();
    }

}
