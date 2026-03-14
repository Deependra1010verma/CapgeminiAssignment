package com.cg.Main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cg.bean.*;

public class EmployeeMain {
	public static void main(String[] args) {
		ApplicationContext ctx=new ClassPathXmlApplicationContext("bean.xml");
		Employee emp=ctx.getBean("e",Employee.class);
		emp.printEmployeeDetails();
		((ClassPathXmlApplicationContext)ctx).close();
		
		
	}
}
