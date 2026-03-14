package com.Day3;

public class EmployeeMain {
    public static void main(String[] args) {
        EmployeeService es=new EmployeeService();
        System.out.println("Sum is : "+ es.sumOfSalary());
        System.out.println(es.getCount());
        es.mostSenior();
        es.printEmployeeServiceDuration();
        es.getEmployeeWithoutDepartment().forEach(e-> System.out.println(e));
        es.getDepartmentWithoutEmployees().forEach(e-> System.out.println(e));
        System.out.println("The departments with highest employee count: ");
        es.getDepartmentsWithHighestEmployeeCount().forEach(e-> System.out.println(e));
    }

}
