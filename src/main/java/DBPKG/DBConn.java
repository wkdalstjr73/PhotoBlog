package DBPKG;

import java.sql.*;

public class DBConn {
	
	public static Connection getConnection() throws Exception {
		Class.forName("oracle.jdbc.OracleDriver");
		Connection con = DriverManager.getConnection
				("jdbc:oracle:thin:@//localhost:1521/xe", "system", "1234");
		return con;
	}
	
	public static void close(Connection con, PreparedStatement pstmt) {
		
		try {
			if(pstmt != null) pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if(con != null) con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void close(Connection con, PreparedStatement pstmt, ResultSet rs) {
		
		try {
			if(pstmt != null) pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if(con != null) con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if(rs != null) rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
