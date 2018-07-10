package com.i9.models;

import java.util.Calendar;
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

    private List<EmployeeHoursPerDay> employeesHoursPerDay;

    private List<Task> tasks;

    //these don't have at BD
    private int totalHoursPerDay;
    private int dayEstimation;
    private Date endDatePrediction;

    private static PredictionCalculator predictionCalculator = new PredictionCalculator();

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

    public Date getEndDatePrediction() {
        return endDatePrediction;
    }

    public void setEndDatePrediction(Date endDatePrediction) {
        this.endDatePrediction = endDatePrediction;
    }

    public int getTotalHoursPerDay() {
        return totalHoursPerDay;
    }

    public void setTotalHoursPerDay(int totalHoursPerDay) {
        this.totalHoursPerDay = totalHoursPerDay;
    }

    public List<EmployeeHoursPerDay> getEmployeesHoursPerDay() {
        return employeesHoursPerDay;
    }

    public void setEmployeesHoursPerDay(List<EmployeeHoursPerDay> employeesHoursPerDay) {
        this.employeesHoursPerDay = employeesHoursPerDay;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void calculatePossibleEstimation() {
        calculateTotalHoursPerDay();
        calculateDayEstimation();
        calculateEndDatePrediction();
    }

    private void calculateTotalHoursPerDay() {
        for(EmployeeHoursPerDay employeeHoursPerDay: employeesHoursPerDay) {
            totalHoursPerDay += employeeHoursPerDay.getHoursPerDay();
        }
    }

    private void calculateDayEstimation() {
        dayEstimation = predictionCalculator.calculateDayEstimation(hourEstimation, totalHoursPerDay);
    }

    private void calculateEndDatePrediction() {
        endDatePrediction = predictionCalculator.calculateEndDatePrediction(initialDate, dayEstimation);
    }
}
