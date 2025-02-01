package command.implementations;

import game.User;

public class NotificationDetails {
     User user;
     String message;

    public NotificationDetails(User user, String message) {
        this.user = user;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public User getReceiver() {
        return user;
    }
}
