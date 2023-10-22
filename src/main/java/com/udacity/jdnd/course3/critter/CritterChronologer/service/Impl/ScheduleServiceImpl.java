package com.udacity.jdnd.course3.critter.CritterChronologer.service.Impl;

import com.udacity.jdnd.course3.critter.CritterChronologer.entities.Customer;
import com.udacity.jdnd.course3.critter.CritterChronologer.entities.Employee;
import com.udacity.jdnd.course3.critter.CritterChronologer.entities.Pet;
import com.udacity.jdnd.course3.critter.CritterChronologer.entities.Schedule;
import com.udacity.jdnd.course3.critter.CritterChronologer.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.CritterChronologer.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.CritterChronologer.repository.PetRepository;
import com.udacity.jdnd.course3.critter.CritterChronologer.repository.ScheduleRepository;
import com.udacity.jdnd.course3.critter.CritterChronologer.service.ScheduleService;
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
