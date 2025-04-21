package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.model.Employee;
import com.udacity.jdnd.course3.critter.model.EmployeeSkill;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface EmployeeService {
    Employee save(Employee employee);
    Employee getById(Long employeeId);
    void setAvailability(Set<DayOfWeek> daysAvailable, long employeeId);
    List<Employee> findEmployeeForService(LocalDate localDate, Set<EmployeeSkill> skills);
}
