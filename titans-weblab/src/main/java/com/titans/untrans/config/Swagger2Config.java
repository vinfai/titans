package com.titans.untrans.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger2 config
 * @author vinfai
 * @since 2017/6/23
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket createRestApi(){
        System.out.println("create rest api.");
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("test-api")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.titans.web.action.api"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("weblab api接口文档")
                .description("API接口文档详细描述")
                .version("1.0")
//                .contact("vinfai")
                .contact(new Contact("vinfai", "", "vinfai85@gmail.com"))
                .termsOfServiceUrl("https://vinfai.github.io")
                .license("apache 2.0")
                .build();
    }

}
