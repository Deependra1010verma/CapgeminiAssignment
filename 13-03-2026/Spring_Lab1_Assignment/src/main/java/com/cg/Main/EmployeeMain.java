package com.cg.Main;
import com.cg.bean.Employee;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class EmployeeMain {
     public static void main(String[] args) {
    	 ApplicationContext ctx=new ClassPathXmlApplicationContext("bean.xml");
    	 Employee emp=ctx.getBean("ee",Employee.class);
    	 emp.displayEmployeeDetails();
    	 ((ClassPathXmlApplicationContext)ctx).close();
     }
}
