package com.lantone.icss.web.common.listen;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
/**
 * @Description:
 * @author: gaodm
 * @date: 2017/5/26 15:55
 * @version: V1.0
 */
@Configuration
@EnableWebMvc
@EnableSwagger2
@ComponentScan(basePackages ="com.lantone.icss.web")
public class RestApiConfig extends WebMvcConfigurationSupport {


    @Bean
    public Docket createAtRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("at-api")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lantone.icss.web.at"))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public Docket createHisRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("his-api")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lantone.icss.web.his"))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public Docket createKlRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("kl-api")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lantone.icss.web.kl"))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public Docket createReasonRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("reason-api")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lantone.icss.web.reason"))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public Docket createRuleRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("rule-api")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lantone.icss.web.rule"))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public Docket createSysRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("sys-api")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lantone.icss.web.sys"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("朗通ICSS平台RESTful APIs")
                .termsOfServiceUrl("http://www.zjlantone.com/")
                .contact(new Contact("gaodm","http://www.zjlantone.com/", null))
                .version("1.0.0")
                .build();
    }
}
