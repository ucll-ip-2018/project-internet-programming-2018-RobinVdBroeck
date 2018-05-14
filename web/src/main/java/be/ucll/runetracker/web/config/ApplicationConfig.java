package be.ucll.runetracker.web.config;

import be.ucll.runetracker.database.*;
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
}
