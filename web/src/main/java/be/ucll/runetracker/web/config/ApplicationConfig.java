package be.ucll.runetracker.web.config;

import be.ucll.runetracker.database.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
    @Bean
    public UserDatabase userDatabase() {
        return new UserDatabaseStub();
    }

    @Bean
    public SkillDatabase skillDatabase() {
        return new SkillDatabaseStub();
    }

    @Bean
    public DataPointDatabase dataPointDatabase() {
        return new DataPointDatabaseStub();
    }

    @Bean
    public DataPointService dataPointService(
            @Autowired UserDatabase userDatabase,
            @Autowired DataPointDatabase dataPointDatabase) {
        return new DataPointService(userDatabase, dataPointDatabase);
    }
}
