package eshopping.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
 
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/prod").allowedOrigins("http://127.0.0.1:5500");
        registry.addMapping("/prod/create").allowedOrigins("http://127.0.0.1:5500");
    }
}

