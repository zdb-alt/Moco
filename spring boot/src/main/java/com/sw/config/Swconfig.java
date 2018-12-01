package com.sw.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by XULE on 2018/12/1.
 */
@Configuration
@EnableSwagger2
public class Swconfig {
    @Bean
    public Docket api(){
        return  new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .pathMapping("/")
                .select()
                .paths(PathSelectors.regex("/.*"))
                .build();


    }

    private ApiInfo apiInfo() {
        return  new ApiInfoBuilder().title("我的接口文档")
                .contact(new Contact("dabao","","1024857728"))
                .description("这是我生成的接口文档")
                .version("1.0")
                .build();

    }


}
