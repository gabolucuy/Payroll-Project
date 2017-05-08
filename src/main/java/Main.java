import static spark.Spark.*;

import java.util.ArrayList;
import java.util.HashMap;

import controller.EmployeeController;
import controller.PayDayController;
import controller.PayrollController;
import payrollcasestudy.boundaries.PayrollDatabase;
import payrollcasestudy.entities.Employee;
import payrollcasestudy.entities.PayCheck;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;



public class Main {
	static EmployeeController employeeController;
	static PayrollController payrollController;
	static VelocityTemplateEngine velocity;
	private static PayrollDatabase payrollDatabase = new PayrollDatabase();

	public static void main(String[] args) 
		{
		HashMap<String,Object> view = new HashMap<String, Object>();
		
		get("/", (request, response) -> {
		    return new VelocityTemplateEngine().render(
			        new ModelAndView(new HashMap(), "home.vtl")
			    );
			});
		
		get("/generar_papeletas", (req, res) -> {
		    return new VelocityTemplateEngine().render(
			        new ModelAndView(new HashMap(), "PapeletasDePago/generar_papeletas.vtl")
			    );
			});
		get("/registrar_Nuevo_Empleado", (req, res) -> {
		    return new VelocityTemplateEngine().render(
			        new ModelAndView(new HashMap(), "home.vtl")
			    );
			});
		
		get("/ver_lista_empleados", (request, response) -> {
			ArrayList<Employee> employees=new ArrayList<>();
			employees =EmployeeController.getListOfAllEmployees();
			view.put("employees", employees);
		      return new ModelAndView(view, "Employee/showEmployees.vtl");
		    }, new VelocityTemplateEngine());
		get("/ver_empleado/:id", (request, response) -> {
			
			Employee employee = EmployeeController.getEmployee(Integer.parseInt(request.params(":id")));
			PayCheck paycheck = EmployeeController.getPayCheck(Integer.parseInt(request.params(":id")));
			view.put("employee", employee);
			
			view.put("paycheck", paycheck);
		      return new ModelAndView(view, "Employee/showEmployee.vtl");
		    }, new VelocityTemplateEngine());
		
		//Registros
		get("/newEmployee", (req, res) -> {
		    return new VelocityTemplateEngine().render(
		        new ModelAndView(new HashMap(), "Employee/registerEmployee.vtl")
		    );
		});
		
		post("/registrarEmpleadoSueldoFijo", (request, response) -> EmployeeController.registrar_empleado_asalariado(request.queryParams("nombre_empleado"),request.queryParams("direccion_empleado"),request.queryParams("ci"), request.queryParams("amount")));
		post("/registrarEmpleadoPorHoras", (request, response) -> EmployeeController.registrar_empleado_por_horas(request.queryParams("nombre_empleado"),request.queryParams("direccion_empleado"),request.queryParams("ci"), request.queryParams("amount")));
		post("/registrarEmpleadoSueldoFijoComisionado", (request, response) -> EmployeeController.registrar_empleado_asalariadoComision(request.queryParams("nombre_empleado"),request.queryParams("direccion_empleado"),request.queryParams("ci"), request.queryParams("amount"),request.queryParams("comision")));
		post("/pagarATodosLosEmpleados",(request, response) ->PayDayController.pagarATodosLosEmpleados(request.queryParams("year"),request.queryParams("month"),request.queryParams("day")));
		}
	

}
