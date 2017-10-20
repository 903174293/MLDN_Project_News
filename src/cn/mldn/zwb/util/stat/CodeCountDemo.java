package cn.mldn.zwb.util.stat;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CodeCountDemo {
	// 定义一个集合操作，保存代码行的信息统计量，但是不统计空行代码
	private static Map<String, Long> map = new HashMap<String, Long>();
	public static void listCount(File file) throws Exception {	// 进行代码行的统计
		if (file.isDirectory()) {	// 文件是一个目录
			File results [] = file.listFiles() ;
			for (int x = 0; x < results.length; x++) {
				listCount(results[x]) ;
			}
		} else {
			if (file.isFile()) {	// 如果现在是文件则判断后缀类型
				String ext = file.getName().substring(file.getName().lastIndexOf(".") + 1) ;
				switch(ext) {
				case "java" : {
					long count = map.get("java") ;	// 获得已经保存的行数
					map.put("java", count + rowCount(file)) ;
					break ;
				}
//				case "jsp" : {
//					long count = map.get("jsp") ;	// 获得已经保存的行数
//					map.put("jsp", count + rowCount(file)) ;
//					break ;
//				}
				}
			}
		}
	}
	public static long rowCount(File file) throws Exception {
		long count = 0L ;
		Scanner scan = new Scanner(file) ;
		scan.useDelimiter("\n") ;
		while (scan.hasNext()) {
			if (scan.next().trim().length() > 0) {
				count ++ ;
			}
		}
		scan.close(); 
		return count ;
	}
	public static void main(String[] args) throws Exception {
		map.put("java", 0L); // 设置一个统计的初始化内容
		map.put("jsp", 0L); // 设置一个统计的初始化内容
		File file = new File("D:" + File.separator + "eclipse-workspace" + File.separator + "DeptProject");
		listCount(file) ;
		System.out.println("Java代码行数：" + map.get("java"));
		System.out.println("JSP代码行数：" + map.get("jsp"));
	}

}
