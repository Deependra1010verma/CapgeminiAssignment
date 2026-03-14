package com.cg.Main;
import com.cg.bean.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {
  public static void main(String[] args) {
	  ApplicationContext ctx=new ClassPathXmlApplicationContext("bean.xml");
	  Librarian l=ctx.getBean("ll",Librarian.class);
	  l.issueBook();
	  ((ClassPathXmlApplicationContext)ctx).close();
  }
}
