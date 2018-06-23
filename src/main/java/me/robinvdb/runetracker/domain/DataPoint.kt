package me.robinvdb.runetracker.domain

import javax.persistence.*
import javax.validation.constraints.NotNull
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Entity
data class DataPoint(
        @Id
        @GeneratedValue
        val id: Long? = null,

        @NotNull
        val dateTime: LocalDateTime = LocalDateTime.now(),

        @ManyToOne(fetch = FetchType.LAZY)
        @NotNull
        val user: RunescapeUser? = null,

        @OneToMany(cascade = [(CascadeType.ALL)])
        val stats: Set<Stat> = emptySet()
) {
    val formatedDateTime: String
        get() = dateTime.format(dateTimeFormatter)

    companion object {
        private val dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/YYYY HH:mm:ss")
    }
}
