package be.ucll.runetracker.domain;

import javax.persistence.Column;
import javax.persistence.Id;

public class Skill {
    @Id
    private String name;

    public Skill() {}

    public Skill(String name) {
        setName(name);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
