import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Apply to all endpoints
                .allowedOrigins("http://localhost:8082")  // Allow requests from localhost:8082 (your frontend)
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // Allow the HTTP methods needed
                .allowedHeaders("*")  // Allow all headers
                .allowCredentials(true);  // Allow credentials if needed (like cookies or HTTP authentication)
    }
}
