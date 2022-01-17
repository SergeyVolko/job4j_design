package ru.job4j.question;

import java.util.HashSet;
import java.util.Set;

public class Analize {
    public static Info diff(Set<User> previous, Set<User> current) {
        int prevLength = previous.size();
        int currLength = current.size();
        Set<User> union = new HashSet<>(previous);
        union.addAll(current);
        int unionLength = union.size();
        int changed = unionLength - (int) union.stream()
                .map(User::getId)
                .distinct()
                .count();
        int added = unionLength - prevLength - changed;
        int deleted = prevLength - currLength + added;
        return new Info(added, changed, deleted);
    }
}
