package me.robinvdb.runetracker.domain

import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import java.util.*

@Entity
data class RunescapeUser(
        @Id
        @GeneratedValue
        var id: Int? = null,

        @Email(message = "Email should be valid")
        @NotNull(message = "Email cannot be null")
        @NotEmpty
        @Column(unique = true)
        var email: String = "",

        @NotNull
        @NotEmpty
        @Column(unique = true)
        var displayName: String = "",

        @OneToMany(mappedBy = "user", cascade = [(CascadeType.ALL)])
        var dataPoints: MutableSet<DataPoint> = mutableSetOf()
) {
    fun addDataPoint(point: DataPoint) {
        this.dataPoints.add(point)
    }
}
