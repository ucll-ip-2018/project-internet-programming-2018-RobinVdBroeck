package be.ucll.runetracker.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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

    public RunescapeUser() {

    }

    public RunescapeUser(String email) {
        setEmail(email);
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

}
