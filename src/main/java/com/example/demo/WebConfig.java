import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Apply CORS to all endpoints
                .allowedOrigins("http://localhost:8081", "http://localhost:8082") // Allow multiple origins
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Add allowed methods
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
