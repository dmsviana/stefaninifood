package com.stefanini.StefaniniFood.config;


import springfox.documentation.service.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.stefanini.StefaniniFood.controllers"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

        private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("StefaniniFood Backend")
                .description("Essa é a documentação da API do StefaniniFood")
                .version("1.0.0")
                .contact(new Contact("Diogo Marcelo", "http://github.com/dmsviana", "dmviana@latam.stefanini.com"))
                .build();
    }




}
