package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;




public class JDBCHelper 
{
	public static void close(ResultSet rs)
	{
		try
		{
				if(rs!=null)
					rs.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	}	
	public static void close(Connection con)
	{
		try
		{
				if(con!=null)
					con.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	}	
	public static void close(Statement stmt)
	{
		try
		{
				if(stmt!=null)
					stmt.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	}
	public static Connection getConnection()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");
			
			//Connection con= DriverManager.getConnection("jdbc:mysql://server12.000webhost.com/a1999142_aadi", "a1999142_aadi", "emp123");
			System.out.println(con);
			//Statement stmt = con.createStatement();
			return con;
			
		}
		catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}



}
