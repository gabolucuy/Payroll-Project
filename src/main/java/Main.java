import static spark.Spark.*;

import java.util.HashMap;

import controller.EmployeeController;
import payrollcasestudy.boundaries.PayrollDatabase;
import payrollcasestudy.entities.Employee;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;



public class Main {
	static EmployeeController employeeController;
	static VelocityTemplateEngine velocity;
	private static PayrollDatabase payrollDatabase = new PayrollDatabase();

	public static void main(String[] args) 
		{
		get("/newSalariedEmployee", (req, res) -> {
		    return new VelocityTemplateEngine().render(
		        new ModelAndView(new HashMap(), "registernewSalariedEmployeeForm.vtl")
		    );
		});
		get("/newHourlyEmployee", (req, res) -> {
		    return new VelocityTemplateEngine().render(
		        new ModelAndView(new HashMap(), "registernewHourlyEmployeeForm.vtl")
		    );
		});
		get("/", (request, response) -> {
		    return new VelocityTemplateEngine().render(
			        new ModelAndView(new HashMap(), "home.vtl")
			    );
			});
		get("/ver_lista_empleados", (request, response) -> ver_lista_empleados());
		post("/registrarEmpleadoSueldoFijo", (request, response) -> EmployeeController.registrar_empleado_asalariado(request.queryParams("nombre_empleado"),request.queryParams("direccion_empleado"),request.queryParams("ci"), request.queryParams("amount")));
		post("/registrarEmpleadoPorHoras", (request, response) -> EmployeeController.registrar_empleado_por_horas(request.queryParams("nombre_empleado"),request.queryParams("direccion_empleado"),request.queryParams("ci"), request.queryParams("amount")));

		}
	public static String ver_lista_empleados(){
		String lista = null;
		lista = payrollDatabase.showEmployees();
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
		+ lista
		+ "<form action='/registrar_Nuevo_Empleado'>"
	    + "<input type='submit' value='Registrar nuevo empleado' />"
	    +"</form>"
		+ "</div>"
		+ "</body>"
		+ "</html>";
	}
}
