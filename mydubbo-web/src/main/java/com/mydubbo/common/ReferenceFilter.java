package com.mydubbo.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcException;

/**
 * 服务调用过滤器
 * @author 
 */
public class ReferenceFilter implements Filter {
	private Logger logger = LoggerFactory.getLogger(ReferenceFilter.class);

	/**
	 * invoke
	 */
	public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {

		Result result = invoker.invoke(invocation);
		return result;
	}
}
