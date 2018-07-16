package com.i9.models;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Task {
    private int id;
    private String name;
    private int phaseId;
    private int realHours;
    private LocalDate realEndDate;
    private String platform;
    private String functionalityTag;
    private LocalDate initialDate;
    private int hourEstimation;
    private int statusPercent;
    private String statusTag;
    private String responsibleEmployee;

    private List<DailyHours> employeesHoursPerDay;

    //these don't have at BD
    private LocalDate endDatePrediction;

    private static PredictionCalculator predictionCalculator = new PredictionCalculator();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhaseId() {
        return phaseId;
    }

    public void setPhaseId(int phaseId) {
        this.phaseId = phaseId;
    }

    public int getRealHours() {
        return realHours;
    }

    public void setRealHours(int realHours) {
        this.realHours = realHours;
    }

    public LocalDate getRealEndDate() {
        return realEndDate;
    }

    public void setRealEndDate(LocalDate realEndDate) {
        this.realEndDate = realEndDate;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getFunctionalityTag() {
        return functionalityTag;
    }

    public void setFunctionalityTag(String functionalityTag) {
        this.functionalityTag = functionalityTag;
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

    public String getEndDatePrediction() {
        if(endDatePrediction != null)
            return endDatePrediction.format(DateTimeFormatter.ofPattern("yyyy-LL-dd"));

        return null;
    }

    public int getStatusPercent() {
        return statusPercent;
    }

    public void setStatusPercent(int statusPercent) {
        this.statusPercent = statusPercent;
    }

    public String getStatusTag() {
        return statusTag;
    }

    public void setStatusTag(String statusTag) {
        this.statusTag = statusTag;
    }

    public String getResponsibleEmployee() {
        return responsibleEmployee;
    }

    public void setResponsibleEmployee(String responsibleEmployee) {
        this.responsibleEmployee = responsibleEmployee;
    }

    public List<DailyHours> getEmployeesHoursPerDay() {
        return employeesHoursPerDay;
    }

    public void setEmployeesHoursPerDay(List<DailyHours> employeesHoursPerDay) {
        this.employeesHoursPerDay = employeesHoursPerDay;
    }

    public void calculateEstimation() {

        endDatePrediction = predictionCalculator.calculateEndDatePrediction(initialDate, employeesHoursPerDay, hourEstimation);
    }

}
