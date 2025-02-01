package events;

import java.util.function.Function;

public class Subscriber {
    private final Function<Event, Void> function;

    public Subscriber(Function<Event, Void> function) {
        this.function = function;
    }
}
