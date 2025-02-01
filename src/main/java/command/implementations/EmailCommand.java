package command.implementations;

import events.Event;
import game.User;

public class EmailCommand {
    NotificationDetails details;
    String link;
    String templateId;
    String templateString;

    public EmailCommand(NotificationDetails details, String link) {
        this.details = details;
        this.link = link;
    }

    public EmailCommand(Event event) {
        this.details = new NotificationDetails(event.getUser(), event.getMessage());
        this.link = event.getLink();
    }

    public NotificationDetails getDetails() {
        return details;
    }

    public String getMessage() {
        return details.getMessage();
    }

    public User getReceiver() {
        return details.getReceiver();
    }
}
