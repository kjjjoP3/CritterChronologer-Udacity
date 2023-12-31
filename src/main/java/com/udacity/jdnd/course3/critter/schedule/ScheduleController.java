package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.entities.Employee;
import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.entities.Schedule;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import com.udacity.jdnd.course3.critter.service.PetService;
import com.udacity.jdnd.course3.critter.service.ScheduleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    PetService petService;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        Schedule schedule = new Schedule();
        schedule.setDate(scheduleDTO.getDate());
        schedule.setActivities(scheduleDTO.getActivities());

        if (scheduleDTO.getEmployeeIds() != null && !scheduleDTO.getEmployeeIds().isEmpty()) {
            List<Employee> employees = employeeService.findAllById(scheduleDTO.getEmployeeIds());
            schedule.setEmployees(employees);
        }

        if (scheduleDTO.getPetIds() != null && !scheduleDTO.getPetIds().isEmpty()) {
            List<Pet> pets = petService.findAllById(scheduleDTO.getPetIds());
            schedule.setPets(pets);
        }

        Schedule createdSchedule = scheduleService.createSchedule(schedule);
        scheduleDTO.setId(createdSchedule.getId());
        return scheduleDTO;
    }


    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        return scheduleService.findAll().stream().map(this::toScheduleDTO).collect(Collectors.toList());
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        if(petId != 0L) {
            return scheduleService.getAllSchedulesForPet(petId).stream().map(this::toScheduleDTO).collect(Collectors.toList());
        }
        return null;
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        Employee employee = employeeService.findById(employeeId);
        if (employee == null) {
            return new ArrayList<>();
        }
        List<Schedule> schedules = scheduleService.getAllSchedulesForEmployee(employeeId);

        List<ScheduleDTO> scheduleDTOs = schedules.stream()
                .map(this::toScheduleDTO)
                .collect(Collectors.toList());

        return scheduleDTOs;
    }



    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        if(customerId == 0L) {
            return null;
        }
        return scheduleService.getAllScheduleForCustomer(customerId)
                .stream().map(this::toScheduleDTO).collect(Collectors.toList());
    }

    private ScheduleDTO toScheduleDTO(Schedule schedule) {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        BeanUtils.copyProperties(schedule, scheduleDTO);
        List<Long> employeeIds = schedule.getEmployees().stream()
                .map(Employee::getId)
                .collect(Collectors.toList());

        List<Long> petIds = schedule.getPets().stream()
                .map(Pet::getId)
                .collect(Collectors.toList());

        scheduleDTO.setEmployeeIds(employeeIds);
        scheduleDTO.setPetIds(petIds);

        return scheduleDTO;
    }
}
