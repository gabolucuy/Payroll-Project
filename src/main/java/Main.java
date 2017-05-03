import static spark.Spark.*;

import java.util.HashMap;

import controller.EmployeeController;
import controller.PayrollController;
import payrollcasestudy.boundaries.PayrollDatabase;
import payrollcasestudy.entities.Employee;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;



public class Main {
	static EmployeeController employeeController;
	static PayrollController payrollController;
	static VelocityTemplateEngine velocity;
	private static PayrollDatabase payrollDatabase = new PayrollDatabase();

	public static void main(String[] args) 
		{
		get("/newSalariedEmployee", (req, res) -> {
		    return new VelocityTemplateEngine().render(
		        new ModelAndView(new HashMap(), "registernewSalariedEmployeeForm.vtl")
		    );
		});
		get("/newSalariedEmployeeComisionaded", (req, res) -> {
		    return new VelocityTemplateEngine().render(
		        new ModelAndView(new HashMap(), "registernewSalariedEmployeeComisionadedForm.vtl")
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
		get("/ver_lista_empleados", (request, response) -> PayrollController.ver_lista_empleados());
		get("/registrar_Nuevo_Empleado", (req, res) -> {
		    return new VelocityTemplateEngine().render(
			        new ModelAndView(new HashMap(), "home.vtl")
			    );
			});
		post("/registrarEmpleadoSueldoFijo", (request, response) -> EmployeeController.registrar_empleado_asalariado(request.queryParams("nombre_empleado"),request.queryParams("direccion_empleado"),request.queryParams("ci"), request.queryParams("amount")));
		post("/registrarEmpleadoPorHoras", (request, response) -> EmployeeController.registrar_empleado_por_horas(request.queryParams("nombre_empleado"),request.queryParams("direccion_empleado"),request.queryParams("ci"), request.queryParams("amount")));
		post("/registrarEmpleadoSueldoFijoComisionado", (request, response) -> EmployeeController.registrar_empleado_asalariadoComision(request.queryParams("nombre_empleado"),request.queryParams("direccion_empleado"),request.queryParams("ci"), request.queryParams("amount"),request.queryParams("comision")));

		}
	

}
