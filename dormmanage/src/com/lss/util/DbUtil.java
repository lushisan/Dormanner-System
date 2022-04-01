package com.lss.util;

import java.sql.Connection;
import java.sql.DriverManager;


//mysql数据库连接项目
public class DbUtil {
	public Connection getCon() throws Exception {
		//jdbcName		
		Class.forName("com.mysql.jdbc.Driver");   
		Connection con = DriverManager.getConnection
			("jdbc:mysql://localhost:3306/Luss","root","2548316766");
		return con;
		
	}
	
	public void closeCon(Connection con) throws Exception {
		if(con!=null) {
			con.close();
		}
	}
	
	public static void main(String[] args) {
		DbUtil dbUtil = new DbUtil();
		
		try {
			dbUtil.getCon();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("no");
			e.printStackTrace();
		}
	}
}