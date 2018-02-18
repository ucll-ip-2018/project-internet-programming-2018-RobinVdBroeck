package be.ucll.runetracker.domain;

import java.time.LocalDateTime;

public class DisplayName {
    private String name;
    private LocalDateTime creationDateTime;
    private LocalDateTime deletionDateTime;

    private DisplayName() {}

    public DisplayName(String name, LocalDateTime creationDateTime) {
        this(name, creationDateTime, null);
    }

    public DisplayName(String name, LocalDateTime creationDateTime, LocalDateTime deletionDateTime) {
        setName(name);
        setCreationDateTime(creationDateTime);
        setDeletionDateTime(deletionDateTime);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(LocalDateTime creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public LocalDateTime getDeletionDateTime() {
        return deletionDateTime;
    }

    public void setDeletionDateTime(LocalDateTime deletionDateTime) {
        this.deletionDateTime = deletionDateTime;
    }
}
