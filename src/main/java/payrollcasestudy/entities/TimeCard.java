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
    	int dia = date.get(Calendar.DAY_OF_MONTH);
    	int mes = date.get(Calendar.MONTH) + 1;
        int año = date.get(Calendar.YEAR);        
        return String.valueOf(dia)+"/"+String.valueOf(mes)+"/"+String.valueOf(año);
    }
}
