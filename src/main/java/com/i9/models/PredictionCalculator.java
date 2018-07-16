package com.i9.models;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class PredictionCalculator {

//    public int calculateTotalHoursPerDay(List<DailyHours> employeesHoursPerDay) {
//        int totalHoursPerDay = 0;
//        for(DailyHours employeeHoursPerDay: employeesHoursPerDay) {
//            totalHoursPerDay += employeeHoursPerDay.getHoursPerDay();
//        }
//        return totalHoursPerDay;
//    }
//
//    public int calculateDayEstimation(int hourEstimation, int hoursPerDay) {
//
//        if(hourEstimation != 0 && hoursPerDay != 0)
//             return (int) Math.ceil( hourEstimation / (double) hoursPerDay);
//
//        return 0;
//    }

    public Date calculateEndDatePrediction(Date initialDate, List<DailyHours> dailyHours, int hourEstimation) {
        Double hours = (double) hourEstimation;
        LocalDate currentDate = initialDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        if(initialDate != null && !dailyHours.isEmpty()) {
            while (hours > 0) {
                String dayOfWeek =  currentDate.getDayOfWeek().toString();

                List<DailyHours> theDay = dailyHours.parallelStream().filter(day -> (day.getDay().compareToIgnoreCase(dayOfWeek) == 0)).collect(Collectors.toList());
                if(!theDay.isEmpty())
                    hours -= theDay.get(0).getDailyWorkload();
                currentDate.plusDays(1);
            }
        }
        currentDate.minusDays(1);

        return Date.from(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
