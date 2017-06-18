/**
 * 
 */
package cn.e3mall.common.utils;

import java.util.Random;

/**
 * @author hasee
 *
 */
public class IDUtils {
	public static String getItemName(){
		long timeMillis = System.currentTimeMillis();
		Random random = new Random();
		int end = random.nextInt(999);
		String name=timeMillis+String.format("%03d", end);
		return name;
	}
	public static long getItemId(){
		long timeMillis = System.currentTimeMillis();
		Random random = new Random();
		int end = random.nextInt(99);
		String name=timeMillis+String.format("%02d", end);
		long id=new Long(name);
		return id;
	}
}
