package com.tvm.staff_management_system.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class OpenApiConfig {
    @Profile({
            "dev",
            "test",
            "prod"
    })

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Staff Management System")
                        .description("API documentation for Staff Management System")
                        .version("1.0"));
    }
}
