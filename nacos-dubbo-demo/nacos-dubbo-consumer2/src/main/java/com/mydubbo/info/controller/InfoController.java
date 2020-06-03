package com.mydubbo.info.controller;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mydubbo.info.service.InfoService;

@RestController
public class InfoController {

    //dubbo提供的Reference注解，用于调用远程服务
    @Reference(version = "${service.version}",check = false)
    private InfoService infoService;

    @RequestMapping("/getInfo")
    public String getInfo(){
        return infoService.getInfo();
    }
}
