package com.i9.models;

import java.util.ArrayList;
import java.util.List;

public class DailyHours {
    private Double dailyWorkload;
    private String day;
    private List<EmployeeAssignedToTask> employeeDailyHours;

    public DailyHours() {
        employeeDailyHours = new ArrayList<>();
    }

    public Double getDailyWorkload() {
        return dailyWorkload;
    }

    public void setDailyWorkload(Double dailyWorkload) {
        this.dailyWorkload = dailyWorkload;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public List<EmployeeAssignedToTask> getEmployeeDailyHours() {
        return employeeDailyHours;
    }

    public void addEmployeeDailyHours(EmployeeAssignedToTask employeeAssignedToTask) {
        this.employeeDailyHours.add(employeeAssignedToTask);
    }
}
