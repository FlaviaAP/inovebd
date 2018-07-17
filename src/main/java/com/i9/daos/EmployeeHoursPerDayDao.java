package com.i9.daos;

import com.i9.models.DailyHours;
import com.i9.models.EmployeeAssignedToTask;

import java.util.List;

public interface EmployeeHoursPerDayDao extends GenericDao<DailyHours> {
    List<DailyHours> getEmployeesHoursPerDayByTask(int taskId);

    List<EmployeeAssignedToTask> getEmployeesAssignedToTask(int taskId);
}
