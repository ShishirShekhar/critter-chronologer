package com.udacity.jdnd.course3.critter.controller;

import com.udacity.jdnd.course3.critter.dto.ScheduleDTO;
import com.udacity.jdnd.course3.critter.mapper.ScheduleMapper;
import com.udacity.jdnd.course3.critter.model.Customer;
import com.udacity.jdnd.course3.critter.model.Employee;
import com.udacity.jdnd.course3.critter.model.Pet;
import com.udacity.jdnd.course3.critter.model.Schedule;
import com.udacity.jdnd.course3.critter.service.CustomerService;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import com.udacity.jdnd.course3.critter.service.PetService;
import com.udacity.jdnd.course3.critter.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    private final ScheduleService scheduleService;
    private final ScheduleMapper scheduleMapper;
    private final CustomerService customerService;
    private final PetService petService;
    private final EmployeeService employeeService;

    @Autowired
    public ScheduleController(
            ScheduleService scheduleService,
            ScheduleMapper scheduleMapper,
            CustomerService customerService,
            PetService petService,
            EmployeeService employeeService
    ) {
        this.scheduleService = scheduleService;
        this.scheduleMapper = scheduleMapper;
        this.customerService = customerService;
        this.petService = petService;
        this.employeeService = employeeService;
    }

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        Schedule schedule = scheduleMapper.convertDTOToSchedule(scheduleDTO);

        return scheduleMapper.convertScheduleToDTO(scheduleService.save(schedule));
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        return scheduleService.getAllSchedule()
                .stream()
                .map(scheduleMapper::convertScheduleToDTO)
                .toList();
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        Pet pet = petService.getPetById(petId);

        return scheduleService.getAllScheduleByPet(pet)
                .stream()
                .map(scheduleMapper::convertScheduleToDTO)
                .toList();
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        Employee employee = employeeService.getById(employeeId);

        return scheduleService.getAllScheduleByEmployee(employee)
                .stream()
                .map(scheduleMapper::convertScheduleToDTO)
                .toList();
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        Customer customer = customerService.getById(customerId);

        return scheduleService.getAllScheduleByCustomer(customer)
                .stream()
                .map(scheduleMapper::convertScheduleToDTO)
                .toList();
    }
}
