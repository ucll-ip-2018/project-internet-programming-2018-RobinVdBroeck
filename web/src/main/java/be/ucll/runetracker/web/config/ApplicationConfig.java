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
    public SkillDatabase skillDatabase() {
        return new SkillDatabaseJPA();

    }

    @Bean
    public DataPointDatabase dataPointDatabase() {
        return new DataPointDatabaseJPA();

    }

    @Bean
    public DataPointService dataPointService(
            @Autowired RunescapeUserDatabase runescapeUserDatabase,
            @Autowired DataPointDatabase dataPointDatabase,
            @Autowired SkillDatabase skillDatabase
    ) {
        return new DataPointService(runescapeUserDatabase, dataPointDatabase, skillDatabase);
    }
}
