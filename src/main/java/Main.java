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
				+ "<body>"
				+ "<form method='get' action='/registrar_Nuevo_Empleado'>" 
				+ "<B>Nombre empleado: </B> nombre_empleado </br></br>"
				+ "<B>Ci empleado: </B> ci </br></br>"
				+ "<B>Direccion empleado</B> direccion_empleado </br></br>"
				+ "<input type='submit' value='Registrar otro empleado'"
				+ "</body>"
				+ "</html>";
	}

	private static String registro_form() {
		return "<html>"
				+ "<body>"
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
				+ "<input type='submit' value='registrarse'"
				+ "</body>"
				+ "</html>";
		
	}
}
