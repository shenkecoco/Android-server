package coco;

import java.sql.*;

public class Sql {
	private String host = "localhost";
	private int port = 3306;//�˿�
	private String dataBaseName = "test";//���ݿ����� 
	private String username = "root";//�û���
	private String password = "090026";//����
	String JDriver="com.mysql.cj.jdbc.Driver";//�������追����Tomcat/libĿ¼��
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
		 //conn= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=utf-8", "root", "123456"); //���ӱ���MYSQL
		//url = String.format("jdbc:mysql://127.0.0.1:3306/test?useSSL=false&serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf-8", host, port, dataBaseName);
		//url = String.format("jdbc:mysql://localhost:3306/test?useSSL=false&serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8","root","090026");
		//conn = DriverManager.getConnection(url);//�����ݿ⽨������
		
		 conn = DriverManager.getConnection(
		          "jdbc:mysql://localhost:3306/test?useSSL=false&serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf-8","root","090026");
		System.out.println("Success connect Mysql server!");    
		
	}catch(Exception e){
		System.out.println("����ʧ�ܣ�����");
		e.printStackTrace();
	}
	}
	public void queryFordlTable() throws SQLException {
		String query = "select * from data where 1 = 1";
		PreparedStatement ps = conn.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		System.out.println("��data");
		ResultSetMetaData rsmd = rs.getMetaData();//ResultSetMetaData��һ���й��������ݿ����Ϣ
		int columns = rsmd.getColumnCount();
		for (int i = 1; i <= columns; i++) {//���ݿ�ı����е������Ǵ�1��ʼ��
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
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return loged;
	}
	public Boolean insertdata(String user, String pass){
		String query = String.format("select * from data where username = '%s'", user);//usernameΪ������Ψһ�Եı�ʶ���ݿ���е����ݣ������ݲ����ظ�
	    PreparedStatement ps;
		try {
			ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery(); 
		    if (!rs.next()) {//��������ڣ���ôֱ�ӽ��в�����У����Ա�data���в�����
		    	query = String.format("insert into data(username, password) values ('%s', '%s')", user, pass);
		    	ps = conn.prepareStatement(query);
		    	ps.execute();
		    	sqlclose();
		    	return true;
		    } 
		    else {
		    	System.out.println("�Ѵ��ڸ�����"); 
		    	sqlclose();
		    	return false;
		    }
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
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