package com.mydubbo.user.client;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

import com.mydubbo.result.Result;
import com.mydubbo.user.service.IUserService;


/**

 */
@Service
public class UserClient {

    @Reference(loadbalance = "roundrobin", cluster = "failfast", check = false,mock = "com.mydubbo.user.client.UserClientMock")
    private IUserService userService;

    public Result<String> getInfo(String name) {
        return userService.getInfo(name);
    }
}
