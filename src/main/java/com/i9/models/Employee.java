package com.i9.models;

import java.util.ArrayList;
import java.util.List;

public class Employee{
    private String name;
    private List<DailyHours> dailyHours;

    public Employee() {
        dailyHours = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DailyHours> getDailyHours() {
        return dailyHours;
    }

    public void setDailyHours(List<DailyHours> dailyHours) {
        this.dailyHours = dailyHours;
    }
}
