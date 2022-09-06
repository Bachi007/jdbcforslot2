package com.slot2jdbc;
import java.sql.*;
public class demonjdbc1 {

	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		//loading driver
		Class.forName("com.mysql.cj.jdbc.Driver");
		//namef the database
		String url="jdbc:mysql://localhost:3306/anudip";
		String username="root";
		String password="905906";
		//query
		String query="select * from anudipemp";
		//gettiing Connection
	Connection con=DriverManager.getConnection(url,username,password);
		Statement st=con.createStatement();
		//executing query
		ResultSet rs=st.executeQuery(query);
		while(rs.next())
		{
		String data=rs.getString(1)+" is "+rs.getString(2)+" working with "+rs.getString(3)+" with "+rs.getInt(4);
		System.out.println(data);
		
		}
		con.close();
		}

}
