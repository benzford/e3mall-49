/**
 * 
 */
package cn.e3mall.service;

/**
 * @author hasee
 *
 */
public interface JedisClient {

	/**
	* <b>Description:</b><br> 
	* @param
	* @return String
	* @param key
	* @return
	* @Note
	* <b>Author:</b> chaonianye
	* <br><b>Date:</b> 2017年6月4日 下午7:15:00
	* <br><b>Version:</b> 1.0
	*/
	String get(String key);

	/**
	* <b>Description:</b><br> 
	* @param
	* @return String
	* @param key
	* @param value
	* @return
	* @Note
	* <b>Author:</b> chaonianye
	* <br><b>Date:</b> 2017年6月4日 下午7:16:11
	* <br><b>Version:</b> 1.0
	*/
	String set(String key, String value);

	/**
	* <b>Description:</b><br> 
	* @param
	* @return long
	* @param key
	* @param seconds
	* @return
	* @Note
	* <b>Author:</b> chaonianye
	* <br><b>Date:</b> 2017年6月4日 下午7:18:01
	* <br><b>Version:</b> 1.0
	*/
	long expire(String key, int seconds);

	/**
	* <b>Description:</b><br> 
	* @param
	* @return boolean
	* @param key
	* @return
	* @Note
	* <b>Author:</b> chaonianye
	* <br><b>Date:</b> 2017年6月4日 下午7:19:33
	* <br><b>Version:</b> 1.0
	*/
	boolean exists(String key);

	/**
	* <b>Description:</b><br> 
	* @param
	* @return long
	* @param key
	* @return
	* @Note
	* <b>Author:</b> chaonianye
	* <br><b>Date:</b> 2017年6月4日 下午7:20:39
	* <br><b>Version:</b> 1.0
	*/
	long ttl(String key);

	/**
	* <b>Description:</b><br> 
	* @param
	* @return long
	* @param key
	* @param field
	* @param value
	* @return
	* @Note
	* <b>Author:</b> chaonianye
	* <br><b>Date:</b> 2017年6月4日 下午7:22:26
	* <br><b>Version:</b> 1.0
	*/
	long hset(String key, String field, String value);

	/**
	* <b>Description:</b><br> 
	* @param
	* @return String
	* @param key
	* @param field
	* @return
	* @Note
	* <b>Author:</b> chaonianye
	* <br><b>Date:</b> 2017年6月4日 下午7:23:19
	* <br><b>Version:</b> 1.0
	*/
	String hget(String key, String field);

	/**
	* <b>Description:</b><br> 
	* @param
	* @return long
	* @param key
	* @return
	* @Note
	* <b>Author:</b> chaonianye
	* <br><b>Date:</b> 2017年6月4日 下午7:26:28
	* <br><b>Version:</b> 1.0
	*/
	long incr(String key);

	/**
	* <b>Description:</b><br> 
	* @param
	* @return long
	* @param key
	* @param fields
	* @return
	* @Note
	* <b>Author:</b> chaonianye
	* <br><b>Date:</b> 2017年6月4日 下午7:27:46
	* <br><b>Version:</b> 1.0
	*/
	long hdel(String key, String... fields);
	
}
