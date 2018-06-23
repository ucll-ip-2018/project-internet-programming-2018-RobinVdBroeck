package me.robinvdb.runetracker.web.config;

import me.robinvdb.runetracker.services.HighScoresService;
import me.robinvdb.runetracker.services.HighScoresServiceImpl;
import me.robinvdb.runetracker.database.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
    @Bean
    public RunescapeUserDatabase runescapeUserDatabase() {
        return new RunescapeUserDatabaseJPA();
    }

    @Bean
    public DataPointDatabase dataPointDatabase() {
        return new DataPointDatabaseJPA();
    }

    @Bean
    public DatabaseService dataPointService(
            @Autowired RunescapeUserDatabase runescapeUserDatabase,
            @Autowired DataPointDatabase dataPointDatabase
    ) {
        return new DatabaseService(runescapeUserDatabase, dataPointDatabase);
    }


    @Bean
    public HighScoresService highScoresService() {
        return new HighScoresServiceImpl();
    }
}
