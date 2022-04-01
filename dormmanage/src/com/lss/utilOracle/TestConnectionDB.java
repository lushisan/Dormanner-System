package com.lss.utilOracle;
import java.sql.Connection;
import java.sql.DriverManager;

//测试Oracle数据库连接项目
public class TestConnectionDB {
	public static void main(String[] args) {
		final String className="oracle.jdbc.OracleDriver";
		final String url="jdbc:oracle:thin:@localhost:1521:orcl";
		final String user="dorm";
		final String password="dorm";
		Connection conn=null;
		try {
			Class.forName(className);
			conn=DriverManager.getConnection(url,user,password);
			System.out.println(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
