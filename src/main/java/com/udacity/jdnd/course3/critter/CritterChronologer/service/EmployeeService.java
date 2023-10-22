package com.udacity.jdnd.course3.critter.CritterChronologer.service;

import com.udacity.jdnd.course3.critter.CritterChronologer.entities.Employee;
import com.udacity.jdnd.course3.critter.CritterChronologer.user.EmployeeSkill;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface EmployeeService {
    Employee createEmployee(Employee employee);
    Employee findById(Long id);
    List<Employee> findEmployeesForService(LocalDate localDate, Set<EmployeeSkill> employeeSkills);

    List<Employee> findAllById(List<Long> ids);
}
