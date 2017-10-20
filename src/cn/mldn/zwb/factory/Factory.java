package cn.mldn.zwb.factory;

import cn.mldn.zwb.service.proxy.ServiceProxy;
import cn.mldn.zwb.util.MessageResource;

public class Factory {
	private static final MessageResource DAO_RESOURCES = new MessageResource("dao") ;
	private static final MessageResource SERVICE_RESOURCES = new MessageResource("service") ;
	private Factory() {}
	public static <T> T getInstance(String classKey) {
		String suffix = classKey.substring(classKey.indexOf(".") + 1) ;
		switch(suffix) {
			case "dao": {
				return getIDAOInstance(classKey) ;	// 返回DAO接口对象
			}
			case "service" : {
				return getIServiceInstance(classKey) ;	// 返回Service接口对象
			}
			default : {
				return null ;
			}
		}
	}
	/**
	 * 根据指定的key获取Service接口对象
	 * @param classKey 服务的key名称
	 * @return Service接口对象
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getIServiceInstance(String classKey) {
		try {
			return (T) new ServiceProxy().bind(Class.forName(SERVICE_RESOURCES.getMessage(classKey)).newInstance());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 根据指定的Class的key的名称通过dao.properties读取对应的类型对象
	 * @param classKey DAO的key名称
	 * @return 指定的DAO接口对象
	 */
	@SuppressWarnings("unchecked") 
	public static <T> T getIDAOInstance(String classKey) {
		try {
			return (T) Class.forName(DAO_RESOURCES.getMessage(classKey)).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			return null ; 
		}
	}
}


