package com.deependra.pl;


import java.util.Scanner;

import com.deependra.bean.Employee;
import com.deependra.service.EmployeeService;
import com.deependra.service.IEmployeeService;

public class EmployeeMain {

	public static void main(String[] args) {
		IEmployeeService es = new EmployeeService();

		System.out.println("Given Choice:");
		System.out.println("\t1. Add Employee");
		System.out.println("\t2. Find Employee");
		System.out.println("\t3. Get Employee");
		System.out.println("\t4. Update Employee");
		System.out.println("\t5. Remove Employee");
		System.out.println("\t6. Exit");
		System.out.print("\tSelect Choice: ");

		Scanner sc = new Scanner(System.in);

		int choice = sc.nextInt();

		while (true) {
			switch (choice) {
			case 1:
				Employee emp = new Employee();
				System.out.print("Enter Employee Name: ");
				emp.setName((sc.next()));
				System.out.print("Enter Employee email: ");
				emp.setEmail((sc.next()));
				System.out.print("Enter Employee dob: ");
				emp.setDob((sc.next()));

				System.out.println(es.createEmployee(emp));
				break;

			case 2:
				System.out.print("Enter Employee id: ");
				System.out.println(es.findById(sc.next()));
				break;

			case 3:
				es.getAllEmployee().forEach(e -> System.out.println(e));
				break;

			case 4:
				System.out.print("Enter Employee id: ");
				String empId = sc.next();
				System.out.println("Select field for update: ");
				System.out.println("\t1. Employee Name");
				System.out.println("\t2. Employee Email");
				System.out.println("\t3. Employee Dob");

				int idx = sc.nextInt();
				System.out.println("Provide new Data");
				String newData = sc.next();

				System.out.println(es.updateEmployeeName(empId, idx, newData));
				break;

			case 5:
				System.out.print("Enter Employee id: ");
				System.out.println(es.removeEmployee(sc.next()));
				break;

			case 6:
				System.exit(0);
				break;

			}
		}

	}

}
