package com.example.demo.service;

import com.example.demo.model.Employee;

public interface EmployeeService {
Employee  createEmployee(Employee employee);
Iterable<Employee> getEmployees();
void updateEmployee(Employee employee);
Employee findById(int theId);

void save(Employee theEmployee);

void deleteById(int theId);}
