package com.cg.Main;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cg.bean.*;
public class Main {
	public static void main(String[] args) {
		ApplicationContext ctx=new ClassPathXmlApplicationContext("bean.xml");
		HelloWorld h1=(HelloWorld)ctx.getBean("hello");
//		HelloWorld h2=(HelloWorld)ctx.getBean("hello");
//		System.out.println(h1==h2);
		System.out.println(h1.sayHello());
		System.out.println("=====Language=====");
		System.out.println(h1.getLanguage());
		((ClassPathXmlApplicationContext)ctx).close();
		
		
	}

}
