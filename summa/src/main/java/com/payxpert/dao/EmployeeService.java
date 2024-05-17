package com.payxpert.dao;

import com.payxpert.entity.Employee;
import java.util.List;

public interface EmployeeService {
    Employee getEmployeeById(int employeeId);
    List<Employee> getAllEmployees();
    void addEmployee(Employee employeeData);
    void updateEmployee(Employee employeeData);
    void removeEmployee(int employeeId);
}
