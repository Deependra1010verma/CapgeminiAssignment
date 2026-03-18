package com.cg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class FirstAppApplication {

	public static void main(String[] args) {
		
		ApplicationContext ctx = SpringApplication.run(FirstAppApplication.class, args);
		
		HelloWorld h = ctx.getBean(HelloWorld.class);
		System.out.println(h.sayHello("Suman"));
	}

}
