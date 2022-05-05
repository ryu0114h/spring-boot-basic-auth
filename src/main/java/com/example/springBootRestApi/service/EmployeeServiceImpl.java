package com.example.springBootRestApi.service;

import com.example.springBootRestApi.model.Employee;
import com.example.springBootRestApi.repository.EmployeeRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final PasswordEncoder passwordEncoder;

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(PasswordEncoder passwordEncoder,
                               EmployeeRepository employeeRepository) {
        this.passwordEncoder = passwordEncoder;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployee(Long id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);

        if (optionalEmployee.isEmpty()) {
            throw new RuntimeException("従業員が存在しません。");
        }

        return optionalEmployee.get();
    }

    @Override
    public Employee createEmployee(Employee employee) {
        if (employeeRepository.existsByEmail(employee.getEmail())) {
            throw new RuntimeException("このメールアドレスは既に登録されています");
        }

        Employee newEmployee = new Employee();
        newEmployee.setEmail(employee.getEmail());
        newEmployee.setPassword(passwordEncoder.encode(employee.getPassword()));
        return employeeRepository.save(newEmployee);
    }

    @Override
    public Employee updateEmployee(Long id, Employee requestBody) {
        Employee employee = getEmployee(id);
        employee.setEmail(requestBody.getEmail() == null ? employee.getEmail() : requestBody.getEmail());
        employee.setPassword(requestBody.getPassword() == null ? employee.getPassword() : requestBody.getEmail());
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(Long id) {
        getEmployee(id);
        employeeRepository.deleteById(id);
    }
}
