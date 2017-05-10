package payrollcasestudy.entities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeCard {
    private Calendar date;
    private double hours;

    public TimeCard(Calendar date, double hours) {
        this.date = date;
        this.hours = hours;
    }

    public Calendar getDate() {
        return date;
    }

    public double getHours() {
        return hours;
    }
    
    public String dateInFormatString(){
       String newDate;
       int year= date.get(Calendar.YEAR);
       int month=date.get(Calendar.MONTH);
       int day = date.get(Calendar.DATE);
       newDate= Integer.toString(day)+" - " + Integer.toString(month)+" - " + Integer.toString(year);
       
       
        return newDate;
    }
}
