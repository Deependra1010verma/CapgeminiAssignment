package com.deependra.service;


import java.util.List;
import java.util.Random;

import com.deependra.bean.Employee;
import com.deependra.dao.EmployeeDao;
import com.deependra.dao.IEmployeeDao;

public class EmployeeService implements IEmployeeService {
	IEmployeeDao dao=new EmployeeDao();
	
	@Override
	public String createEmployee(Employee emp) {
		if(emp.getName()==null || emp.getName().length()<4 || emp.getEmail()==null ) {
			return "Invalid Employee Data";
		}
		else {
			Random r=new Random();
			String empid=emp.getName().substring(0,3)+r.nextInt(1000);
			emp.setEmpid(empid);
			return dao.saveEmployee(emp);
		}
	}

	@Override
	public Employee findById(String eid) {
		if(eid.length()<4 ) {
			return null;
		}
		Employee emp = dao.findEmployee(eid);
		
		return emp;
	}

	@Override
	public List<Employee> getAllEmployee() {
		
		return dao.getAll();
	}

	@Override
	public String updateEmployeeName(String eid, String name) {
		if(eid.length()<4  || name.length()<4) {
			return "Invalid Details";
		}
		
		return dao.updateEmployeeName(eid, name);
	}

	@Override
	public String removeEmployee(String empid) {
		if(empid.length()<4) {
			return "Invalid Deatails";
		}
		
		return dao.deleteEmployee(empid);
		
		
	}

}
