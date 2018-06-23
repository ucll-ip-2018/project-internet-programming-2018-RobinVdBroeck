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
        val id: Long? = null,

        @Email(message = "Email should be valid")
        @NotNull(message = "Email cannot be null")
        @NotEmpty
        @Column(unique = true)
        val email: String = "",

        @NotNull
        @NotEmpty
        @Column(unique = true)
        val displayName: String = "",

        @OneToMany(mappedBy = "user", cascade = [(CascadeType.ALL)])
        val dataPoints: Set<DataPoint> = emptySet()
)
