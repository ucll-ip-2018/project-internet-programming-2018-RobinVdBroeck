package me.robinvdb.runetracker.web.config

import me.robinvdb.runetracker.services.HighScoresService
import me.robinvdb.runetracker.services.HighScoresServiceImpl
import me.robinvdb.runetracker.database.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EnableJpaRepositories
class ApplicationConfig {
    @Bean
    fun highScoresService(): HighScoresService {
        return HighScoresServiceImpl()
    }
}
