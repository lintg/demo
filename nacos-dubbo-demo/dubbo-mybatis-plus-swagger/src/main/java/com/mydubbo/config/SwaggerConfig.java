package com.mydubbo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * 
 * 给bean指定特定的名称，然后给docket配置一个groupName，就可以分组显示
 * @author Administrator
 *
 */
@Configuration
public class SwaggerConfig {
	@Bean(name="restApiTest2")
    public Docket createRestApi2() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.mydubbo.user.controller"))
                .paths(PathSelectors.any())
                .build().groupName("组二");
    }
	
	@Bean(name="restApiTest")
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.mydubbo.myuser.controller"))
                .paths(PathSelectors.any())
                .build().groupName("组一");
    }
	
    //配置在线文档的基本信息
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("API文档")
                .description("API 接口列表")
                .termsOfServiceUrl("https://me.csdn.net/blog/miachen520")
                .version("1.0")
                .build();
    }

}
