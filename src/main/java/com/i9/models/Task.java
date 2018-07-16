package com.i9.models;

import javax.swing.text.DateFormatter;
import java.text.DateFormat;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
    private int statusPercent;
    private String statusTag;
    private String responsibleEmployee;

    private List<EmployeeHoursPerDay> employeesHoursPerDay;

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

    public String getEndDatePrediction() {
        if(endDatePrediction != null)
            return endDatePrediction.toInstant().atZone(ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern("yyyy-LL-dd"));

        return null;
    }

    public void setEndDatePrediction(Date endDatePrediction) {
        this.endDatePrediction = endDatePrediction;
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

    public List<EmployeeHoursPerDay> getEmployeesHoursPerDay() {
        return employeesHoursPerDay;
    }

    public void setEmployeesHoursPerDay(List<EmployeeHoursPerDay> employeesHoursPerDay) {
        this.employeesHoursPerDay = employeesHoursPerDay;
    }

    public void calculateEstimations() {
        calculateTotalHoursPerDay();
        calculateDayEstimation();
        calculateEndDatePrediction();
    }

    private void calculateTotalHoursPerDay() {
        totalHoursPerDay = predictionCalculator.calculateTotalHoursPerDay(employeesHoursPerDay);
    }


    private void calculateDayEstimation() {
        dayEstimation = predictionCalculator.calculateDayEstimation(hourEstimation, totalHoursPerDay);
    }

    private void calculateEndDatePrediction() {
        endDatePrediction = predictionCalculator.calculateEndDatePrediction(initialDate, dayEstimation);
    }

}
