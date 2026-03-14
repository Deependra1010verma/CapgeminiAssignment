package com.cg.bean;

import java.util.*;

public class SBU {
    private int sbuId;
    private String sbuName;
    private String sbuHead;
    private List<Employee> empList;
	public int getSbuId() {
		return sbuId;
	}
	public void setSbuId(int sbuId) {
		this.sbuId = sbuId;
	}
	public List<Employee> getEmpList() {
		return empList;
	}
	public void setEmpList(List<Employee> empList) {
		this.empList = empList;
	}
	public String getSbuName() {
		return sbuName;
	}
	public void setSbuName(String sbuName) {
		this.sbuName = sbuName;
	}
	public String getSbuHead() {
		return sbuHead;
	}
	public void setSbuHead(String sbuHead) {
		this.sbuHead = sbuHead;
	}
	public void displaySbuDetails() {
        System.out.println("SBU Details");
        System.out.println("SBU Code: " + sbuId);
        System.out.println("SBU Name: " + sbuName);
        System.out.println("SBU Head: " + sbuHead);

        System.out.println("Employees in this BU:");

        for (Employee e : empList) {
            e.displayEmployeeDetails();
            System.out.println("================");
        }
	}
}
