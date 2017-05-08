package controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import payrollcasestudy.boundaries.PayrollDatabase;
import payrollcasestudy.entities.Employee;
import payrollcasestudy.entities.PayCheck;
import payrollcasestudy.transactions.PaydayTransaction;
import payrollcasestudy.transactions.Transaction;
import payrollcasestudy.transactions.add.AddCommissionedEmployeeTransaction;
import payrollcasestudy.transactions.add.AddHourlyEmployeeTransaction;
import payrollcasestudy.transactions.add.AddSalariedEmployeeTransaction;
import payrollcasestudy.transactions.add.AddTimeCardTransaction;

public class EmployeeController {

	public static String registrar_empleado_asalariado(String nombre_empleado,String direccion_empleado,String ci_employee, String amount) {
		int ci = Integer.parseInt(ci_employee);
		double amountt= Double.parseDouble(amount);
		 Transaction addEmployeeTransaction =
	                new AddSalariedEmployeeTransaction(ci, nombre_empleado, direccion_empleado,amountt);
	        addEmployeeTransaction.execute();
	        return "Empleado creado satisfactoriamente!";
	}
	
	public static String registrar_empleado_por_horas(String nombre_empleado,String direccion_empleado,String ci_employee, String amount) {
		int ci = Integer.parseInt(ci_employee);
		double amountt= Double.parseDouble(amount);
		Transaction addEmployeeTransaction =
                new AddHourlyEmployeeTransaction(ci, nombre_empleado,direccion_empleado , amountt);
        addEmployeeTransaction.execute();
	        return "Empleado por hora creado satisfactoriamente!";
		
	}
	public static String registrar_empleado_asalariadoComision(String nombre_empleado,String direccion_empleado,String ci_employee, String amount,String comision){
		int ci = Integer.parseInt(ci_employee);
		double amountt= Double.parseDouble(amount);
		double comisionn= Double.parseDouble(comision);
	    Transaction addEmployeeTransaction =
	            new AddCommissionedEmployeeTransaction(ci, nombre_empleado, direccion_empleado, amountt , comisionn);
	    addEmployeeTransaction.execute();
	    return "Empleado por hora creado satisfactoriamente!";
	}
	public static ArrayList<Employee> getListOfAllEmployees(){
		ArrayList<Employee> listOfEmployees = new ArrayList<>();
		listOfEmployees = PayrollDatabase.globalPayrollDatabase.getAllEmployees();
		return listOfEmployees;
	}

	public static Employee getEmployee(int i) {
		Employee employee  = PayrollDatabase.globalPayrollDatabase.getEmployee(i);
		return employee;
	}
	public static PayCheck getPayCheck(int employeeId){
		PayCheck paycheck = PayrollDatabase.globalPayrollDatabase.getPaycheck(employeeId);
		return paycheck;
	}
	
	public static ArrayList<Employee> getListOfAllHourlyEmployees(){
		ArrayList<Employee> listOfEmployees = new ArrayList<>();
		listOfEmployees = PayrollDatabase.globalPayrollDatabase.getAllHourlyEmployees();
		return listOfEmployees;
	}
	public static String addHoursToEmployee(String employeeId,String hours,String year,String month,String day){
		double hours1 = Double.parseDouble(hours);
		int day1=Integer.parseInt(day); 
		int month1=Integer.parseInt(month)-1; 
		int year1=Integer.parseInt(year); 
		int id = Integer.parseInt(employeeId);
		Calendar payDate = new GregorianCalendar(year1, month1, day1);
		Transaction addTimeCard = new AddTimeCardTransaction(payDate, hours1, id);
         addTimeCard.execute();
		return "Horas agregadas Satisfactoriamente";
	}

}
