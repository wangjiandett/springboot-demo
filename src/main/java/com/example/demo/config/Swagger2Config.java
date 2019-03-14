package com.example.demo.config;

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
 * 官方文档地址：https://swagger.io/docs/specification/describing-parameters/#path-parameters
 *
 *  @ApiImplicitParam(name = "id", value = "用户ID", required = true, paramType = "path", dataType = "Integer")
 *
 *  paramType有如下几种类型
 *  1.body
 *  2.path parameters, such as /users/{id}
 *  3.query parameters, such as /users?role=admin
 *  4.header parameters, such as X-MyHeader: Value
 *  5.cookie parameters, which are passed in the Cookie header, such as Cookie: debug=0; csrftoken=BUSe35dohU3O1MZvDCU
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.demo.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("使用Swagger2构建RESTful APIs")
                .description("关注博主博客：https://www.baidu.com/")
                .termsOfServiceUrl("https://localhost:8080/")
                .contact(new Contact("wangjian","xxx", "wangjiandett@gmail.com"))
                .version("1.0")
                .license("license")
                .build();
    }

}
