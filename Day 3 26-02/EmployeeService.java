package com.Day3;
import java.util.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.stream.Collectors;

public class EmployeeService {
    public double sumOfSalary(){
        double totalSum=EmployeeRepository.getEmployees().stream().map(e->e.getSalary()).collect(Collectors.summingDouble(e->e));
        return totalSum;
    }
    public Map<String, Long> getCount(){
        Map<String, Long> mp=EmployeeRepository.getEmployees().stream().filter(e->e.getDepartment() != null)
                .map(e->e.getDepartment().getDepartmentName())
                .collect(Collectors.groupingBy(e->e, Collectors.counting()));
        return mp;
    }
    public void mostSenior(){
         EmployeeRepository.getEmployees().stream()
                .min(Comparator.comparing(e->e.getHireDate()))
                 .ifPresent(e->System.out.println("Most Senior Employee ID: "+e.getFirstName()));

    }
    public void printEmployeeServiceDuration(){
        LocalDate today= LocalDate.now();
        EmployeeRepository.getEmployees().stream().map(e->{
           Period duration=Period.between(emp.getHireDate, today);
           int totalMonths=(duration.getYears()*12)+duration.getMonths();
           int days=duration.getDays();
            System.out.println(emp.getFirstName() + " " + emp.getLastName() + " - Duration: " + totalMonths + " months " + days + " days");
        });
    }
    public List<Employee> getEmployeeWithoutDepartment(){
        return EmployeeRepository.getEmployees().stream()
                .filter(e->e.getDepartment()==null)
                .collect(Collectors.toList());
    }
    public List<Department> getDepartmentWithoutEmployees(){
        Set<Integer> activeDeptIds= EmployeeRepository.getEmployees().stream()
                .filter(e->e.getDepartment()!=null)
                .map(e->e.getDepartment().getDepartmentId())
                .collect(Collectors.toSet());
        return EmployeeRepository.getEmployees().stream()
                .filter(dept->!activeDeptIds.contains(dept.getDepartmentId()))
                .collect(Collectors.toList());
    }
    public List<String> getDepartmentsWithHighestEmployeeCount(){
        Map<String, Long> deptCounts=getCount();
        long maxCount=deptCounts.values().stream().max((a,b)->a.compareTo(b)).orElse(0L);
        return deptCount.entrySet()
                .stream()
                .filter(e->e.getValue()==maxCount)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }


}
