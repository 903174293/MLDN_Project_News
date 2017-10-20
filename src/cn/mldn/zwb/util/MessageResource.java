package cn.mldn.zwb.util;

import java.util.ResourceBundle;
/**
 * 定义一个资源读取的工具类
 * @author mldn
 */
public class MessageResource {
	private ResourceBundle resourceBundle = null ;	// 定义要读取的工具集类
	/**
	 * 定义要读取的资源文件的名称
	 * @param baseName 资源的名称
	 */
	public MessageResource(String baseName) {
		if (baseName.contains(".")) {	// 表示现在已经传递了包名称
			this.resourceBundle = ResourceBundle.getBundle(baseName) ;
		} else {	// 只传递了一个名称，默认名称设置为cn.mldn.resources
			this.resourceBundle = ResourceBundle.getBundle("cn.mldn.zwb.resources." + baseName) ;
		}
	}
	/**
	 * 根据指定的key获得对应的value数据
	 * @param key 要读取的名称
	 * @return 资源的完整信息，如果没有指定的key存在返回null 
	 */
	public String getMessage(String key) {
		try {
			return this.resourceBundle.getString(key) ;
		} catch (Exception e) {
			return null ;
		}
	}
}
