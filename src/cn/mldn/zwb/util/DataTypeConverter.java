package cn.mldn.zwb.util;

import java.util.HashSet;
import java.util.Set;

/**
 * 实现一些接收参数的类型转换处理
 * @author Maibenben
 *
 */
public class DataTypeConverter {
	public static Set<Long> converterStringSplit(String str){
		Set<Long> set = new HashSet<Long>();
		String result[] = str.split(",");
		for(int x=0;x<result.length;x++) {
			set.add(Long.parseLong(result[x]));
		}
		return set;
	}

	public static Set<Long> converterStringToLong(String str[]){
		Set<Long> set = new HashSet<Long>();
		for(int x=0;x<str.length;x++) {
			set.add(Long.parseLong(str[x]));
		}
		return set;
	}
}
