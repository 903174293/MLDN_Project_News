package cn.mldn.zwb.util.test;


public class TestSplit {
	public static void main(String[] args) {
		long allRecorders = 26 ;
		int lineSize = 5 ;
		// (26 + 5 - 1) / 5 = 6
		// (25 + 5 - 1) / 5 = 5
		long allPages = (allRecorders + lineSize - 1) / lineSize ;
		System.out.println(allPages);
	}
}

