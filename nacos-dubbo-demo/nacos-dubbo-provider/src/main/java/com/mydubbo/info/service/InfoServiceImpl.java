package com.mydubbo.info.service;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

//dubbo提供的Service注解，用于声明对外暴露服务
@Component
@Service(version = "${service.version}")
public class InfoServiceImpl implements InfoService {

    @Override
    public String getInfo() {
    	System.out.println("hello，这里是service-base模块！");
        return "hello，这里是service-base模块！";
    }
}
