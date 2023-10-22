package com.udacity.jdnd.course3.critter.CritterChronologer.service.Impl;

import com.udacity.jdnd.course3.critter.CritterChronologer.entities.Employee;
import com.udacity.jdnd.course3.critter.CritterChronologer.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.CritterChronologer.service.EmployeeService;
import com.udacity.jdnd.course3.critter.CritterChronologer.user.EmployeeSkill;
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


    @Override
    public Employee findById(Long id) {
        return employeeRepository.getOne(id);
    }


    @Override
    public List<Employee> findEmployeesForService(LocalDate localDate, Set<EmployeeSkill> employeeSkills) {
        List<Employee> employees = employeeRepository.getAllByDaysAvailableContains(localDate.getDayOfWeek());
        if(employees != null) {
            employees.removeIf(employee -> !employee.getSkills().containsAll(employeeSkills));
        } else {
            return null;
        }
        return employees;
    }

    @Override
    public List<Employee> findAllById(List<Long> ids) {
        return employeeRepository.findAllById(ids);
    }
}