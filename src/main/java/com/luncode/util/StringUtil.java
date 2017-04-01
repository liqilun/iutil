package com.luncode.util;

public class StringUtil {
	/**
	 * 判断字符是否是英文字母.
	 * 
	 * @param c
	 *            字符
	 * @return true/false
	 */
	public static boolean isAlpha(char c) {
		return c >= 'A' && c <= 'Z' || c >= 'a' && c <= 'z';
	}
}
