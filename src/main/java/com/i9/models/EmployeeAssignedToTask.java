package com.i9.models;

public class EmployeeAssignedToTask {

    private String name;
    private Integer dailyHoursPorcentage;

    public EmployeeAssignedToTask(String name, Integer dailyHoursPorcentage) {
        this.name = name;
        this.dailyHoursPorcentage = dailyHoursPorcentage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDailyHoursPorcentage() {
        return dailyHoursPorcentage;
    }

    public void setDailyHoursPorcentage(Integer dailyHoursPorcentage) {
        this.dailyHoursPorcentage = dailyHoursPorcentage;
    }
}
