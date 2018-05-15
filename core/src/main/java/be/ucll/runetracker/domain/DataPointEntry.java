package be.ucll.runetracker.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
public class DataPointEntry {
    @Id
    @GeneratedValue
    private Integer id;

    @Min(1)
    private short level;

    @Enumerated
    @NotNull
    private Skill skill;

    @PositiveOrZero
    @NotNull
    private int experience;

    @Positive
    private int rank;

    public DataPointEntry() {
    }

    public DataPointEntry(Skill skill, int rank, short level, int experience) {
        setSkill(skill);
        setRank(rank);
        setLevel(level);
        setExperience(experience);
    }


    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public short getLevel() {
        return level;
    }

    public void setLevel(short level) {
        this.level = level;
    }

    public String toString() {
        return getSkill() + " - Level: " + getLevel() + " Rank: " + getRank() + " exp:" + getExperience();
    }
}
