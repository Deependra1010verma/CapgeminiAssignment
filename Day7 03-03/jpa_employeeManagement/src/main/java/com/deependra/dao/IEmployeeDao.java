package com.deependra.dao;



import java.util.List;

import com.deependra.bean.Employee;

public interface IEmployeeDao {
	public String saveEmployee(Employee emp);
	public Employee findEmployee(String empid);
	public List<Employee> getAll();
	public String updateEmployeeName(String empid,int idx,String data);
	public String deleteEmployee(String empid);	
}
