package cn.mldn.zwb.service.abs;

public class AbstractService {

	/**
	 * 
	 * 如果现在设置的字符串不是空则表示需要进行模糊查询处理
	 * @author Maibenben
	 *
	 */
		public boolean isLikeSearch(String str) {
			if(str==null||"".equals(str)) {
				return false;
			}
			return true;
		}
}
