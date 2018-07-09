package com.i9.daos;

import com.i9.models.EmployeeHoursPerDay;

import java.util.List;

public interface EmployeeHoursPerDayDao extends GenericDao<EmployeeHoursPerDay> {
    List<EmployeeHoursPerDay> getEmployeesHoursPerDayByPhase(int phaseId);
}
