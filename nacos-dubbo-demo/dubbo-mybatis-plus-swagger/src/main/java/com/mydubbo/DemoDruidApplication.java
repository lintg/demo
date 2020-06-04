package com.mydubbo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;


//http://127.0.0.1:8087/demo-druid/swagger-ui.html
@EnableSwagger2
@SpringBootApplication
public class DemoDruidApplication {

    public static void main(String[] args) {
    	SpringApplication.run(DemoDruidApplication.class, args);
    }

}
