package events;

import game.User;

public class Event {
    private User user;
    private String message;
    private String link;
    private String type;

    public Event(User user, String message, String link, String type) {
        this.user = user;
        this.message = message;
        this.link = link;
        this.type = type;
    }

    public Event(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
