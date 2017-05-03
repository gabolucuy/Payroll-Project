package controller;

import payrollcasestudy.entities.Employee;
import payrollcasestudy.transactions.Transaction;
import payrollcasestudy.transactions.add.AddSalariedEmployeeTransaction;

public class EmployeeController {

	public static String registrar_empleado_asalariado(String nombre_empleado,String direccion_empleado,String ci_employee, String amount) {
		int ci = Integer.parseInt(ci_employee);
		double amountt= Double.parseDouble(amount);
		 Transaction addEmployeeTransaction =
	                new AddSalariedEmployeeTransaction(ci, nombre_empleado, direccion_empleado,amountt);
	        addEmployeeTransaction.execute();
	        return "Empleado creado satisfactoriamente!";
		/*return "<html>"
				+"<style>"
				+"input[type=submit]{"
				  +  "width: 100%;"
				   + "background-color: #4CAF50;"
				   + "color: white;"
				   + "padding: 14px 20px;"
				   + "margin: 8px 0;"
				   + "border: none;"
				   + "border-radius: 4px;"
				   + "cursor: pointer;}"
				+"input[type=submit]:hover{"
				   + "background-color: #45a049;}"
				+"div{"
			    +"border-radius: 5px;"
			    +"background-color: #f2f2f2;"
			    +"padding: 20px;"
			    + "  width: 50%;}"
			    +"</style>"
				+ "<body>"
				+ "<div>"
				+ "<form method='get' action='/registrar_Nuevo_Empleado'>" 
				+ "<B>Nombre empleado: </B>"+ nombre_empleado+ "</br></br>"
				+ "<B>Ci empleado: </B>" +ci+ "</br></br>"
				+ "<B>Direccion empleado</B> "+direccion_empleado+" </br></br>"
				+ "<input type='submit' value='Registrar otro empleado'>"
				+"</form>"
				+ "<form action='/ver_lista_empleados'>"
			    + "<input type='submit' value='Ver lista de empleados' />"
			    +"</form>"
				+ "</div>"
				+ "</body>"
				+ "</html>";*/
	}

}
