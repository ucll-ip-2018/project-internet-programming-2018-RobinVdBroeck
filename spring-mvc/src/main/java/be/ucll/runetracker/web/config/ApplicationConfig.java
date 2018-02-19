package be.ucll.runetracker.web.config;

import be.ucll.runetracker.DataPointService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
    @Bean
    public DataPointService dataPointService() {
        return new DataPointService();
    }
}
