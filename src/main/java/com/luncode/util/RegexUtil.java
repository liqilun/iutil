package com.luncode.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;


public class RegexUtil {
	//^[\S\s]{44}
	
	public static boolean regMatch(String src, String reg, boolean ignoreCase) {
		Pattern pattern = null;
		if (ignoreCase) {
			pattern = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
		} else {
			pattern = Pattern.compile(reg);
		}
		Matcher matcher = pattern.matcher(src);
		return matcher.find();
	}
	public static List<String> findByRegex(String src, String reg, boolean unique) {
		List<String> result = new ArrayList<String>();
		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(src);
		while (matcher.find()) {
			String s = matcher.group();
			if (!unique || !result.contains(s)) {
				result.add(s);
			}
		}
		return result;
	}

	public static String findFirstByRegex(String src, String reg) {
		Pattern pattern = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(src);
		if (matcher.find()) {
			return matcher.group();
		}
		return "";
	}

	public static String substitute(String src, String reg, String replacement, boolean ignoreCase) {
		Pattern pattern = null;
		if (ignoreCase) {
			pattern = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
		} else {
			pattern = Pattern.compile(reg);
		}
		Matcher matcher = pattern.matcher(src);
		String result = matcher.replaceAll(replacement);
		return result;
	}
	public static boolean isEmail(String email){
		if(StringUtils.isBlank(email)) return false;
		return regMatch(email, "^[A-Z0-9._-]+@[A-Z0-9_-]+(\\.[A-Z0-9_-]+)*(\\.[A-Z]{2,4})+$", true);
	}
	public static boolean isMobile(String mobile) {
		if(StringUtils.isBlank(mobile)) return false;
		return regMatch(mobile, "^1[34578]{1}\\d{9}$", true);
	}
	public static boolean isIDCard(String number){
		return regMatch(number, "^(\\d{15}|\\d{17}[0-9xX]{1})$", false);
	}
	public static boolean isPhone(String phone){
		if(StringUtils.isBlank(phone)) return false;
		return regMatch(phone, "^[0-9,-]{6,24}$", true);
	}
	public static boolean isPostCode(String postcode) {
		return regMatch(postcode, "^[0-9]{6}$", true);
	}
	/**
	 * 数字、下划线、字母的标识符
	 * @param variable
	 * @param length1
	 * @param length2
	 * @return
	 */
	public static boolean isVariable(String variable, int length1, int length2) {
		if(StringUtils.isBlank(variable)) return false;
		return regMatch(variable, "^\\w{" + length1 + "," + length2 + "}$", true);
	}
	/**
	 * 数字、下划线、字母、中文的标识符
	 * @param variable
	 * @param length1
	 * @param length2
	 * @return
	 */
	public static boolean isCNVariable(String variable, int length1, int length2) {
		if(StringUtils.isBlank(variable)) return false;
		return regMatch(variable, "^[\\w+$\\u4e00-\\u9fa5]{" + length1 + "," + length2 +"}$", true);
	}
	public static boolean isPassword(String password) {
		int len = StringUtils.length(password);
		return StringUtils.isAsciiPrintable(password) && len >=6 && len <=14;
	}
}
