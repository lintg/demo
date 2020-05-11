package com.mydubbo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动前 确保 naocos 和 sentinel都启动再运行
 * 
 * 启动前在Run configurations  界面Arguments 中的VM 设置
 * -Djava.net.preferIPv4Stack=true -Dcsp.sentinel.api.port=8721 -Dcsp.sentinel.dashboard.server=localhost:8888 -Dproject.name=dubbo-sentinel-demo-comsumer
 * 
 * http://127.0.0.1:8086/user/getInfo?name=xiaozhang
 *
 */
@SpringBootApplication
public class AppConsumer {

    public static void main(String[] args) {
        SpringApplication.run(AppConsumer.class, args);
    }

}