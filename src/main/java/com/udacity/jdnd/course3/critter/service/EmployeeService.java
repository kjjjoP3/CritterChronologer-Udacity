package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entities.Employee;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface EmployeeService {

     Employee createEmployee(Employee employee);

     Employee findById(Long id);

     List<Employee> findEmployeesForService(LocalDate localDate, Set<EmployeeSkill> employeeSkills);

     List<Employee> findAllById(List<Long> ids);
}
