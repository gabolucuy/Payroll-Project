import static spark.Spark.*;

import payrollcasestudy.boundaries.PayrollDatabase;
import payrollcasestudy.entities.Employee;

public class Main {
	private static PayrollDatabase payrollDatabase = new PayrollDatabase();

	public static void main(String[] args) {
		get("/", (request, response) -> registro_form());
		get("/registrar_Nuevo_Empleado", (request, response) -> registro_form());
		post("/registrar", (request, response) -> registrar_empleado(request.queryParams("nombre_empleado"),request.queryParams("direccion_empleado"),request.queryParams("ci")));
	}

	private static String registrar_empleado(String nombre_empleado,String direccion_empleado,String ci_employee) {
		int ci = Integer.parseInt(ci_employee);
		Employee employee = new Employee(ci,nombre_empleado,direccion_empleado);
		payrollDatabase.addEmployee(ci, employee);
		
		return "<html>"
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
				+ "<B>Nombre empleado: </B> nombre_empleado </br></br>"
				+ "<B>Ci empleado: </B> ci </br></br>"
				+ "<B>Direccion empleado</B> direccion_empleado </br></br>"
				+ "<input type='submit' value='Registrar otro empleado'>"
				+ "</div>"
				+ "</body>"
				+ "</html>";
	}

	private static String registro_form() {
		return "<html>"
				+"<style>"
				+"input[type=number], select {"
				   + "width: 100%;"
				    +"padding: 12px 20px;"
				    +"margin: 8px 0;"
				    +"display: inline-block;"
				    +"border: 1px solid #ccc;"
				    +"border-radius: 4px;"
				    +"box-sizing: border-box;}"
					+"input[type=text], select {"
					   + "width: 100%;"
					    +"padding: 12px 20px;"
					    +"margin: 8px 0;"
					    +"display: inline-block;"
					    +"border: 1px solid #ccc;"
					    +"border-radius: 4px;"
					    +"box-sizing: border-box;}"
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
				+ "<form method='post' action='/registrar'>" 
				+ "<label>Nombre:</label>"
				+ "<input type='text' name='nombre_empleado'>"
				+ "</br>"
				+ "</br>"
				+ "<label>CI:</label>"
				+ "<input type='number' name='ci'>"
				+ "</br>"
				+ "</br>"
				+ "<label>Direccion:</label>"
				+ "<input type='text' name='direccion_saludo'>"
				+ "</br>"+ "</br>"
				+ "<input type='submit' value='Registrarse'>"
				+ "</div>"
				+ "</body>"
				+ "</html>";
		
	}
}
