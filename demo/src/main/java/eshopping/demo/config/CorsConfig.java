package eshopping.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
    // andrew
        registry.addMapping("/prod").allowedOrigins("http://127.0.0.1:8081");
        registry.addMapping("/prod/create").allowedOrigins("http://127.0.0.1:8081");
        registry.addMapping("/api/v1/auth/authenticate").allowedOrigins("http://127.0.0.1:8081");
        registry.addMapping("/api/v1/auth/register").allowedOrigins("http://127.0.0.1:8081");

    // anas
        // registry.addMapping("/prod").allowedOrigins("http://127.0.0.1:5500");
        // registry.addMapping("/prod/create").allowedOrigins("http://127.0.0.1:5500");
        // registry.addMapping("/api/v1/auth/authenticate").allowedOrigins("http://127.0.0.1:5500");
        // registry.addMapping("/api/v1/auth/register").allowedOrigins("http://127.0.0.1:5500");

    }
}
