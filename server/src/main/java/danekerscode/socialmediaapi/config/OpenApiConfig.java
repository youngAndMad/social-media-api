package danekerscode.socialmediaapi.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(info = @Info(contact = @Contact(name = "Daneker", email = "kkraken2005@gmail.com", url = "t.me/youngAndMad"), description = "OpenApi documentation for Moodle clone", title = "OpenApi specification - Moodle", version = "1.0", license = @License(name = "Licence name", url = "https://some-url.com"), termsOfService = "Terms of service"), servers = {
        @Server(description = "Local ENV", url = "http://localhost:8080"),
        @Server(description = "PROD ENV", url = "to do")
}, security = {
        @SecurityRequirement(name = "bearerAuth")
})
@SecurityScheme(name = "Jwt Token", description = "JWT auth token", scheme = "bearer", type = SecuritySchemeType.HTTP, bearerFormat = "JWT", in = SecuritySchemeIn.HEADER)
public class OpenApiConfig {
}
