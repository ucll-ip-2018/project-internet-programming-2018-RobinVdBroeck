package be.ucll.runetracker.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
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
