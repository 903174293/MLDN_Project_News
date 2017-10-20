package cn.mldn.zwb.dbc;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 负责数据库连接定义的程序类
 * @author mldn
 * 该类可以负责所有操作线程的数据库连接，利用get()方法可以获得连接对象
 */
public class DatabaseConnection {
	private static final String DBDRIVER = "org.gjt.mm.mysql.Driver" ;
	private static final String DBURL = "jdbc:mysql://localhost:3306/mldn" ;
	private static final String USER = "root" ;
	private static final String PASSWORD = "123456" ;
	private static ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>() ;
	/**
	 * 负责对外部提供获取的数据库连接对象，该对象通过ThreadLocal获取，如果当前线程没有保存的连接对象，则创建新的连接
	 * @return 连接对象
	 */
	public static Connection getConnection() {
		Connection conn = threadLocal.get() ;	// 先判断一下在ThreadLocal里是否有连接对象
		if(conn == null) {	// 第一次使用，没有连接，没有连接应该创建一个连接
			conn = connectionDatabase() ;	// 获取连接对象
			threadLocal.set(conn); 	// 将刚刚创建好的连接对象保存在ThreadLocal之中
		}
		return conn ;	// 返回连接对象
	}
	/**
	 * 进行数据库的关闭处理。
	 */
	public static void close() {
		Connection conn = threadLocal.get() ;
		if (conn != null) {	// 现在有连接对象了
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} 
			threadLocal.remove(); 	// 从当前线程之中删除掉指定连接
		}
	}
	/**
	 * 负责创建一个数据库连接对象
	 * @return 数据库连接的实例化对象
	 */
	private static Connection connectionDatabase() {	// 该方法只能本类调用
		Connection conn = null ;
		try {	// 一旦连接出现了错误，整个程序都无发执行
			Class.forName(DBDRIVER) ;
			conn = DriverManager.getConnection(DBURL, USER, PASSWORD) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn ;	// 获得数据库连接对象
	}
}
