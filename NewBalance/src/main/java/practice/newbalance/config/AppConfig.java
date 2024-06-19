package practice.newbalance.config;


import jakarta.servlet.ServletContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import practice.newbalance.service.item.FileUtils;

@Configuration
public class AppConfig {

    @Bean
    public FileUtils fileUtils(){
        return new FileUtils();
    }
}
