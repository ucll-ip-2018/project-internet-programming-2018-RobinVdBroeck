package me.robinvdb.runetracker.web.config

import me.robinvdb.runetracker.services.HighScoresService
import me.robinvdb.runetracker.services.HighScoresServiceImpl
import me.robinvdb.runetracker.database.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ApplicationConfig {
    @Bean
    fun runescapeUserDatabase(): RunescapeUserDatabase {
        return RunescapeUserDatabaseJPA()
    }

    @Bean
    fun dataPointDatabase(): DataPointDatabase {
        return DataPointDatabaseJPA()
    }

    @Bean
    fun dataPointService(
            @Autowired runescapeUserDatabase: RunescapeUserDatabase,
            @Autowired dataPointDatabase: DataPointDatabase
    ): DatabaseService {
        return DatabaseService(runescapeUserDatabase, dataPointDatabase)
    }


    @Bean
    fun highScoresService(): HighScoresService {
        return HighScoresServiceImpl()
    }
}
