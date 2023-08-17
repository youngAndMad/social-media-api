package danekerscode.socialmediaapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebMvcConfig {

    @Value("${spring.mvc.allowed.headers}")
    private String allowedHeaders;

    @Value("${spring.mvc.allowed.methods}")
    private String allowedMethods;

    @Value("${spring.mvc.allowed.credentials}")
    private Boolean allowedCredentials;

    @Value("${spring.mvc.mapping}")
    private String mapping;

    @Value("${spring.mvc.allowed.origin-patterns}")
    private String originPatterns;

    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                System.out.println("hii");
                registry.addMapping(mapping)
                        .allowCredentials(allowedCredentials)
                        .allowedOriginPatterns(originPatterns)
                        .allowedHeaders(allowedHeaders)
                        .allowedMethods(allowedMethods);
            }
        };
    }

}
