/**
 * 
 */
package cn.e3mall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.e3mall.service.JedisClient;
import redis.clients.jedis.JedisCluster;

/**
 * @author hasee
 *
 */
public class JedisClientCluster implements JedisClient{
	@Autowired
	private JedisCluster jedisCluster;

	@Override
	public String get(String key) {
		String string = jedisCluster.get(key);
		return string;
	}

	@Override
	public String set(String key, String value) {
		String result = jedisCluster.set(key, value);
		return result;
	}

	@Override
	public long expire(String key, int seconds) {
		Long result = jedisCluster.expire(key, seconds);
		return result;
	}

	@Override
	public boolean exists(String key) {
		Boolean flag = jedisCluster.exists(key);
		return flag;
	}

	@Override
	public long ttl(String key) {
		Long result = jedisCluster.ttl(key);
		return result;
	}

	@Override
	public long hset(String key, String field, String value) {
		Long result = jedisCluster.hset(key, field, value);
		return result;
	}

	@Override
	public String hget(String key, String field) {
		String string = jedisCluster.hget(key, field);
		return string;
	}

	@Override
	public long incr(String key) {
		Long result = jedisCluster.incr(key);
		return result;
	}

	@Override
	public long hdel(String key, String[] fields) {
		Long result = jedisCluster.hdel(key, fields);
		return result;
	}
}
