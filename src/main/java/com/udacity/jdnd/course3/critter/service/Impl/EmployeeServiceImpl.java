package com.udacity.jdnd.course3.critter.service.Impl;

import com.udacity.jdnd.course3.critter.entities.Employee;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee findById(Long id) {
        return employeeRepository.getOne(id);
    }

    public List<Employee> findEmployeesForService(LocalDate localDate, Set<EmployeeSkill> employeeSkills) {
        List<Employee> employees = employeeRepository.getAllByDaysAvailableContains(localDate.getDayOfWeek());
        if(employees != null) {
            employees.removeIf(employee -> !employee.getSkills().containsAll(employeeSkills));
        } else {
            return null;
        }
        return employees;
    }

    public List<Employee> findAllById(List<Long> ids) {
        return employeeRepository.findAllById(ids);
    }
}
