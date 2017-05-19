package payrollcasestudy.entities;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class PayCheck {
    private double grossPay;
    private double deductions;
    private double netPay;
    private Calendar payPeriodStart;
    private Calendar payDate;
    private int memberId;
    private Map<String, String> fields = new HashMap<String, String>();

    public PayCheck(Calendar payPeriodStart, Calendar payPeriodEnd) {
        this.payPeriodStart = payPeriodStart;
        this.payDate = payPeriodEnd;
    }
    public void setMemberId(int memberId){
    	this.memberId=memberId;
    }
    public int getMemberId(){
    	return memberId;
    }
    public Calendar getPayPeriodEnd() {
        return this.payDate;
    }

    public double getGrossPay() {
        return grossPay;
    }

    public String getField(String fieldName) {
        return fields.get(fieldName);
    }

    public double getDeductions() {
        return deductions;
    }

    public double getNetPay() {
        return netPay;
    }

    public void setGrossPay(double grossPay) {
        this.grossPay = grossPay;
    }

    public void setDeductions(double deductions) {
        this.deductions = deductions;
    }

    public void setNetPay(double netPay) {
        this.netPay = netPay;
    }

    public void setField(String fieldName, String value) {
        fields.put(fieldName, value);
    }

    public Calendar getPayPeriodStart() {
        return payPeriodStart;
    }
    
    public void setStartDay(Calendar startDate){
    	this.payPeriodStart=startDate;
    }
    
    public String returnPayDate(){   	
    	int dia = payDate.get(Calendar.DAY_OF_MONTH);
    	int mes = payDate.get(Calendar.MONTH) + 1;
        int año = payDate.get(Calendar.YEAR);        
        return String.valueOf(dia)+"/"+String.valueOf(mes)+"/"+String.valueOf(año);
    }
    
}
