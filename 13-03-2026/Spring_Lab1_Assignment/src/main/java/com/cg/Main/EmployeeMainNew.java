package com.cg.Main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cg.bean.Employee;

public class EmployeeMainNew {
	 public static void main(String[] args) {
    	 ApplicationContext ctx=new ClassPathXmlApplicationContext("bean.xml");
    	 Employee emp=ctx.getBean("ee",Employee.class);
    	 emp.getSbuDetails();
    	 ((ClassPathXmlApplicationContext)ctx).close();
     }
}
