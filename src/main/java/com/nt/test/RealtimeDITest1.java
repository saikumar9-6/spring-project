//RealtimeDITest1.java
package com.nt.test;

import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.nt.controller.CustomerOperationsController;
import com.nt.model.Customer;

public class RealtimeDITest1 {

	public static void main(String[] args) {
		//read input values from enduser
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter  customer name::");
		String name=sc.next();
		System.out.println("Enter customer  address::");
		String  addrs=sc.next();
		System.out.println("Enter Customer Bill Amount::");
		double billAmt=sc.nextDouble();
		System.out.println("Enter  discount percentage::");
		double discount=sc.nextDouble();
		
		//create Customer class object
		Customer cust=new Customer();
		cust.setCname(name); cust.setCaddrs(addrs);
		cust.setBillAmount(billAmt); cust.setDiscount(discount);
		
		
		//create IOC container
	try(ClassPathXmlApplicationContext ctx=
				new ClassPathXmlApplicationContext("com/nt/cfgs/applicationContext.xml");){
		
		//get Cotroller class object from  the IOC container  (dependency lookup)
		/*CustomerOperationsController controller=
				  ctx.getBean("custController",CustomerOperationsController.class);*/
		
		CustomerOperationsController controller=
				  ctx.getBean("customerOperationsController",CustomerOperationsController.class);
		
		//invoke the b.method
		
			String resultMsg=controller.processCustomer(cust);
			System.out.println(resultMsg);
			
			System.out.println("_____________________________");
			Date  dt1=ctx.getBean("java.util.Date",Date.class);
			System.out.println(dt1);
			Date  dt2=ctx.getBean("java.util.Date#0",Date.class);
			System.out.println(dt2);
			
			Date  dt3=ctx.getBean("java.util.Date#1",Date.class);
			System.out.println(dt3);
			
			
		}//try
		catch(SQLException se) {
			if(se.getErrorCode()==12899)  //SQLError code
			    System.out.println("Problem with colum size");
			else {
				System.out.println("One or another Db problem");
			}
		}
		catch(Exception e) {
			System.out.println("non Db problem");
		}
		
	   

	}//main

}//class
