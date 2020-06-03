package com.mydubbo.common.filter;

import org.slf4j.LoggerFactory;

import org.apache.dubbo.common.Constants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.Filter;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Result;
import org.apache.dubbo.rpc.RpcException;
import com.coba.core.monitor.accesslog.service.IAccessLogService;

import ch.qos.logback.classic.Logger;

/**
 * 服务访问日志过滤器,通过logback.xml 中的配置来判断要记录哪些服务的访问信息
 * 
 * 
 * 在resources\META-INF\dubbo 下配置
 * @author
 * 
 */
@Activate(group = Constants.PROVIDER)
public class MeAccessLogFilter implements Filter {
	protected static final Logger logger = (Logger) LoggerFactory.getLogger(MeAccessLogFilter.class);
	private static final String interfaceName = IAccessLogService.class.getName();

	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
		String serviceName = invoker.getInterface().getName();
		// 避免 filter 与 IAccessLogService 出现死循环,尽管 使用了 filter排除,但一旦忘记排除,就会出现死循环
		//interfaceName = com.coba.core.monitor.accesslog.service.IAccessLogService
		if (interfaceName.equals(serviceName)) {
			return invoker.invoke(invocation);
		}
		
		System.out.println("过滤器输出--------------------------serviceName="+serviceName);
		return invoker.invoke(invocation);
		
	}

}
