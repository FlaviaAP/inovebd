package com.i9.models;

public class EmployeeAssignedToTask {

    private String name;
    private Integer dailyHoursPercentage;

    public EmployeeAssignedToTask(String name, Integer dailyHoursPercentage) {
        this.name = name;
        this.dailyHoursPercentage = dailyHoursPercentage;
    }

    public EmployeeAssignedToTask() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDailyHoursPercentage() {
        return dailyHoursPercentage;
    }

    public void setDailyHoursPercentage(Integer dailyHoursPercentage) {
        this.dailyHoursPercentage = dailyHoursPercentage;
    }
}
