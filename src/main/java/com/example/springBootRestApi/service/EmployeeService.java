package com.example.springBootRestApi.service;

import com.example.springBootRestApi.model.Employee;
import java.util.List;

public interface EmployeeService {

    List<Employee> getEmployees();

    Employee getEmployee(Long id);

    Employee createEmployee(Employee employee);

    Employee updateEmployee(Long id, Employee employee);

    void deleteEmployee(Long id);
}
