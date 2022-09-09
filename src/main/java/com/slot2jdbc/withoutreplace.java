package com.slot2jdbc;

public class withoutreplace {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str="hello this is java a class";
		int res=str.indexOf("a");
		
		String finalstr= str.substring(0, res)+"the"+str.substring(res+1);
		System.out.println(finalstr);
		
				//hello this is
				//the
				//java class 
		
	}

}
