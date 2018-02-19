package be.ucll.runetracker.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DataPoint {
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/YYYY HH:mm:ss");
    private int id;
    private LocalDateTime dateTime;
    private User user;
    private int experience;
    private int rank;

    public DataPoint(int id, LocalDateTime dateTime, User user, int exp, int rank) {
        setId(id);
        setDateTime(dateTime);
        setUser(user);
        setExperience(exp);
        setRank(rank);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

    public String getFormatedDateTime() {
        return getDateTime().format(dateTimeFormatter);
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
