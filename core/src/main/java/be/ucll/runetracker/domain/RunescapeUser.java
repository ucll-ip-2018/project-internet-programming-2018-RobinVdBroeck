package be.ucll.runetracker.domain;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
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

    @OneToMany
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

    public Set<DataPoint> getDataPoints() {
        return dataPoints;
    }

    public void setDataPoints(Collection<DataPoint> datapoints) {
        if(this.dataPoints == null) {
            this.dataPoints = new HashSet<>();
        } else {
            this.dataPoints.clear();
        }
        this.dataPoints.addAll(datapoints);
    }

    public void setDataPoints(Set<DataPoint> dataPoints) {
        this.dataPoints = dataPoints;
    }
}
