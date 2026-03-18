package com.deependra.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.deependra.bean.Employee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Persistence;

public class EmployeeDao implements IEmployeeDao{
	
	private static EntityManagerFactory emf;
	private static EntityManager em;
	private static EntityTransaction tx;
	
	static {
			emf = Persistence.createEntityManagerFactory("my-pu");
			em = emf.createEntityManager();
		    tx = em.getTransaction();
		
	}
	@Override
	public String saveEmployee(Employee emp) {
		tx.begin();
		em.persist(emp);
		tx.commit();
		
		return "Employee Added";
	}

	@Override
	public Employee findEmployee(String empid) {
		
		tx.begin();
		Employee e = em.find(Employee.class, empid);
		if(e!=null) {
			return e;
		}
		tx.commit();
		return null;
		
	}

	@Override
	public List<Employee> getAll() {
		
		List<Employee> li = em.createQuery("from Employee").getResultList();
		return li;
	}

	@Override
	public String updateEmployeeName(String empid,int idx,String data) {
		tx.begin();
		Employee e = em.find(Employee.class, empid);
		if(e!=null) {
			if(idx==1) {
				e.setName(data);
			} else if(idx==2) {
				e.setEmail(data);
			} else {
				e.setDob(data);
			}
			tx.commit();
			return "Employee Updated";
		}
		return "Invalid Id";
		
	}

	@Override
	public String deleteEmployee(String empid) {
		tx.begin();
		Employee e = em.find(Employee.class, empid);
		if(e!=null) {
			em.remove(e);
			tx.commit();
			return "Employee Deleted";
		}
		return "Invalid Id";
		
	}

}
