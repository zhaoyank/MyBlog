/*package com.kaishengit.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Savepoint;

import org.junit.Test;


public class SqlTestCase {

	@Test
	public void viewTest() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql:///db_24","root","123456");
		String sql = "select * from v_author";
		
		PreparedStatement pst = conn.prepareStatement(sql);
		
		ResultSet rs = pst.executeQuery();
		while(rs.next()) {
			System.out.println(rs.getString("name"));
		}
		
		rs.close();
		pst.close();
		conn.close();
	}
	
	@Test
	public void transactionTest() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql:///db_24","root","123456");
		
		conn.setAutoCommit(false);
		
		String sql = "delete from t_stu1 where id = ?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1, 6);
		
		PreparedStatement pst1 = conn.prepareStatement(sql);
		pst1.setInt(1, 7);
		
		pst.executeUpdate();
		
		Savepoint point = conn.setSavepoint();
		
		pst1.executeUpdate();
		
		conn.rollback(point);
		
		conn.commit();
		
		pst.close();
		pst1.close();
		conn.close();
	}
	
	
}
*/