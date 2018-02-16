package org.ucll.runetracker.domain;

import java.time.LocalDateTime;

public class DataPoint {
    private LocalDateTime dateTime;
    private User user;
    private int experience;
    private int rank;

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getExperience() {
        return this.experience;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getRank() {
        return this.rank;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
