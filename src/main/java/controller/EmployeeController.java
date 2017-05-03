package controller;

import java.util.Set;

import payrollcasestudy.boundaries.PayrollDatabase;
import payrollcasestudy.entities.Employee;
import payrollcasestudy.transactions.Transaction;
import payrollcasestudy.transactions.add.AddCommissionedEmployeeTransaction;
import payrollcasestudy.transactions.add.AddHourlyEmployeeTransaction;
import payrollcasestudy.transactions.add.AddSalariedEmployeeTransaction;

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
	public static String showEmployee(){
		String lista = null;
		 lista = PayrollDatabase.globalPayrollDatabase.showEmployees();
		return lista;
	}

	

}