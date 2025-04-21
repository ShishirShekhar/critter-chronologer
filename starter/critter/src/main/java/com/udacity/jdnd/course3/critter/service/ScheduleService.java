package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.model.Customer;
import com.udacity.jdnd.course3.critter.model.Employee;
import com.udacity.jdnd.course3.critter.model.Pet;
import com.udacity.jdnd.course3.critter.model.Schedule;

import java.util.List;

public interface ScheduleService {
    Schedule save(Schedule schedule);
    List<Schedule> getAllSchedule();
    List<Schedule> getAllScheduleByPet(Pet pet);
    List<Schedule> getAllScheduleByEmployee(Employee employee);
    List<Schedule> getAllScheduleByCustomer(Customer customer);
}
