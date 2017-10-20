package cn.mldn.zwb.util.action.abs;

import java.util.HashMap;
import java.util.Map;

import cn.mldn.zwb.util.action.ActionResourceUtil;
import cn.mldn.zwb.util.web.ModelAndView;

public abstract class AbstractAction {
	/**
	 * 将传递过来的数据进行拆分，本次包含有图片信息
	 * @param ids 包含的数据，格式“ID:图片,”
	 * @return
	 */
	public Map<Long,String> handlePhotoLong(String ids) {
		Map<Long,String> map = new HashMap<Long,String>() ;
		String result [] = ids.split(",") ;
		for (int x = 0 ; x < result.length ; x ++) {
			String temp [] = result[x].split(":") ;
			map.put(Long.parseLong(temp[0]), temp[1]) ;
		}
		return map ;
	}
	/**
	 * 定义一个专门设置提示信息与跳转路径属性的操作方法
	 * @param mav 要进行返回的ModelAndView对象
	 * @param urlKey 跳转路径的key
	 * @param msgKey 提示消息的文字key
	 * 
	 */
	public void setUrlAndMsg(ModelAndView mav, String urlKey, String msgKey,String ... msgParam) {
		mav.addObject("msg", ActionResourceUtil.getMessage(msgKey, msgParam));
		mav.addObject("url", ActionResourceUtil.getPage(urlKey));
	}
}
