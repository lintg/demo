package com.mydubbo.user.service;

import java.util.Random;

import org.apache.dubbo.config.annotation.Service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.mydubbo.result.Result;

/** * 用户维护服务 * * @author lintg * */
//@Transactional
//@Component
@Service
public class UserService implements IUserService {
//	@Autowired
//	private UserDao userDao;
//	@Autowired
//	private IErrorLogsService errLogService;
	
	private static Random random = new Random();
	
	@SentinelResource(value = "getInfo")
	@Override
	public Result<String>  getInfo(String name) {
		System.out.println("调用dubbo "+name);
        if (random.nextBoolean()) {
//        	System.out.println("故意抛异常");
//            throw new RuntimeException("故意抛异常");
        }
        return new Result<>("Hello World: -> " + name);
	}

}