package api;

import java.util.*;
import java.util.function.Consumer;

public class RuleSet implements Iterable<Rule> {
    Set<Rule> ruleList = new HashSet<>();

    public void add(Rule boardRule) {
        ruleList.add(boardRule);
    }

    @Override
    public Iterator<Rule> iterator() {
        return ruleList.iterator();
    }

    @Override
    public void forEach(Consumer<? super Rule> action) {
        ruleList.forEach(action);
    }

    @Override
    public Spliterator<Rule> spliterator() {
        return ruleList.spliterator();
    }
}
