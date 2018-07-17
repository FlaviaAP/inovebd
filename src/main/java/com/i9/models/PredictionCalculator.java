package com.i9.models;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class PredictionCalculator {

    public LocalDate calculateEndDatePrediction(LocalDate initialDate, List<DailyHours> dailyHours, int hourEstimation) {
        Double hours = (double) hourEstimation;
        LocalDate currentDate = null;
        if(initialDate != null && !dailyHours.isEmpty()) {
            currentDate = initialDate;
            while (hours > 0) {
                String dayOfWeek =  currentDate.getDayOfWeek().toString();

                List<DailyHours> theDay = dailyHours.parallelStream().filter(day -> (day.getDay().compareToIgnoreCase(dayOfWeek) == 0)).collect(Collectors.toList());
                if(!theDay.isEmpty())
                    hours -= theDay.get(0).getDailyWorkload();
                currentDate = currentDate.plusDays(1);
            }
            currentDate = currentDate.minusDays(1);
        }

        return currentDate;
    }
}
