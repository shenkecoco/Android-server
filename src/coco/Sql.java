package coco;

import java.sql.*;

public class Sql {
	private String host = "localhost";
	private int port = 3306;//端口
	private String dataBaseName = "test";//数据库名称 
	private String username = "root";//用户名
	private String password = "090026";//密码
	String JDriver="com.mysql.cj.jdbc.Driver";//驱动，需拷贝到Tomcat/lib目录下
	private String url = null;
	private PreparedStatement ps = null; 
	String sql="select * from data where 1=1"; 
	private Connection conn;
	String user="";
	String pass="";
	public Sql() {
	try{
		Class.forName(JDriver);
		System.out.println("Success loading Mysql Driver!");
		 //conn= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=utf-8", "root", "123456"); //链接本地MYSQL
		//url = String.format("jdbc:mysql://127.0.0.1:3306/test?useSSL=false&serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf-8", host, port, dataBaseName);
		//url = String.format("jdbc:mysql://localhost:3306/test?useSSL=false&serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8","root","090026");
		//conn = DriverManager.getConnection(url);//与数据库建立连接
		
		 conn = DriverManager.getConnection(
		          "jdbc:mysql://localhost:3306/test?useSSL=false&serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf-8","root","090026");
		System.out.println("Success connect Mysql server!");    
		
	}catch(Exception e){
		System.out.println("连接失败！！！");
		e.printStackTrace();
	}
	}
	public void queryFordlTable() throws SQLException {
		String query = "select * from data where 1 = 1";
		PreparedStatement ps = conn.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		System.out.println("表data");
		ResultSetMetaData rsmd = rs.getMetaData();//ResultSetMetaData是一个有关整个数据库的信息
		int columns = rsmd.getColumnCount();
		for (int i = 1; i <= columns; i++) {//数据库的表中列的索引是从1开始的
			System.out.print(rsmd.getColumnName(i) + "\t");
		} 
		System.out.println();
		while (rs.next()) {
			for (int i = 1; i <= columns; i++) {
				System.out.print(rs.getString(i) + "\t");
			}
			System.out.println(); 
		}
	}
	public int login(String user,String pass){
		String query = String.format("select * from data where username = '%s'", user);
		PreparedStatement ps;
		int loged = 0;
		int m;
		try {
			ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				//System.out.println(rs.getString(1)+rs.getString(2));
				if(rs.getString(1).equals(user)){
					if(rs.getString(2).equals(pass)) {
						loged=1;
					}
					else
						loged=2;
				}
				else
					loged=3;
			}
			sqlclose();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return loged;
	}
	public Boolean insertdata(String user, String pass){
		String query = String.format("select * from data where username = '%s'", user);//username为主键，唯一性的标识数据库表中的数据，器数据不可重复
	    PreparedStatement ps;
		try {
			ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery(); 
		    if (!rs.next()) {//如果不存在，那么直接进行插入就行，（对表data进行操作）
		    	query = String.format("insert into data(username, password) values ('%s', '%s')", user, pass);
		    	ps = conn.prepareStatement(query);
		    	ps.execute();
		    	sqlclose();
		    	return true;
		    } 
		    else {
		    	System.out.println("已存在该数据"); 
		    	sqlclose();
		    	return false;
		    }
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return false;
		}
	    
	}
	public void sqlclose() throws SQLException {
		    if(ps!= null){
			    ps.close();
			    ps = null;
			}
			if(conn != null){
				conn.close();
				conn = null;
			}
	}
}