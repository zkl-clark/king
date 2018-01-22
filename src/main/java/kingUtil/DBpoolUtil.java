package kingUtil;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.google.common.io.Resources;

public class DBpoolUtil {
	
	private final Logger logger = LogManager.getLogger("DbPool");
			
	private int init;  //初始化数量
	
	private int append;  //追加数量
	
	private int max;  //连接池最大数量
	
	private String url;  //数据库ip地址
	
	 private String username;  
	 
	 private String passwd;
	 
	 private boolean activation=false;  //激活状态
	 
	 private BlockingQueue<Connection> freeQueue;
	 
	 private BlockingQueue<Connection> busyQueue;
	 
	 private static DBpoolUtil dbpool;
	 
	 public DBpoolUtil(String name){
		 
	 }
	 
	 private  DBpoolUtil() {
		boolean flag = initParam();
		if (flag) {
			if (init<1||append<1||max<1||init>max) {
				logger.warn("连接池数量不能小于1");
				return;
			}
			activation = initDbPool();
		}else{
			logger.warn("读取配置文件失败");
		}	
	}
	 /**
	  * 获取数据库连接池
	  * @return
	  */
	 public static DBpoolUtil getDBpool(){
		 if (dbpool==null) {
			dbpool=new DBpoolUtil();
		}
		 return dbpool;
	 }
	 /**
	  * 获取一个打开的连接
	  * @return  获取成功返回一个打开的连接，否则返回null
	  */
	 public  Connection getConnection(){
		 if (!activation) {
			return null;
		}
		 if (freeQueue.size()>0) {  //有空闲，直接返回
			Connection conn=freeQueue.poll();
			busyQueue.offer(conn);
			return conn;
		}
		 if(busyQueue.size()==max){  //没有空闲，到达最大值
			 Connection conn=null;
			 try {
				conn = freeQueue.poll(60,TimeUnit.SECONDS);
			} catch (InterruptedException e) {
				logger.error("DbPool.getConnection",e);
			}
			 if(conn!=null){
				 busyQueue.offer(conn);
			 }
			 return conn;
		 }
		try {
			 if (busyQueue.size()+append>max) {
				addConnection(max-busyQueue.size());
			 }else {
					addConnection(append);
			 }
			 Connection coon=freeQueue.poll();
			 busyQueue.offer(coon);
			 return coon;
		} catch (SQLException e) {
			logger.error("DbPoll.getConnection",e);
		
		}
		 return null;
	 }
	 /**
	  * 返回链接给连接池
	  * @param conn
	  */
	public void returnConnection(Connection conn) {
		if (!activation) {
			return;
		}
		busyQueue.remove(conn);
		if ((freeQueue.size()+busyQueue.size()<init&&freeQueue.size()<=1)||freeQueue.size()+busyQueue.size()<max) {
				freeQueue.offer(conn);
		}else {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("DbPool.returnConnection",e);
			}
		}
	}
	 /**
	  * 销毁数据库连接池
	  */
	 private void destroy() {
		activation =false;
		closeConnection(freeQueue);
		closeConnection(busyQueue);
	}
	 /**
	  * 关闭连接
	  * @param queue
	  */
	 private void closeConnection(BlockingQueue<Connection> queue) {
		if (queue.size()>0) {
			while(true){
				Connection conn=queue.poll();
				try {
					conn.close();
				} catch (SQLException e) {
					logger.error("DbPool.closeConnection",e);
				}finally {
					if (conn==null) {
						break;
					}
				}
			}
		}
	}
	/**
	 * 添加新的连接
	 * @param count
	 * @throws SQLException
	 */
	 private void addConnection(int count) throws SQLException{
		 for (int cnt = 0; cnt < count; cnt++) {
			Connection conn=DriverManager.getConnection(url,username,passwd);
			freeQueue.offer(conn);
		}
	 }
	 /**
	  * 读取配置文件
	  * @return 读取成功返回true，否则返回false
	  */
	 private boolean initParam() {
		boolean flag = true;
		SAXReader reader = new SAXReader();
		Document doc=null;
		try {
			doc = reader.read(Resources.getResource("jdbc.xml"));
		} catch (DocumentException e) {
			logger.error("DbPool.initParam",e);
			flag=false;
		}
		if (flag&&doc!=null) {
			Element root=doc.getRootElement();
			url =root.element("url").getText();
			passwd =root.element("passwd").getText();
			username=root.element("username").getText();
			init=Integer.valueOf(root.element("init").getText());
			append = Integer.valueOf(root.element("append").getText());
			max=Integer.valueOf(root.element("max").getText());
		}
		return flag;
	}
	 /**
	  * 打开连接池
	  * @return 打开成功返回true ，否则返回flase
	  */
	 private boolean initDbPool() {
		freeQueue = new ArrayBlockingQueue<Connection>(max);
		busyQueue = new ArrayBlockingQueue<Connection>(max);
		boolean flag = true;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			try {
				addConnection(init);
			} catch (SQLException e) {
				logger.error("DbPool.initDbPool",e);
				flag = false;
			}
		} catch (ClassNotFoundException e) {
		}
		return flag;
	}
	 public boolean isActivation(){
		 return activation;
	 }
}
