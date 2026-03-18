package com.deependra.pl;



import com.deependra.bean.Employee;
import com.deependra.service.EmployeeService;
import com.deependra.service.IEmployeeService;

public class EmployeeMain {

	public static void main(String[] args) {
		IEmployeeService es=new EmployeeService();
		String result=es.createEmployee(new Employee("Anant", "an@gmail.com", "1985-07-28"));
		System.out.println(result);

	}

}
