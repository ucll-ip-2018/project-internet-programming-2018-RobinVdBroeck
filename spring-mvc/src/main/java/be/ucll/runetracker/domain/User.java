package be.ucll.runetracker.domain;

public class User {
    private String email;
    private String displayName;

    private User() {
        this(null);
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

    public String setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
