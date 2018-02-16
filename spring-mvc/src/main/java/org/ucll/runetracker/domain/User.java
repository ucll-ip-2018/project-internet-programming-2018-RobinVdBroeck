package org.ucll.runetracker.domain;

import java.util.*;
import java.util.stream.Collectors;

public class User {
    private String email;
    private List<DisplayName> displayNames = new ArrayList<>();

    private User() {
        this(null);
    }

    public User(String email) {
        setEmail(email);
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void addDisplayName(DisplayName displayName) {
        displayNames.add(displayName);
    }

    public Collection<DisplayName> getDisplayNames() {
        return displayNames;
    }

    public Optional<DisplayName> getCurrentDisplayName() {
        var sorted = sortedDisplayNames();
        return Optional.ofNullable(sorted.get(sorted.size() - 1));
    }

    private List<DisplayName> sortedDisplayNames() {
        return displayNames.stream()
                .sorted(Comparator.comparing(DisplayName::getCreationDateTime))
                .collect(Collectors.toUnmodifiableList());
    }
}
