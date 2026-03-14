package com.cg.bean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.cg.bean.*;
public class Employee {
   private int empId;
   private String name;
   @Autowired
   @Qualifier("add1")
   private Address address;
   public int getEmpId() {
	return empId;
   }
   public void setEmpId(int empId) {
	this.empId = empId;
   }
   public String getName() {
	return name;
   }
   public void setName(String name) {
	this.name = name;
   }
   public Address getAddress() {
	return address;
   }
   public void setAddress(Address address) {
	this.address = address;
   }
   public void printEmployeeDetails() {
	   System.out.println("empid: "+empId);
	   System.out.println("name: "+name);
	   System.out.println("======Addresss=====");
	   System.out.println("city: "+address.getCity());
	   System.out.println("country: "+address.getCountry());
	   System.out.println("zip: "+address.getZip());
   }
}
