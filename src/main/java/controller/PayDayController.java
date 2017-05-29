package controller;



import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import payrollcasestudy.boundaries.MemoryDB;
import payrollcasestudy.boundaries.PayrollDatabase;
import payrollcasestudy.boundaries.Repository;
import payrollcasestudy.entities.Employee;
import payrollcasestudy.entities.PayCheck;
import payrollcasestudy.transactions.PaydayTransaction;
import payrollcasestudy.transactions.Transaction;
import payrollcasestudy.transactions.add.AddTimeCardTransaction;

public class PayDayController {
	
	private static Repository repository;
	public PayDayController(Repository repository) {
		this.repository = repository;
	}

	public static String payAllEmployees(String year,String month,String day){
		int day1=Integer.parseInt(day); 
		int month1=Integer.parseInt(month)-1; 
		int year1=Integer.parseInt(year); 
		Calendar payDate = new GregorianCalendar(year1, month1, day1);
		//addTimeCardsTransactionsToHourlyEmployees(payDate);
		PaydayTransaction paydayTransaction = new PaydayTransaction(payDate);
        paydayTransaction.execute(repository);
		return "Se pago a todos los empleados";
	}
	
public static void addTimeCardsTransactionsToHourlyEmployees(Calendar payDate, Repository repository){
	ArrayList<Employee> listOfEmployees = new ArrayList<>();
	listOfEmployees = repository.getAllHourlyEmployees();
	for (Employee employee: listOfEmployees){
        Transaction addTimeCard = new AddTimeCardTransaction(payDate, employee.getHoursOfWork(), employee.getEmployeeId());
        addTimeCard.execute(repository);
	}
}
}
