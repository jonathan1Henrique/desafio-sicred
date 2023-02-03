package com.desafio.pauta.swagger;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Optional;

@Configuration
@EnableSwagger2
public class Swagger {

    @Value("localhost:8080")
    private String swaggerPath;

    @Bean
    public Docket postsApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.desafio.pauta.controllers"))
                .build()
                .ignoredParameterTypes(ApiIgnore.class,
                        ResponseEntity.class,
                        Page.class,
                        Pageable.class,
                        Sort.class)
                .enableUrlTemplating(false)
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                .genericModelSubstitutes(Optional.class);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Pauta API")
                .description("API de Pautas")
                .termsOfServiceUrl("")
                .licenseUrl("none")
                .version("1.0")
                .contact(new Contact("Jonathan Herique",
                        "https://github.com/jonathan1Henrique",
                        "jonathan12jhon@gmail.com"))
                .build();
    }

}
