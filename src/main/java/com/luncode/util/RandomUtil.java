package com.luncode.util;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang.math.RandomUtils;

public class RandomUtil {
	public static final String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String lower = "abcdefghijklmnopqrstuvwxyz";
	public static final String digital = "0123456789"; // 0,1
	public static String getRandomString(String charArray, int length) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			sb.append(charArray.charAt(RandomUtils.nextInt(charArray.length())));
		}
		return sb.toString();
	}
	public static String getRandomString(int length, boolean includeUpper, boolean includeLower, boolean includeDigital) {
		if (length > 100)
			length = 100;
		String s = "";
		if (includeUpper)
			s += upper;
		if (includeLower)
			s += lower;
		if (includeDigital)
			s += digital;
		return getRandomString(s, length);
	}
	public static String getRandomString(int length) {
		return getRandomString(length, true, true, true);
	}

	public static String getUpperRandomString(int length) {
		return getRandomString(length, true, false, false);
	}

	public static String getLowerRandomString(int length) {
		return getRandomString(length, false, true, false);
	}

	public static String getDigitalRandomString(int length) {
		return getRandomString(length, false, false, true);
	}
	/**
	 * 返回随机整数
	 * <p>封装java.util.Random
	 * @param n 范围值
	 * @return n<=1,返回0;其他返回范围0~(n-1)内的随机整数
	 */
	public static int randomInt(int n){
		if(n <= 1){
			return 0;
		}
		return new Random().nextInt(n);
	}
	
	/**
	 * 返回随机整数
	 * <p>封装org.apache.commons.lang.math.RandomUtils
	 * <p>在万次以上的调用上，时间优于{@link #randomInt(int)}
	 * @param n 范围值
	 * @return n<=1,返回0;其他返回范围0~(n-1)内的随机整数
	 */
	public static int randomIntApache(int n){
		if(n <= 1){
			return 0;
		}
		return org.apache.commons.lang.math.RandomUtils.nextInt(n);
	}

	/**
	 * 随机返回List中的值，采用{@link #randomInt(int)}
	 * @param list
	 * @return 如果list为null或空，则返回null
	 */
	public static <T> T getRandomFromList(List<T> list){
		if(list == null || list.isEmpty()){
			return null;
		}
		return list.get(randomInt(list.size()));
	}
	
	/**
	 * 随机返回List中的值，采用{@link #randomIntApache(int)}
	 * @param <T>
	 * @param list
	 * @return 如果list为null或空，则返回null
	 */
	public static <T> T getRandomFromListApache(List<T> list){
		if(list == null || list.isEmpty()){
			return null;
		}
		return list.get(randomInt(list.size()));
	}
	/**
	 * 生成max以内, count个不重复随机数 eg. getRandomNumber(20, 6) 生成20以内6个不重复随机数
	 */
	public static Set getRandomNumber(int max, int count) {
		Set v = new TreeSet();
		Random r = new Random();
		boolean b = true;
		while (b) {
			v.add(r.nextInt(max));
			if (v.size() == count) {
				b = false;
			}
		}
		return v;
	}
}
