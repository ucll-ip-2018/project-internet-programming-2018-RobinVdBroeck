package  me.robinvdb.runetracker

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RunetrackerApplication

fun main(args: Array<String>) {
    runApplication<RunetrackerApplication>(*args)
}
