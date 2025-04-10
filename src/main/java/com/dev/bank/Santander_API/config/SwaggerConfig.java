package com.dev.bank.Santander_API.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .servers(List.of(
                        new Server().url("http://localhost:8080").description("Servidor local"),
                        new Server().url("M2/api-ocs/public-spls").description("Caminho base da API")
                ))
                .info(new Info()
                        .title("API de aplicativo de Banco")
                        .version("1.0")
                        .description("API desenvolvida por Davi Rosa Gomes!")
                        .termsOfService("https://seusite.com/termos")
                        .license(new License()
                                .name("MIT")
                                .url("https://opensource.org/licenses/MIT"))
                        .contact(new Contact()
                                .name("Davi")
                                .url("https://github.com/DaviRGomes")
                                .email("davirosagomes.eng@gmail.com")));
    }

    @Bean
    public GroupedOpenApi allApis() {
        return GroupedOpenApi.builder()
                .group("all-apis")
                .packagesToScan("com.dev.bank.Santander_API.controller")
                .pathsToMatch("/**")
                .build();
    }
}
