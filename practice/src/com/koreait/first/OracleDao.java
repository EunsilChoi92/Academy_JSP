package com.koreait.first;

import java.sql.Connection;
import java.sql.DriverManager;

public class OracleDao {
	
//	private static OracleDao dao = null;
//	
//	private OracleDao() {}
//	
//	public static OracleDao getInstance() {
//		if(dao == null) {
//			dao = new OracleDao();
//		}
//		return dao;
//	}
//	
//	public Connection getConn() throws Exception {
//		String url = "";
//		String id = "";
//		String pw = "";
//		
//		Class.forName("Oracle.jdbc.driver.OracleDriver");
//		
//		Connection conn = DriverManager.getConnection(url, id, pw);
//		System.out.println("접속 성공!");
//		
//		return conn;
//	}
//	
//	public void closeConn(Connection conn) {
//		if(conn != null) {
//			try {
//				conn.close();
//			} catch(Exception e) {
//				e.printStackTrace();
//			}
//		}
//	}	
	public Connection getConn() throws Exception {
		String url = "";
		String id = "";
		String pw = "";
		
		Class.forName("Oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection(url, id, pw);
		System.out.println("접속 성공!");
		
		return conn;
	}
}
