package com.example.booking.config;


import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    GroupedOpenApi company() {
        return GroupedOpenApi.builder().group("company").pathsToMatch("/company/**").build();
    }


    @Bean
    GroupedOpenApi booking() {
        return GroupedOpenApi.builder().group("booking").pathsToMatch("/booking/**").build();
    }

    @Bean
    GroupedOpenApi test() {
        return GroupedOpenApi.builder().group("test").pathsToMatch("/test/**").build();
    }
}

