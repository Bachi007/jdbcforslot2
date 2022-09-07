package com.slot2jdbc;
import java.util.*;
public class empconsume {
//mainfile for employee management
	public static void main(String[] args) throws Exception {
		
		Scanner bs=new Scanner(System.in);
		empdao dao=new empdao();
		employee e1=new employee();
		
		System.out.println("----Employee Management System----");
		
		System.out.println("Select Operation : \n 1 for add the Employee \n 2 for to print Employee");
		//reading the option for adding or printing employee details
		int op=bs.nextInt();
		switch(op) {
		
		case 1->{
			//reading employee details
			System.out.print("Enter name");
			String ename=bs.next();
			System.out.print("Enter Employee ID");
			int eid=bs.nextInt();
			System.out.print("Enter employee domain");
			String edomain=bs.next();
			System.out.println("Enter employee salary");
			int esal=bs.nextInt();
			//creating employee object with entered data by the user
			e1.eid=eid;
			e1.ename=ename;
			e1.edomain=edomain;
			e1.esalary=esal;
			//getting connection
			dao.connect();
			//adding the employee to table
			int res=dao.addEmployee(e1);
			//if res is 0 that means unable to add employee
			if(res!=0) {
				System.out.println("Employee added to database");
					}
			else {
				System.out.println("something went wrong, please tryagain");
			}
			}
		case 2->{
			//we are printing the employee details byusing given eid by the user
			//reading eid
			System.out.println("Enter employee id :");
			int eid=bs.nextInt();
			//connecting to db
			dao.connect();
			//retriving the employee from db
			employee e2=dao.getEmployee(eid);
			//if eid is 0 that means not found
			if(e2.eid==0) {
				System.out.println("Employee not found");
				
			}
			else {
				System.out.println(e2.eid+" is "+e2.ename+" working in "+e2.edomain+" with"+e2.esalary+" salary");
			}
		}
		}
	//closing scanner
	bs.close();
	
	}

}
