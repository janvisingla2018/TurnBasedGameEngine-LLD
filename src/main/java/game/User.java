package game;

import java.util.concurrent.TimeUnit;

public class User {
    private String id;
    private long lastActiveTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean activeAfter(int threshold, TimeUnit timeUnit) {
        return System.currentTimeMillis() - lastActiveTime > timeUnit.toMillis(threshold);
    }
}
