package com.i9.models;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class Task {
    private int id;
    private String name;
    private int phaseId;
    private int realHours;
    @DateTimeFormat(pattern = "MM-dd-yyyy")
    private LocalDate realEndDate;
    private String platform;
    private String functionalityTag;
    @DateTimeFormat(pattern = "MM-dd-yyyy")
    private LocalDate initialDate;
    private int hourEstimation;
    private int statusPercent;
    private String statusTag;
    private String responsibleEmployee;

    private List<DailyHours> dailyHours;
    private List<EmployeeAssignedToTask> employeesAssignedToTask;
    //these don't have at BD
    private LocalDate endDatePrediction;

    public Task(){

    }
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

    public void setInitialDate(String initialDate) {
        this.initialDate = LocalDate.parse(initialDate,DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public int getHourEstimation() {
        return hourEstimation;
    }

    public void setHourEstimation(int hourEstimation) {
        this.hourEstimation = hourEstimation;
    }

    public String getEndDatePrediction() {
        if(endDatePrediction != null)
            return endDatePrediction.format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));

        return null;
    }

    public LocalDate getLocalDateEndDatePrediction(){
        return endDatePrediction;
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

    public List<DailyHours> getDailyHours() {
        return dailyHours;
    }

    public void setDailyHours(List<DailyHours> dailyHours) {
        this.dailyHours = dailyHours;
    }

    public void calculateEstimation() {

        endDatePrediction = predictionCalculator.calculateEndDatePrediction(initialDate, dailyHours, hourEstimation);
    }

    public List<EmployeeAssignedToTask> getEmployeesAssignedToTask() {
        return employeesAssignedToTask;
    }

    public void setEmployeesAssignedToTask(List<EmployeeAssignedToTask> employeesAssignedToTask) {
        this.employeesAssignedToTask = employeesAssignedToTask.parallelStream().filter(employeeAssignedToTask ->  employeeAssignedToTask.getDailyHoursPercentage() > 0 ).collect(Collectors.toList());
    }
}
