package com.deependra.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.deependra.bean.Employee;

public class EmployeeDao implements IEmployeeDao{
	private static Connection con;
	static {
		try {
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","pass1234");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public String saveEmployee(Employee emp) {
		try {
			PreparedStatement ps=con.prepareStatement("insert into employee values(?,?,?,?)");
			ps.setString(1, emp.getEmpid());
			ps.setString(2, emp.getName());
			ps.setString(3, emp.getEmail());
			ps.setString(4, emp.getDob());
			int rows=ps.executeUpdate();
			if(rows>0)
				return "Employee Created";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "Employee Not Created";
	}

	@Override
	public Employee findEmployee(String empid) {
		
		PreparedStatement ps;
		try {
			ps = con.prepareStatement("SELECT* FROM employee WHERE empid =?");
			ps.setString(1, empid);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				Employee emp = new Employee(rs.getString(2),rs.getString(3),rs.getString(4));
				return emp;
			}
			else 
				return null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<Employee> getAll() {
		
		List<Employee> ls = new ArrayList<>();
		PreparedStatement ps;
		try {
			ps = con.prepareStatement("SELECT* FROM employee");
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				Employee emp = new Employee(rs.getString(2),rs.getString(3),rs.getString(4));
				ls.add(emp);
			}
			
			return ls;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public String updateEmployeeName(String empid, String newName) {
		
		PreparedStatement ps;
		try {
			ps = con.prepareStatement("UPDATE emp SET name = ? WHERE empid=?");
			ps.setString(1, newName);
			ps.setString(1, empid);
			int rows=ps.executeUpdate();
			if (rows > 0) {
				return "Customer Updated Database..";
			} else
				return "Customer Not Updated..";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}

	@Override
	public String deleteEmployee(String empid) {

		PreparedStatement ps;
		try {
			ps = con.prepareStatement("Delete From emp Where empid=?");
			ps.setString(1, empid);
			int rows = ps.executeUpdate();
			if (rows > 0) {
				System.out.println("Customer deleted from Database..");
			} else
				System.out.println("Customer Not Deleted..");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return null;
	}

}
