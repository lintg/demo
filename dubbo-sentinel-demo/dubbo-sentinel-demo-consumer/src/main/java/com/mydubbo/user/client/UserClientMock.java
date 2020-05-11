package com.mydubbo.user.client;

import com.mydubbo.result.Result;
import com.mydubbo.user.service.IUserService;


/**
 */
public class UserClientMock implements IUserService {

    /**
     * 获取用户信息
     */
    @Override
    public Result<String> getInfo(String name) {
        return new Result<>("被熔断了！");
    }
}
