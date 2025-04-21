package com.udacity.jdnd.course3.critter.mapper;

import com.udacity.jdnd.course3.critter.dto.ScheduleDTO;
import com.udacity.jdnd.course3.critter.model.Employee;
import com.udacity.jdnd.course3.critter.model.Pet;
import com.udacity.jdnd.course3.critter.model.Schedule;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import com.udacity.jdnd.course3.critter.service.PetService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ScheduleMapper {
    private final EmployeeService employeeService;
    private final PetService petService;

    public ScheduleMapper(
            EmployeeService employeeService,
            PetService petService
    ) {
        this.employeeService = employeeService;
        this.petService = petService;
    }

    public Schedule convertDTOToSchedule(ScheduleDTO scheduleDTO) {
        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(scheduleDTO, schedule);

        if (scheduleDTO.getEmployeeIds() != null) {
            schedule.setEmployees(
                    scheduleDTO.getEmployeeIds()
                            .stream()
                            .map(employeeService::getById)
                            .filter(Objects::nonNull)
                            .toList()
            );
        }

        if (scheduleDTO.getPetIds() != null) {
            schedule.setPetIds(
                    scheduleDTO.getPetIds().stream()
                            .map(petService::getPetById)
                            .filter(Objects::nonNull)
                            .toList()
            );
        }

        return schedule;
    }

    public ScheduleDTO convertScheduleToDTO(Schedule schedule) {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        BeanUtils.copyProperties(schedule, scheduleDTO);

        if (schedule.getEmployees() != null) {
            scheduleDTO.setEmployeeIds(
                    schedule.getEmployees().stream()
                            .map(Employee::getId)
                            .toList()
            );
        }

        if (schedule.getPets() != null) {
            scheduleDTO.setPetIds(
                    schedule.getPets().stream()
                            .map(Pet::getId)
                            .toList()
            );
        }

        return scheduleDTO;
    }
}

