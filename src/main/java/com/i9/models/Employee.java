package com.i9.models;

import com.mysql.cj.conf.ConnectionUrlParser;

public class Employee{
    private String name;
    private Integer workloadHours;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getWorkloadHours() {
        return workloadHours;
    }

    public void setWorkloadHours(Integer workloadHours) {
        this.workloadHours = workloadHours;
    }
}
