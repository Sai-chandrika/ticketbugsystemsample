package com.example.ticket_bug_system.security;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author chandrika
 * user
 * @ProjectName otp-sending-users
 * @since 29-09-2023
 */
@Configuration
public class SwaggerConfiguration {
      @Bean
      public OpenAPI customOpenApi(){
            return new OpenAPI().info(new Info().title("Ticket Bug Tracking System")
                            .version("3.0.8")).components(new Components().
                            addSecuritySchemes("bearer-key", new SecurityScheme().
                                    type(SecurityScheme.Type.HTTP)
                                    .scheme("bearer")
                                    .bearerFormat("JWT")))
                    .addSecurityItem(new SecurityRequirement().addList("bearer-key"));

      }
}
