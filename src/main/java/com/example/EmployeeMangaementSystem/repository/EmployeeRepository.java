package com.example.EmployeeMangaementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.EmployeeMangaementSystem.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}