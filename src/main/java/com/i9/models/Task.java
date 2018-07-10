package com.i9.models;

import java.util.Calendar;
import java.util.Date;

public class Task {
    private int id;
    private String name;
    private int phaseId;
    private int realHours;
    private Date realEndDate;
    private String platform;
    private String functionalityTag;
    private Date initialDate;
    private int hourEstimation;
    private int status;
    private String responsibleEmployee;


    //these don't have at BD
    private int hoursPerDay;
    private int dayEstimation;
    private Date endDatePrediction;

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

    public Date getRealEndDate() {
        return realEndDate;
    }

    public void setRealEndDate(Date realEndDate) {
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

    public Date getEndDatePrediction() {
        return endDatePrediction;
    }

    public void setEndDatePrediction(Date endDatePrediction) {
        this.endDatePrediction = endDatePrediction;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getResponsibleEmployee() {
        return responsibleEmployee;
    }

    public void setResponsibleEmployee(String responsibleEmployee) {
        this.responsibleEmployee = responsibleEmployee;
    }

    public int getHoursPerDay() {
        return hoursPerDay;
    }

    public void setHoursPerDay(int hoursPerDay) {
        this.hoursPerDay = hoursPerDay;
    }

    public void calculatePossibleEstimation() {
        calculateDayEstimation();
        calculateEndDatePrediction();
    }

    private void calculateDayEstimation() {
        dayEstimation = predictionCalculator.calculateDayEstimation(hourEstimation, hoursPerDay);
    }

    private void calculateEndDatePrediction() {
        endDatePrediction = predictionCalculator.calculateEndDatePrediction(initialDate, dayEstimation);
    }

}
