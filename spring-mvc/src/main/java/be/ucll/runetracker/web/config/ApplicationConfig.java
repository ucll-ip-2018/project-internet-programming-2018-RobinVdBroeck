package be.ucll.runetracker.web.config;

import be.ucll.runetracker.database.DatabaseType;
import be.ucll.runetracker.domain.DataPointService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
    @Bean
    public DataPointService dataPointService() {
        return new DataPointService(DatabaseType.STUB);
    }
}
