//banking dao which is havingall the logic for the controller

package com.slot2jdbc;
import java.sql.*;
public class bankingdao {

	Connection con=null;
	//getting the connection	
	public void connectDB()throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/anudip","root","905906");
	}
	
	//creating account for customer
	public int registerCustomer(customer c1)throws Exception {
		
		//feteching existed customer details
		String query2="select * from banking where cname='"+c1.cname+"'";
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(query2);
		if(rs.next())
		{
			return -1;
		}
		else {
			//updating the new customer details
		String query="insert into banking (cname,cpassword,cphone,caccbal) values(?,?,?,?)";
		
		PreparedStatement pst=con.prepareStatement(query);
		
		pst.setString(1, c1.cname);
		pst.setString(2, c1.cpassword);
		pst.setString(3, c1.cphone);
		pst.setInt(4, c1.caccbal);
		
		int count =pst.executeUpdate();
		return count;
		}
		
	}
	//login into the account
	public int login(String username,String pwd)throws Exception{

		String query2="select * from banking where cname='"+username+"'";
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(query2);
		if(rs.next()) {
			
			String password=rs.getString(3);
			//if password from db equal to entered password then login
			if(password.equals(pwd)) {
				int cid= rs.getInt(1);
				return cid;
			}
			else {
				return 0;
			}
		}
		else {
			return -1;
		}
	}
	
	//withdraw amount from user account
	public  int withdraw(int cid,int amount)throws Exception {
		//getting details by customerid
		String query="select * from banking where cid="+cid;
		
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(query);
		rs.next();
		int accbal=rs.getInt(5);
		//if available balance is greater than required amount then withdraw
		if(accbal>amount) {
			
			accbal-=amount;
			//updating remaining amount
			String quer2="update banking set caccbal="+accbal+" where cid="+cid;
			PreparedStatement pst=con.prepareStatement(quer2);
			pst.executeUpdate();
			return accbal;
		}
		else {
			return -1;
		}
	}
	
	public int deposit(int cid, int amount)throws Exception {
		
		String query="select * from banking where cid="+cid;
		
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(query);
		rs.next();
		int availamount=rs.getInt(5);
		availamount+=amount;
		String query2="update banking set caccbal="+availamount;
		PreparedStatement pst=con.prepareStatement(query2);
		
		pst.executeUpdate();
		
		return availamount;
		
	}
	
	
	public int checkBalance(int cid)throws Exception{
		
		String query="select * from banking where cid="+cid;
		
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(query);
		rs.next();
		int availabaleamount=rs.getInt(5);
		return availabaleamount;
	}	
	
	public int pinChange(int cid,String oldpwd,String newpwd)throws Exception{
		
		String query="select * from banking where cid="+cid;
		
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(query);
		rs.next();
		
		String pwd=rs.getString(3);
		if(pwd.equals(oldpwd)) {
			
			String query2="update banking set cpassword='"+newpwd+"' where cid="+cid;
			
			PreparedStatement pst=con.prepareStatement(query2);
			int res=pst.executeUpdate();
			return res;
		}
		else {
			return -1;
		}
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
