package controller;

public class PayrollController {

	public static String ver_lista_empleados() {
		String lista = null;
		lista = EmployeeController.showEmployee();
		return "<html>"
		+"<head>"
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
		+"margin-right: auto;" 
  	  	+ "margin-left: auto;"
	    +"border-radius: 5px;"
	    +"background-color: #f2f2f2;"
	    +"padding: 20px;"
	    + "  width: 50%;}"
	    +"body{"
	  	+"	background-color:#008080}"
	    +"</style>"
	    +"</head>"
		+ "<body>"
		+ "<div>"
		+ lista
		+ "<form action='/registrar_Nuevo_Empleado'>"
	    + "<input type='submit' value='Registrar nuevo empleado' />"
	    +"</form>"
		+ "</div>"
		+ "</body>"
		+ "</html>";
	}
}
