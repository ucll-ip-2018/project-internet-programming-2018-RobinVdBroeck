package me.robinvdb.runetracker.domain;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity(name = "RunescapeUser")
public class RunescapeUser {
    @Id
    @GeneratedValue
    private Integer id;

    @Email(message = "Email should be valid")
    @NotNull(message = "Email cannot be null")
    @NotEmpty
    @Column(unique = true)
    private String email;

    @NotNull
    @NotEmpty
    @Column(unique = true)
    private String displayName;

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL
    )
    private Set<DataPoint> dataPoints;

    public RunescapeUser() {
    }

    public RunescapeUser(String email, String displayName, Collection<DataPoint> dataPoints) {
        setDisplayName(displayName);
        setEmail(email);
        setDataPoints(dataPoints);
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public void addDataPoint(DataPoint point) {
        this.dataPoints.add(point);
    }

    public void setDataPoints(Collection<DataPoint> datapoints) {
        if (this.dataPoints == null) {
            this.dataPoints = new HashSet<>();
        } else {
            datapoints.clear();
        }
        this.dataPoints.addAll(datapoints);
    }

    public Collection<DataPoint> getDataPoints() {
        return dataPoints;
    }
}