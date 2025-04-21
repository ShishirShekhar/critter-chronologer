package com.udacity.jdnd.course3.critter.service.impl;

import com.udacity.jdnd.course3.critter.model.Customer;
import com.udacity.jdnd.course3.critter.model.Employee;
import com.udacity.jdnd.course3.critter.model.Pet;
import com.udacity.jdnd.course3.critter.model.Schedule;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import com.udacity.jdnd.course3.critter.service.PetService;
import com.udacity.jdnd.course3.critter.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleServiceImpl(
            ScheduleRepository scheduleRepository
    ) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public Schedule save(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    @Override
    public List<Schedule> getAllSchedule() {
        return scheduleRepository.findAll();
    }

    @Override
    public List<Schedule> getAllScheduleByPet(Pet pet) {
        return scheduleRepository.findAllByPets(pet);
    }

    @Override
    public List<Schedule> getAllScheduleByEmployee(Employee employee) {
        return scheduleRepository.findAllByEmployees(employee);
    }

    @Override
    public List<Schedule> getAllScheduleByCustomer(Customer customer) {
        List<Pet> pets = customer.getPets();

        return pets
                .stream()
                .flatMap(pet -> scheduleRepository.findAllByPets(pet).stream())
                .toList();
    }
}
