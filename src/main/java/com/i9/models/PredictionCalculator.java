package com.i9.models;

import java.util.Calendar;
import java.util.Date;

public class PredictionCalculator {

    public int calculateDayEstimation(int hourEstimation, int hoursPerDay) {

        if(hourEstimation != 0 && hoursPerDay != 0)
             return (int) Math.ceil( hourEstimation / (double) hoursPerDay);

        return 0;
    }

    public Date calculateEndDatePrediction(Date initialDate, int dayEstimation) {
        Date endDatePrediction = null;
        if(initialDate != null && dayEstimation != 0) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(initialDate);
            calendar.add(Calendar.DATE, + dayEstimation);
            endDatePrediction = calendar.getTime();
        }
        return endDatePrediction;
    }
}
