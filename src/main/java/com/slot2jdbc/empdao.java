package com.slot2jdbc;
import java.sql.*;
public class empdao {

	Connection con=null;
	public void connect()throws Exception {
		//creating the connection
		Class.forName("com.mysql.cj.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/anudip","root","905906");
		
	}
	//adding the employee
	public int addEmployee(employee e1)throws Exception {
		String query="insert into anudipemp values(?,?,?,?)";
		
		PreparedStatement pst=con.prepareStatement(query);
		pst.setInt(1, e1.eid);
		pst.setString(2, e1.ename);
		pst.setString(3,e1.edomain);
		pst.setInt(4, e1.esalary);
		int count=pst.executeUpdate();
		//returning no of rows affected 
		return count;
		
	}
	
	public String getEmpName(int eid) throws Exception
	{
		String query="select * from anudipemp where eid="+eid;
		
		Statement st=con.createStatement();
		
		ResultSet rs=st.executeQuery(query);
		if(rs.next())
		{
			String empname=rs.getString(2);
			return empname;	
		}
		else {
			return null;
		}
		
		
	}	
	
	
	
	//retriving employee details byusing eid given by the user
	public employee  getEmployee(int eid)throws Exception {
		//fetching employee details
		String query="select * from anudipemp where eid="+eid;
		Statement st=con.createStatement();
		
		ResultSet rs=st.executeQuery(query);
		employee e1=new employee();
		if(rs.next()) {
			//after retriving we need to return to employee to main method so we are creating employee object
			e1.eid=rs.getInt(1);
			e1.ename=rs.getString(2);
			e1.edomain=rs.getString(3);
			e1.esalary=rs.getInt(4);			
			//returing the employee
			return e1;
		}
		else {
			return e1;
		}
		
		}
	
	
	
	
}
