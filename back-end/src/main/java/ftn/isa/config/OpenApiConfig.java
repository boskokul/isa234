package ftn.isa.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

/*
konfiguracija omogucava generisanje metapodataka API-ju, poput vlasnika, uslova koriscenja, licence i sl.
moguce je konfigurisati i vise servera
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openApi() {
        ArrayList<Server> servers = new ArrayList<>(3);
        servers.add(new Server().url("http://localhost:8084/").description("development server"));
        servers.add(new Server().url("http://qa:8085/").description("test server"));
        servers.add(new Server().url("http://www.rest-example.com/").description("production server"));

        return new OpenAPI()
                .info(new Info()
                        .title("Rest example")
                        .description("Rest example with OpenAPI documentation")
                        .version("v1.0")
                        .contact(new Contact()      // kontakt informacije
                                .name("FTN")
                                .url("https://github.com/stojkovm")     // mora biti u url formatu
                                .email("ftn@uns.ac.rs"))
                        .termsOfService("TOC")
                        .license(new License().name("License").url("#"))
                )
                .servers(servers)
                .addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"))
                .components(new Components().addSecuritySchemes("Bearer Authentication", createAPIKeyScheme()))
                .info(new Info().title("My REST API")
                .description("Some custom description of API.")
                .version("1.0")
                .license(new License().name("License of API")
                        .url("API license URL")));
    }
    private SecurityScheme createAPIKeyScheme() {
        return new SecurityScheme().type(SecurityScheme.Type.HTTP)
                .bearerFormat("JWT")
                .scheme("bearer");
    }
}
