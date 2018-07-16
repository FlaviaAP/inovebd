package com.i9.daos;

import com.i9.models.DailyHours;

import java.util.List;

public interface EmployeeHoursPerDayDao extends GenericDao<DailyHours> {
    List<DailyHours> getEmployeesHoursPerDayByPhase(int phaseId);
    List<DailyHours> getEmployeesHoursPerDayByTask(int taskId);
}
