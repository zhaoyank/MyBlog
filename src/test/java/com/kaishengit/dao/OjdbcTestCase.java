/*package com.kaishengit.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Test;

public class OjdbcTestCase {

	@Test
	public void OjdbcTest() throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:ORCL", "scott", "123456");
		String sql = "select ename from emp";
		
		PreparedStatement pst = conn.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		
		while(rs.next()) {
			System.out.println(rs.getString(1));
		}

		rs.close();
		pst.close();
		conn.close();
		
	}
	
}
*/