package cn.mldn.zwb.util;

import java.util.Base64;

public class PasswordUtil {
	private static final int REPEAT_COUNT  = 3;//加密测次数
	private static final String SALT = "mldnjava";//盐值的种子数
	private PasswordUtil() {}
	/**
	 * 进行一个加密处理操作
	 */
	public static String encoder(String pwd) {
		byte[] data = SALT.getBytes();
		for(int x=0;x<REPEAT_COUNT;x++) {
			data = Base64.getEncoder().encode(data);
		}
		String saltPwd = "{"+new String(data)+"}"+pwd;//加盐处理
		for(int x=0;x<REPEAT_COUNT;x++) {
			saltPwd = new MD5Code().getMD5ofStr(saltPwd);
		}
		return saltPwd;
	}
	/**
	 * 使用Base64的加密算法进行指定字符串的加密处理
	 * @param str 要加密的字符串
	 * @return 加密后的字符串
	 */
	public static String encoderString(String str) {
		byte data[] = str.getBytes();
		for(int x=0;x<REPEAT_COUNT;x++) {
			data = Base64.getEncoder().encode(data);
		}
		return new String(data);
	}
	/**
	 * 使用Base64的揭密算法进行指定字符串的加密处理
	 * @param str 要加密的字符串
	 * @return 加密后的字符串
	 */
	public static String decoderString(String str) {
		byte[] data = str.getBytes();
		for(int x=0;x<REPEAT_COUNT;x++) {
			data = Base64.getDecoder().decode(data);
		}
		return new String(data);
	}

}
