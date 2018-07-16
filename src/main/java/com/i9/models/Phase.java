package com.i9.models;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Phase {
    private int id;
    private int projectId;
    private int number;
    private String observation;
    private LocalDate initialDate;
    private LocalDate endDate;
    private int hourEstimation;
    private List<DailyHours> dailyHours;

    private List<Task> tasks;

    private LocalDate endDatePrediction;

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

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(LocalDate initialDate) {
        this.initialDate = initialDate;
    }

    public int getHourEstimation() {
        return hourEstimation;
    }

    public void setHourEstimation(int hourEstimation) {
        this.hourEstimation = hourEstimation;
    }

    public List<DailyHours> getDailyHours() {
        return dailyHours;
    }

    public void setDailyHours(List<DailyHours> dailyHours) {
        this.dailyHours = dailyHours;
    }

    public String getEndDatePrediction() {
        return endDatePrediction.format(DateTimeFormatter.ofPattern("yyyy-LL-dd"));
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void calculateEstimation() {
        endDatePrediction = predictionCalculator.calculateEndDatePrediction(initialDate, dailyHours, hourEstimation);
    }

}
