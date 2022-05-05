package com.example.springBootRestApi.controller;

import com.example.springBootRestApi.model.Employee;
import com.example.springBootRestApi.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    private final UserDetailsService userDetailsService;

    private final AuthenticationManager authenticationManager;

    public EmployeeController(EmployeeService employeeService,
                              UserDetailsService userDetailsService,
                              AuthenticationManager authenticationManager) {
        this.employeeService = employeeService;
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public ResponseEntity<Employee> register(@Validated @RequestBody Employee employee) {
        return new ResponseEntity<Employee>(employeeService.createEmployee(employee), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<HttpStatus> login(@Validated @RequestBody Employee employee) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(employee.getEmail(), employee.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new ResponseEntity<HttpStatus>(HttpStatus.OK);
    }
}
