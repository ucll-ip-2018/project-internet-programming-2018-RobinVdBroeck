package be.ucll.runetracker.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    private String email;
    private String displayName;

    public User() {

    }

    public User(String email) {
        setEmail(email);
    }

    public void setEmail(String email) {
        if (email.trim().isEmpty()) {
            throw new DomainException("Email should not be empty");
        }

        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setDisplayName(String displayName) {
        if (displayName.trim().isEmpty()) {
            throw new DomainException("DisplayName should not be empty");
        }

        this.displayName = displayName;
    }

    public String getDisplayName() {
        return this.displayName;
    }
}
