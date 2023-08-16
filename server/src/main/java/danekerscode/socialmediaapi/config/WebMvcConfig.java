package danekerscode.socialmediaapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    // @Value("${cors.allowedOrigins}")
    // private String allowedOrigins;

    @Value("${cors.headers}")
    private String headers;

    @Value("${cors.path.pattern}")
    private String pathPattern;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        final long MAX_AGE_SECS = 3600;

        registry.addMapping(pathPattern)
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders(headers)
                .maxAge(MAX_AGE_SECS);

    }

}
