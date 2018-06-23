package me.robinvdb.runetracker.domain

import javax.persistence.*
import javax.validation.constraints.*

@Entity
class Stat(
        @Id
        @GeneratedValue
        var id: Long? = null,

        @NotNull
        var skill: Skill,

        @Positive
        var rank: Int,

        @Min(1)
        var level: Short,

        @PositiveOrZero
        var experience: Int
) {
    override fun toString(): String {
        return skill.toString() + " - Level: " + level + " Rank: " + rank + " exp:" + experience
    }
}
