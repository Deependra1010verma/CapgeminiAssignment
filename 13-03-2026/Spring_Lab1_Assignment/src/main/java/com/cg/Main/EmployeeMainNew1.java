package com.cg.Main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cg.bean.SBU;

public class EmployeeMainNew1 {
	 public static void main(String[] args) {

	        ApplicationContext context =
	                new ClassPathXmlApplicationContext("bean.xml");

	        SBU sbu = (SBU) context.getBean("sbuPES");

	        sbu.displaySbuDetails();
	    }
}
