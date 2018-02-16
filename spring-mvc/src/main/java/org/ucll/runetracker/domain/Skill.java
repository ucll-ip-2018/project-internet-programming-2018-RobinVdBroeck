package org.ucll.runetracker.domain;

public class Skill {
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