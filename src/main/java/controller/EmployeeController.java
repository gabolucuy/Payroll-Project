package controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import Gson.FromGson;
import Gson.ToGson;
import payrollcasestudy.boundaries.MemoryDB;
import payrollcasestudy.boundaries.PayrollDatabase;
import payrollcasestudy.boundaries.Repository;
import payrollcasestudy.entities.Employee;
import payrollcasestudy.entities.PayCheck;
import payrollcasestudy.entities.SalesReceipt;
import payrollcasestudy.entities.TimeCard;
import payrollcasestudy.entities.paymentclassifications.CommissionedPaymentClassification;
import payrollcasestudy.entities.paymentclassifications.HourlyPaymentClassification;
import payrollcasestudy.transactions.Transaction;
import payrollcasestudy.transactions.add.AddCommissionedEmployeeTransaction;
import payrollcasestudy.transactions.add.AddHourlyEmployeeTransaction;
import payrollcasestudy.transactions.add.AddSalariedEmployeeTransaction;
import payrollcasestudy.transactions.add.AddSalesReceiptTransaction;
import payrollcasestudy.transactions.add.AddTimeCardTransaction;

public class EmployeeController {
	private static Repository repository =  new  MemoryDB();
	

	public EmployeeController(Repository repository) {
		this.repository =repository;
	}

	public static String addSalariedEmployee(String nombre_empleado,String direccion_empleado,String ci_employee, String amount) {
		int ci = Integer.parseInt(ci_employee);
		double amountt= Double.parseDouble(amount);
		 Transaction addEmployeeTransaction =
	                new AddSalariedEmployeeTransaction(ci, nombre_empleado, direccion_empleado,amountt);
	        addEmployeeTransaction.execute(repository);
	        return "Empleado a sueldo fijo creado satisfactoriamente!";
	}
	
	public static String addHourlyEmployee(String nombre_empleado,String direccion_empleado,String ci_employee, String amount) {
		int ci = Integer.parseInt(ci_employee);
		double amountt= Double.parseDouble(amount);
		Transaction addEmployeeTransaction =
                new AddHourlyEmployeeTransaction(ci, nombre_empleado,direccion_empleado , amountt);
        addEmployeeTransaction.execute(repository);
	        return "Empleado por hora creado satisfactoriamente!";
		
	}
	public static String addComissionedEmployee(String nombre_empleado,String direccion_empleado,String ci_employee, String amount,String comision){
		int ci = Integer.parseInt(ci_employee);
		double amountt= Double.parseDouble(amount);
		double comisionn= Double.parseDouble(comision);
	    Transaction addEmployeeTransaction =
	            new AddCommissionedEmployeeTransaction(ci, nombre_empleado, direccion_empleado, amountt , comisionn);
	    addEmployeeTransaction.execute(repository);
	    return "Empleado por comision creado satisfactoriamente!";
	}
	public static ArrayList<Employee> getListOfAllEmployees(){
		ArrayList<Employee> listOfEmployees = new ArrayList<>();
		listOfEmployees = repository.getAllEmployees();
		return listOfEmployees;
	}
	
	public static ArrayList<PayCheck> getAllPaychecksOfEmployee(int memberId){
		ArrayList<PayCheck> listOfEmployeePayChecks = new ArrayList<>();
		listOfEmployeePayChecks = repository.getAllPaychecksOfEmployee(memberId);
		return listOfEmployeePayChecks;
	}
	
	public static Employee getEmployee(int i) {
		Employee employee  = repository.getEmployee(i);
		return employee;
	}
	public static PayCheck getPayCheck(int employeeId){
		PayCheck paycheck = PayrollDatabase.globalPayrollDatabase.getPaycheck(employeeId);
		return paycheck;
	}
	
	public static ArrayList<Employee> getListOfAllHourlyEmployees(){
		ArrayList<Employee> listOfEmployees = new ArrayList<>();
		listOfEmployees = repository.getAllHourlyEmployees();
		return listOfEmployees;
	}
	
	public static ArrayList<Employee> getListOfAllCommissionedEmployees(){
		ArrayList<Employee> listOfEmployees = new ArrayList<>();
		listOfEmployees = repository.getAllCommissionedEmployees();
		return listOfEmployees;
	}
	
	public static String addHoursToEmployee(String employeeId,String hours,String year,String month,String day){
		double hours1 = Double.parseDouble(hours);
		int day1=Integer.parseInt(day); 
		int month1=Integer.parseInt(month)-1; 
		int year1=Integer.parseInt(year); 
		int id = Integer.parseInt(employeeId);
		Employee employee  = repository.getEmployee(id);
		employee.setHoursOfWork(hours1);
		Calendar payDate = new GregorianCalendar(year1, month1, day1);
		Transaction addTimeCard = new AddTimeCardTransaction(payDate, hours1, id);
         addTimeCard.execute(repository);
		
		return "Horas agregadas Satisfactoriamente";
	}
	
	
	public static String addSalesToEmployee(String employeeId,String sale,String year,String month,String day){
		double sale1 = Double.parseDouble(sale);
		int day1=Integer.parseInt(day); 
		int month1=Integer.parseInt(month)-1; 
		int year1=Integer.parseInt(year); 
		int id = Integer.parseInt(employeeId);
		
		Calendar payDate = new GregorianCalendar(year1, month1, day1);
		Transaction addSalesReceipt = new AddSalesReceiptTransaction(payDate, sale1, id);
        addSalesReceipt.execute(repository);
			
		
		return "Venta agregada Satisfactoriamente";
	}

	public static ArrayList<TimeCard> getTimeCards(int i) {
		HourlyPaymentClassification classification = (HourlyPaymentClassification) repository.getEmployee(i).getPaymentClassification();
		ArrayList<TimeCard> timecards = classification.getTimeCards();	
		return timecards;
	}
	
	public static ArrayList<SalesReceipt> getReceipts(int i) {
		CommissionedPaymentClassification classification = (CommissionedPaymentClassification) repository.getEmployee(i).getPaymentClassification();
		ArrayList<SalesReceipt> receipts = classification.getReceipts();	
		return receipts;
	}
	public static String getAllPaychecksAsJson(){
		ArrayList<PayCheck> listOfEmployeePayChecks = new ArrayList<>();
		ToGson tojson= new ToGson();
		listOfEmployeePayChecks = repository.getAllPaychecks();
		return tojson.ToGson(listOfEmployeePayChecks);	
	}
	
	public static String addTimeCardsToEmployees(String json){
		FromGson fromgson = new FromGson();
		ArrayList<AddTimeCardTransaction> list = fromgson.FromGson(json);
		for(AddTimeCardTransaction addTimeCard : list){
			addTimeCard.execute(repository);
		}		
		return "Horas agregadas a empleados Satisfactoriamente";
	}
}
