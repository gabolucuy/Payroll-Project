package controller;



import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import payrollcasestudy.boundaries.PayrollDatabase;
import payrollcasestudy.entities.Employee;
import payrollcasestudy.entities.PayCheck;
import payrollcasestudy.transactions.PaydayTransaction;

public class PayDayController {
	//private static Map<Integer, PaydayTransaction> GlobalPaydaysList = new HashMap<Integer, PaydayTransaction>();
	
	public static String pagarATodosLosEmpleados(String year,String month,String day){
		int day1=Integer.parseInt(day); 
		int month1=Integer.parseInt(month); 
		int year1=Integer.parseInt(year); 
		Calendar payDate = new GregorianCalendar(year1, month1, day1);
		PaydayTransaction paydayTransaction = new PaydayTransaction(payDate);
        paydayTransaction.execute();
        
        
		return "Se pago a todos los empleados";
	}
	
	

}
