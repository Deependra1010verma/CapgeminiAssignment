package com.deependra.service;


import java.util.List;

import com.deependra.bean.Employee;

public interface IEmployeeService {
	public String createEmployee(Employee emp);
	public Employee findById(String eid);
	public List<Employee> getAllEmployee();
	public String updateEmployeeName(String empid,int idx,String data);
	public String removeEmployee(String empid);
}
