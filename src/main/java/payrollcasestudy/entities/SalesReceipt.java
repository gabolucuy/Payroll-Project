package payrollcasestudy.entities;

import java.util.Calendar;

public class SalesReceipt {
    private Calendar date;
    private double amount;

    public SalesReceipt(Calendar date, double amount) {
        this.date = date;
        this.amount = amount;
    }

    public Calendar getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }
    public String dateInFormatString(){
    	int dia = date.get(Calendar.DAY_OF_MONTH);
    	int mes = date.get(Calendar.MONTH) + 1;
        int año = date.get(Calendar.YEAR);        
        return String.valueOf(dia)+"/"+String.valueOf(mes)+"/"+String.valueOf(año);
    }
}
