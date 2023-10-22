package com.udacity.jdnd.course3.critter.CritterChronologer.service;

import com.udacity.jdnd.course3.critter.CritterChronologer.entities.Schedule;

import java.util.List;

public interface ScheduleService {
    List<Schedule> findAll();

    Schedule createSchedule(Schedule schedule);

    List<Schedule> getAllSchedulesForPet(Long petId);

    List<Schedule> getAllSchedulesForEmployee(Long employeeId);

    List<Schedule> getAllScheduleForCustomer(Long customerId);
}
