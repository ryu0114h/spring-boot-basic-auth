package com.example.springBootRestApi.security;

import com.example.springBootRestApi.model.Employee;
import com.example.springBootRestApi.repository.EmployeeRepository;
import java.util.ArrayList;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    
    private final EmployeeRepository employeeRepository;

    public UserDetailsServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = employeeRepository.findByEmail(username);

        if (employee == null) {
            throw new RuntimeException("従業員が見つかりません。");
        }

        return new org.springframework.security.core.userdetails.User(
                employee.getEmail(), employee.getPassword(), new ArrayList<>()
        );
    }
}
