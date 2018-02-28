package be.ucll.runetracker.domain;

import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Skill {
    @Id
    @NotEmpty
    @NotNull
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
