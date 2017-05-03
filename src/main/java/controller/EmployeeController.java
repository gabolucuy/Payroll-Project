package controller;

import java.util.ArrayList;
import java.util.Set;

import payrollcasestudy.boundaries.PayrollDatabase;
import payrollcasestudy.entities.Employee;
import payrollcasestudy.transactions.Transaction;
import payrollcasestudy.transactions.add.AddCommissionedEmployeeTransaction;
import payrollcasestudy.transactions.add.AddHourlyEmployeeTransaction;
import payrollcasestudy.transactions.add.AddSalariedEmployeeTransaction;
import updatable.EmpleadoView;
import updatable.Updatable;

public class EmployeeController {

	public static String registrar_empleado_asalariado(String nombre_empleado,String direccion_empleado,String ci_employee, String amount) {
		int ci = Integer.parseInt(ci_employee);
		double amountt= Double.parseDouble(amount);
		 Transaction addEmployeeTransaction =
	                new AddSalariedEmployeeTransaction(ci, nombre_empleado, direccion_empleado,amountt);
	        addEmployeeTransaction.execute();
	        return "Empleado: </br> " + nombre_empleado + " </br>creado satisfactoriamente como empleado fijo!";
	}
	
	public static String registrar_empleado_por_horas(String nombre_empleado,String direccion_empleado,String ci_employee, String amount) {
		int ci = Integer.parseInt(ci_employee);
		double amountt= Double.parseDouble(amount);
		Transaction addEmployeeTransaction =
                new AddHourlyEmployeeTransaction(ci, nombre_empleado,direccion_empleado , amountt);
        addEmployeeTransaction.execute();
	        return "Empleado: </br> " + nombre_empleado + " </br>creado satisfactoriamente como empleado por hora!";
		
	}
	public static String registrar_empleado_asalariadoComision(String nombre_empleado,String direccion_empleado,String ci_employee, String amount,String comision){
		int ci = Integer.parseInt(ci_employee);
		double amountt= Double.parseDouble(amount);
		double comisionn= Double.parseDouble(comision);
	    Transaction addEmployeeTransaction =
	            new AddCommissionedEmployeeTransaction(ci, nombre_empleado, direccion_empleado, amountt , comisionn);
	    addEmployeeTransaction.execute();
	    return "Empleado: </br> " + nombre_empleado + " </br>creado satisfactoriamente como empleado fijo con comision!";
	    		
	}

	public static String showEmployees() {
		Updatable updatable = new views.EmpleadoView();
		Set<Integer> employeeIds=PayrollDatabase.globalPayrollDatabase.getAllEmployeeIds();
		ArrayList<Integer> employeeIdLista = new ArrayList<>(employeeIds);
		
		return " ";
	}
	

}
