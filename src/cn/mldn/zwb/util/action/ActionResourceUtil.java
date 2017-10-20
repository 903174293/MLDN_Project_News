package cn.mldn.zwb.util.action;

import java.text.MessageFormat;

import cn.mldn.zwb.util.ResourceUtil;

public class ActionResourceUtil {
	private static final ResourceUtil PAGE_RESOURCE = new ResourceUtil("cn.mldn.zwb.resources.page");
	private static final ResourceUtil MESSAGE_RESOURCE = new ResourceUtil("cn.mldn.zwb.resources.message");
	/**
	 * 读取资源提示信息
	 * @param key 要读取的资源key
	 * @param args 占位符的设置内容
	 * @return 返回文字提示信息
	 */
	public static String getMessage(String key, String...args) {
		String val = null;
		try {
			val = MessageFormat.format(MESSAGE_RESOURCE.get(key), args);
		} catch (Exception e) {
		}
		return val;
	}
	/**
	 * 获取页面的对应的资源信息
	 * @param key 要读取的key
	 * @return f安徽资源路径
	 */
	public static String getPage(String key) {
		String val = null;
		try {
			val = PAGE_RESOURCE.get(key);
		} catch (Exception e) {
		}
		return val;
	}
	
}
