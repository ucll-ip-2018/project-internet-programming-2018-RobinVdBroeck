package be.ucll.runetracker.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
public class DataPoint {
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/YYYY HH:mm:ss");
    @Id
    @GeneratedValue
    private Integer id;
    private LocalDateTime dateTime;

    @ManyToOne
    @NotNull
    private RunescapeUser user;

    @PositiveOrZero
    @NotNull
    private int experience;

    @Positive
    private int rank;

    public DataPoint() {
    }

    public DataPoint(int id, LocalDateTime dateTime, RunescapeUser user, int exp, int rank) {
        setId(id);
        setDateTime(dateTime);
        setUser(user);
        setExperience(exp);
        setRank(rank);
    }

    public Integer getId() {
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

    public RunescapeUser getUser() {
        return user;
    }

    public void setUser(RunescapeUser user) {
        this.user = user;
    }

}
