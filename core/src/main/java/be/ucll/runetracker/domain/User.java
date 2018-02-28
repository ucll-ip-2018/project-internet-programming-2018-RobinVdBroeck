package be.ucll.runetracker.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class User {
    @Id
    @Email(message = "Email should be valid")
    @NotNull(message = "Email cannot be null")
    @NotEmpty
    private String email;

    @NotNull
    @NotEmpty
    private String displayName;

    public User() {

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

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return this.displayName;
    }
}
