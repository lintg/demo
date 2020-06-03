package com.mydubbo.config;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.coba.core.cacheframework.cache.ICacheClient;
import com.coba.core.cacheframework.redis.RedisClientFactory;
import com.coba.core.cacheframework.redis.RedisConfigInfo;
/**
 * 
 * @description：Redis配置
 */
@Configuration
@ConditionalOnProperty(value = "redis.enable", havingValue = "true")
@ConfigurationProperties(prefix = "redis")
public class RedisConfig {
	private static final  Logger log = LoggerFactory.getLogger(RedisConfig.class);
	@Value("${redis.group}")
	private String group;
	@Value("${redis.jedisType}")
	private String jedisType;
	// 密码，为空时，不设置
	@Value("${redis.password:}")
	private String password;

	@Value("${redis.poolConfig.maxTotal}")
	private Integer maxTotal;

	@Value("${redis.poolConfig.maxIdle}")
	private Integer maxIdle;
	@Value("${redis.poolConfig.minIdle}")
	private Integer minIdle;

	@Value("${redis.poolConfig.maxWaitMillis}")
	private Integer maxWaitMillis;

	@Value("${redis.poolConfig.testOnBorrow}")
	private Boolean testOnBorrow;
	@Value("${redis.poolConfig.testOnReturn}")
	private Boolean testOnReturn;

	private List<Map<String, String>> nodeList;
	/**
	 * Sentinel模式填写主节点名
	 */
	private String masterName;
	private Integer dbIndex;
	
	/**
	 * redis客户端工具类
	 * @return redis客户端
	 */
	@Bean("cacheClient")
	public ICacheClient cacheClient() {
		log.info("---jedisPoolConfig maxTotal:{}", maxTotal);
		RedisConfigInfo configInfo = new RedisConfigInfo();
		configInfo.setGroup(group);
		configInfo.setPassword(password);
		configInfo.setDbIndex(dbIndex);
		configInfo.setJedisType(jedisType);
		configInfo.setMasterName(masterName);
		configInfo.setNodeList(nodeList);
		//poolConfig
		configInfo.setMaxTotal(maxTotal);
		configInfo.setMaxIdle(maxIdle);
		configInfo.setMinIdle(minIdle);
		configInfo.setMaxWaitMillis(maxWaitMillis);
		configInfo.setTestOnReturn(testOnReturn);
		configInfo.setTestOnBorrow(testOnBorrow);
		
		return RedisClientFactory.getCacheClient(configInfo);
	}

	public List<Map<String, String>> getNodeList() {
		return nodeList;
	}

	public void setNodeList(List<Map<String, String>> nodeList) {
		this.nodeList = nodeList;
	}


	public Integer getDbIndex() {
		return dbIndex;
	}

	public void setDbIndex(Integer dbIndex) {
		this.dbIndex = dbIndex;
	}

	public String getJedisType() {
		return jedisType;
	}

	public void setJedisType(String jedisType) {
		this.jedisType = jedisType;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getMaxTotal() {
		return maxTotal;
	}

	public void setMaxTotal(Integer maxTotal) {
		this.maxTotal = maxTotal;
	}

	public Integer getMaxIdle() {
		return maxIdle;
	}

	public void setMaxIdle(Integer maxIdle) {
		this.maxIdle = maxIdle;
	}

	public Integer getMinIdle() {
		return minIdle;
	}

	public void setMinIdle(Integer minIdle) {
		this.minIdle = minIdle;
	}

	public Integer getMaxWaitMillis() {
		return maxWaitMillis;
	}

	public void setMaxWaitMillis(Integer maxWaitMillis) {
		this.maxWaitMillis = maxWaitMillis;
	}

	public Boolean getTestOnBorrow() {
		return testOnBorrow;
	}

	public void setTestOnBorrow(Boolean testOnBorrow) {
		this.testOnBorrow = testOnBorrow;
	}

	public Boolean getTestOnReturn() {
		return testOnReturn;
	}

	public void setTestOnReturn(Boolean testOnReturn) {
		this.testOnReturn = testOnReturn;
	}

	public String getMasterName() {
		return masterName;
	}

	public void setMasterName(String masterName) {
		this.masterName = masterName;
	}
	
	
}
