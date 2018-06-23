package me.robinvdb.runetracker.domain

import javax.persistence.*
import javax.validation.constraints.NotNull
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.HashSet

@Entity
data class DataPoint(
        @Id
        @GeneratedValue
        var id: Int? = null,

        @NotNull
        var dateTime: LocalDateTime = LocalDateTime.now(),

        @ManyToOne(fetch = FetchType.LAZY)
        @NotNull
        var user: RunescapeUser? = null,

        @OneToMany(cascade = [(CascadeType.ALL)])
        var entries: Collection<DataPointEntry> = mutableSetOf()
) {
    val formatedDateTime: String
        get() = dateTime.format(dateTimeFormatter)

    companion object {
        private val dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/YYYY HH:mm:ss")
    }
}
