package com.example.EmployeeMangaementSystem.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.EmployeeMangaementSystem.entity.Employee;
import com.example.EmployeeMangaementSystem.repository.EmployeeRepository;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // Get all employees
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // Get employee by ID
    public Employee getEmployeeById(int id) {
        return employeeRepository.findById(id).orElse(null);
    }

    // Add employee
    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    // Update employee
    public Employee updateEmployee(int id, Employee employee) {

        Employee oldEmployee = employeeRepository.findById(id).orElse(null);

        if (oldEmployee != null) {
            oldEmployee.setName(employee.getName());
            oldEmployee.setSalary(employee.getSalary());
            oldEmployee.setDepartment(employee.getDepartment());
            oldEmployee.setDesignation(employee.getDesignation());
            oldEmployee.setHireDate(employee.getHireDate());

            return employeeRepository.save(oldEmployee);
        }

        return null;
    }

    // Delete employee
    public String deleteEmployee(int id) {

        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return "Employee deleted successfully";
        }

        return "Employee not found";
    }
}