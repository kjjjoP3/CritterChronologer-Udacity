package com.udacity.jdnd.course3.critter.CritterChronologer.schedule;

import com.udacity.jdnd.course3.critter.CritterChronologer.entities.Schedule;
import com.udacity.jdnd.course3.critter.CritterChronologer.service.EmployeeService;
import com.udacity.jdnd.course3.critter.CritterChronologer.service.PetService;
import com.udacity.jdnd.course3.critter.CritterChronologer.service.ScheduleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        BeanUtils.copyProperties(scheduleDTO, schedule);
        if(scheduleDTO != null) {
            schedule.setPets(petService.findAllById(scheduleDTO.getPetIds()));
            schedule.setEmployees(employeeService.findAllById(scheduleDTO.getEmployeeIds()));
        }
        return toScheduleDTO(scheduleService.createSchedule(schedule));
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
        if(employeeId == 0L) {
            return null;
        }
        return scheduleService.getAllSchedulesForEmployee(employeeId)
                .stream().map(this::toScheduleDTO).collect(Collectors.toList());
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
        schedule.getEmployees().forEach(employee -> {scheduleDTO.getEmployeeIds().add(employee.getId());});
        schedule.getPets().forEach(pet -> {scheduleDTO.getPetIds().add(pet.getId());});
        return scheduleDTO;
    }
}
