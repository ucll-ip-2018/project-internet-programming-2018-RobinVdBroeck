package be.ucll.runetracker.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
public class DataPoint {
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/YYYY HH:mm:ss");
    @Id
    @GeneratedValue
    private Integer id;
    private LocalDateTime dateTime;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<DataPointEntry> entries;

    @ManyToOne
    @NotNull
    private RunescapeUser user;


    public DataPoint() {
    }

    public DataPoint(int id, LocalDateTime dateTime, RunescapeUser user, Collection<DataPointEntry> entries) {
        setId(id);
        setDateTime(dateTime);
        setUser(user);
        setEntries(entries);
    }

    public static DataPoint createWithUserAndStats(RunescapeUser user, Collection<DataPointEntry> entries) {
        DataPoint dataPoint = new DataPoint();
        dataPoint.setUser(user);
        dataPoint.setDateTime(LocalDateTime.now());
        dataPoint.setEntries(entries);
        return dataPoint;
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

    public RunescapeUser getUser() {
        return user;
    }

    public void setUser(RunescapeUser user) {
        this.user = user;
    }

    public void setEntries(Set<DataPointEntry> entries) {
        this.entries = entries;
    }

    public void setEntries(Collection<DataPointEntry> entries) {
        if (this.entries == null) {
            this.entries = new HashSet<>();
        } else {
            this.entries.clear();
        }
        this.entries.addAll(entries);
    }

    public Collection<DataPointEntry> getEntries() {
        return entries;
    }
}
