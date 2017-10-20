package cn.mldn.zwb.util;

import java.util.concurrent.TimeUnit;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 要想实现免登录的控制，那么肯定要进行Cookie的处理操作，既然要操作Cookie，建立设置一个单独的程序类
 * @author Maibenben
 *
 */
public class CookieUtil {
	private HttpServletRequest request;
	private HttpServletResponse response;
	public CookieUtil(HttpServletRequest request,HttpServletResponse responose) {
		this.request = request;
		this.response = responose;//----------------------------------------这里为什么会有警告？？？？？？？？？？？？？？
	}
	/**
	 * 向Cookie之中保存有mid的数据信息，但是这个mid在进行保存的时候应该考虑加密处理
	 * @param mid 要保存的用户编号
	 */
	public void saveMid(String mid) {
		Cookie cookie = new Cookie("member",PasswordUtil.encoderString(mid));
		cookie.setPath(this.request.getContentType());//------------------------------默认保存的位置是web根目录
		//转换之后的类型long,而setMaxAge()需要的是int类型----------------------------------这一步很关键
		cookie.setMaxAge((int) TimeUnit.SECONDS.convert(10L, TimeUnit.DAYS));
		this.response.addCookie(cookie);//保存cookie
	}
	/**
	 * 通过Cookie读取保存的用户名
	 * @return 返回保存的用户名，如果没有保存返回null
	 */
	public String loadMid() {
		Cookie cookie[] = this.request.getCookies();
		if(cookie==null) {
			return null;
		}
		for(int x=0;x<cookie.length;x++) {
			if("member".equals(cookie[x].getName())) {
				return PasswordUtil.decoderString(cookie[x].getValue());//取得用户名
			}
		}
		return null;
	}
	/**
	 * 清除session
	 */
	public void cleanMid() {
		Cookie cookie = new Cookie("member","hello");//随便设置一个备用替换
		cookie.setPath(this.request.getContextPath());
		cookie.setMaxAge(0);//-----------------------------关键是这一步
		this.response.addCookie(cookie);
	}

}
