package com.riwi.LibrosYa.infrastructure.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Library management system", version = "1.0", description = "Api rest to loan and reservate books to the users"))
public class OpenApiConfig {

}
