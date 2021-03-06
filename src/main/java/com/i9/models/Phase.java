package com.i9.models;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Phase {
    private int id;
    private int projectId;
    private int number;
    private String observation;
    @DateTimeFormat(pattern = "MM-dd-yyyy")
    private LocalDate initialDate;
    @DateTimeFormat(pattern = "MM-dd-yyyy")
    private LocalDate endDate;
    private int hourEstimation;
    private List<DailyHours> dailyHours;

    private List<Task> tasks;

    private LocalDate endDatePrediction;

    public Phase() {
        tasks = new ArrayList<>();
        dailyHours = new ArrayList<>();
    }

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
        int totalHoursEstimation = 0;
        for (Task task: tasks){
         totalHoursEstimation += task.getHourEstimation();
        }
        return totalHoursEstimation;
    }


    public String getEndDatePrediction() {
        if(endDatePrediction!=null)
            return endDatePrediction.format(DateTimeFormatter.ofPattern("yyyy-LL-dd"));
        return null;
    }

    public LocalDate getLocalDateEndDatePrediction(){
        return endDatePrediction;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void calculateEstimation() {
        endDatePrediction=this.initialDate;
        for (Task task:tasks){
            if(this.initialDate == null){
                break;
            }
            if(task.getLocalDateEndDatePrediction() == null){
                endDatePrediction=null;
                break;
            }
            if(endDatePrediction.isBefore(task.getLocalDateEndDatePrediction()))
                endDatePrediction = task.getLocalDateEndDatePrediction();
        }
    }

}
