package com.i9.models;

import java.util.Date;
import java.util.List;

public class Phase {
    private int id;
    private int projectId;
    private int number;
    private String observation;
    private Date initialDate;
    private Date endDate;
    private int hourEstimation;
    private Date dateForecastWithMargin;

    private List<EmployeeHoursPerDay> employeesHoursPerDay;

    //these don't have at BD
    private int hoursPerDay;
    private int dayEstimation;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(Date initialDate) {
        this.initialDate = initialDate;
    }

    public int getHourEstimation() {
        return hourEstimation;
    }

    public void setHourEstimation(int hourEstimation) {
        this.hourEstimation = hourEstimation;
    }

    public int getDayEstimation() {
        return dayEstimation;
    }

    public void setDayEstimation(int dayEstimation) {
        this.dayEstimation = dayEstimation;
    }

    public Date getDateForecastWithMargin() {
        return dateForecastWithMargin;
    }

    public void setDateForecastWithMargin(Date dateForecastWithMargin) {
        this.dateForecastWithMargin = dateForecastWithMargin;
    }

    public int getHoursPerDay() {
        return hoursPerDay;
    }

    public void setHoursPerDay(int hoursPerDay) {
        this.hoursPerDay = hoursPerDay;
    }

    public List<EmployeeHoursPerDay> getEmployeesHoursPerDay() {
        return employeesHoursPerDay;
    }

    public void setEmployeesHoursPerDay(List<EmployeeHoursPerDay> employeesHoursPerDay) {
        this.employeesHoursPerDay = employeesHoursPerDay;
    }
}
