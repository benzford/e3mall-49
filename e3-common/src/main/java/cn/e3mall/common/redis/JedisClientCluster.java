/**
 * 
 */
package cn.e3mall.common.redis;

import java.util.List;


import redis.clients.jedis.JedisCluster;

/**
 * @author hasee
 *
 */
public class JedisClientCluster implements JedisClient{
	private JedisCluster jedisCluster;

	public JedisCluster getJedisCluster() {
		return jedisCluster;
	}

	public void setJedisCluster(JedisCluster jedisCluster) {
		this.jedisCluster = jedisCluster;
	}

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
		long result = jedisCluster.incr(key);
		return result;
	}

	@Override
	public long hdel(String key, String[] fields) {
		Long result = jedisCluster.hdel(key, fields);
		return result;
	}

	@Override
	public List<String> hvals(String key) {
		List<String> list = jedisCluster.hvals(key);
		return list;
	}

	@Override
	public boolean hExists(String key, String field) {
		Boolean flag = jedisCluster.hexists(key, field);
		return flag;
	}
}
