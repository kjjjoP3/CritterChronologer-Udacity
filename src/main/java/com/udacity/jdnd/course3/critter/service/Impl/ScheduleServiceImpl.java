package com.udacity.jdnd.course3.critter.service.Impl;

import com.udacity.jdnd.course3.critter.entities.Customer;
import com.udacity.jdnd.course3.critter.entities.Employee;
import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.entities.Schedule;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import com.udacity.jdnd.course3.critter.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    PetRepository petRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public List<Schedule> findAll() {
        return scheduleRepository.findAll();
    }

    @Override
    public Schedule createSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }
    @Override
    public List<Schedule> getAllSchedulesForPet(Long petId) {
        Pet pet = petRepository.getOne(petId);
        if(pet != null) {
            return scheduleRepository.getAllByPetsContains(pet);
        }
        return null;
    }
    @Override
    public List<Schedule> getAllSchedulesForEmployee(Long employeeId) {
        Employee employee = employeeRepository.getOne(employeeId);
        if(employee != null) {
            return scheduleRepository.getAllByEmployeesContains(employee);
        }
        return null;
    }
    @Override
    public List<Schedule> getAllScheduleForCustomer(Long customerId) {
        Customer customer = customerRepository.getOne(customerId);
        if(customer != null) {
            return scheduleRepository.getAllByPetsIn(customer.getPets());
        }
        return null;
    }

}
