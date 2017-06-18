/**
 * 
 */
package cn.e3mall.common.redis;

import java.util.List;

import org.apache.http.impl.cookie.PublicSuffixListParser;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author hasee
 *
 */
public class JedisClientPool implements JedisClient {
	private JedisPool jedisPool;
	
	public JedisPool getJedisPool() {
		return jedisPool;
	}

	public void setJedisPool(JedisPool jedisPool) {
		this.jedisPool = jedisPool;
	}

	@Override
	public String get(String key) {
		Jedis jedis = jedisPool.getResource();
		String string = jedis.get(key);
		jedis.close();
		return string;
	}

	@Override
	public String set(String key, String value) {
		Jedis jedis = jedisPool.getResource();
		String result = jedis.set(key, value);
		jedis.close();
		return result;
	}

	@Override
	public long expire(String key, int seconds) {
		Jedis jedis = jedisPool.getResource();
		long time = jedis.expire(key, seconds);
		jedis.close();
		return time;
	}

	@Override
	public boolean exists(String key) {
		Jedis jedis = jedisPool.getResource();
		Boolean flag = jedis.exists(key);
		jedis.close();
		return flag;
	}
	@Override
	public boolean hExists(String key,String field) {
		Jedis jedis = jedisPool.getResource();
		Boolean flag = jedis.hexists(key, field);
		jedis.close();
		return flag;
	}

	@Override
	public long ttl(String key) {
		Jedis jedis = jedisPool.getResource();
		long time = jedis.ttl(key);
		jedis.close();
		return time;
	}

	@Override
	public long hset(String key, String field, String value) {
		Jedis jedis = jedisPool.getResource();
		long result = jedis.hset(key, field, value);
		jedis.close();
		return result;
	}

	@Override
	public String hget(String key, String field) {
		Jedis jedis = jedisPool.getResource();
		String string = jedis.hget(key, field);
		jedis.close();
		return string;
	}

	@Override
	public long incr(String key) {
		Jedis jedis = jedisPool.getResource();
		long result = jedis.incr(key);
		jedis.close();
		return result;
	}

	@Override
	public long hdel(String key, String... fields) {
		Jedis jedis = jedisPool.getResource();
		long result = jedis.hdel(key, fields);
		jedis.close();
		return result;
	}
	@Override
	public List<String> hvals(String key){
		Jedis jedis = jedisPool.getResource();
		List<String> list = jedis.hvals(key);
		return list;
	}
	
}
