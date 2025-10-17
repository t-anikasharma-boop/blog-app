package com.blogApplication.blogapis.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ForwardedHeaderFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@Configuration
@OpenAPIDefinition(info = @Info(title = "Theta Spring Boot", description = "Theta Framework in Spring Boot", version = "v1", license = @License(name = "Flexidev", url = "https://www.flexidev.co")))
public class SwaggerConfig  {

   public static final String AUTHORIZATION_HEADER = "Authorization";

//    private ApiInfo getInfo() {
//        return new ApiInfo("Blogging Application: Backend",
//                "This Project is Developed by Prashant Tripathi",
//                "1.0",
//                "Free to use",
//                new Contact("Prashant Tripathi",
//                        "https://github.com/Pt1234567?tab=repositories",
//                        "tripathiprashant45678@gamil.com"),
//                "API License",
//                "API License URL",
//                Collections.emptyList());
//    }



        // Access local: http://localhost:9090/theta/swagger-ui/index.html
        @Bean
        public OpenAPI customOpenAPI() {
            return new OpenAPI()
                    .addSecurityItem(new SecurityRequirement().addList(AUTHORIZATION_HEADER))
                    .components(new Components().addSecuritySchemes(AUTHORIZATION_HEADER,
                            new io.swagger.v3.oas.models.security.SecurityScheme().type(io.swagger.v3.oas.models.security.SecurityScheme.Type.HTTP).scheme("Bearer").bearerFormat("JWT")));
        }

        @Bean
        ForwardedHeaderFilter forwardedHeaderFilter() {
            return new ForwardedHeaderFilter();
        }

}
