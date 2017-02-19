package com.luncode.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;

public class EncrypUtil {
	public static final String SIGN = "sign";
	public static final String SIGNMETHOD = "signmethod";

	private static String encryptPassword(byte[] unencodedPassword, String algorithm) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance(algorithm);
		} catch (Exception e) {
			return null;
		}
		md.reset();
		md.update(unencodedPassword);
		byte[] encodedPassword = md.digest();
		StringBuilder buf = new StringBuilder();
		for (int i = 0; i < encodedPassword.length; i++) {
			if ((encodedPassword[i] & 0xff) < 0x10) {
				buf.append("0");
			}
			buf.append(Long.toString(encodedPassword[i] & 0xff, 16));
		}
		return buf.toString();
	}

	private static String encryptPassword(String password, String encoding, String algorithm) {
		try {
			byte[] unencodedPassword = password.getBytes(encoding);
			return encryptPassword(unencodedPassword, algorithm);
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}

	public static String md5(byte[] input) {
		return encryptPassword(input, "md5");
	}

	public static String md5(String text) {
		return md5(text, "utf-8");
	}

	public static String md5(String text, String encoding) {
		return encryptPassword(text, encoding, "md5");
	}

	public static String md5(String text, int length) {
		String result = md5(text);
		if (result.length() > length)
			result = result.substring(0, length);
		return result;
	}

	public static String sha(String text, String encoding) {
		return encryptPassword(text, encoding, "sha");
	}

	/**
	 * 对请求参数集进行MD5签名
	 * 
	 * @param params
	 *            待签名的请求参数集
	 * @param secretCode
	 *            签名密码
	 * @return 返回null 或 32位16进制大写字符串
	 */
	public static String signMD5(Map<String, String> params, String secretCode) {
		if (params == null || params.isEmpty())
			return "";
		if (params instanceof java.util.TreeMap) {
			return signMD5Inner((TreeMap<String, String>) params, secretCode);
		} else {
			TreeMap<String, String> treeMap = new TreeMap<String, String>();
			treeMap.putAll(params);
			return signMD5Inner(treeMap, secretCode);
		}
	}

	/**
	 * 对请求参数集进行MD5签名
	 * 
	 * @param param
	 *            待签名的请求参数集
	 * @param secretCode
	 *            签名密码
	 * @return 返回32位16进制大写字符串
	 */
	private static String signMD5Inner(TreeMap<String, String> param, String secretCode) {
		return DigestUtils.md5Hex(signStr(param, secretCode, false)).toUpperCase();
	}

	/**
	 * 将请求参数按key=value&key=valuesecretCode拼接 <br/>
	 * 排除key为sign和signmethod的key-value
	 * 
	 * @param param
	 *            请求参数
	 * @param secretCode
	 *            签名密码
	 * @return
	 */
	public static String signStr(TreeMap<String, String> param, String secretCode, boolean startAppend) {
		StringBuilder orgin = new StringBuilder();
		String value = "";
		for (String name : param.keySet()) {
			// 参与签名的值不包括参数中的签名值和签名方法
			if (!StringUtils.equalsIgnoreCase(name, SIGN) && !StringUtils.equalsIgnoreCase(name, SIGNMETHOD)) {
				value = param.get(name);
				if (StringUtils.isEmpty(value)) {
					value = "";
				}
				orgin.append(name).append("=").append(value).append("&");
			}
		}
		if (startAppend) {
			return secretCode + StringUtils.substringBeforeLast(orgin.toString(), "&");
		}
		return StringUtils.substringBeforeLast(orgin.toString(), "&") + secretCode;
	}
}
