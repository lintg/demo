package com.mydubbo.user.service;

import com.mydubbo.result.Result;

/** * 用户维护服务 * *@author lintg * */
public interface IUserService {
	/** * 增加 * * @param User * @return * @throws BusinessException */
	public Result<String> getInfo(String name) ;

}