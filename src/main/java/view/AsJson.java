package view;

import static spark.Spark.get;
import static spark.Spark.post;

import java.util.ArrayList;
import java.util.HashMap;

import controller.EmployeeController;
import controller.PayDayController;
import payrollcasestudy.boundaries.MemoryDB;
import payrollcasestudy.boundaries.MySqlDB;
import payrollcasestudy.boundaries.Repository;
import payrollcasestudy.entities.Employee;
import payrollcasestudy.entities.PayCheck;
import payrollcasestudy.entities.SalesReceipt;
import payrollcasestudy.entities.TimeCard;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

public class AsJson implements ToShow {

	static VelocityTemplateEngine velocity;
	private static Repository repository =  new  MemoryDB();
	static EmployeeController employeeController = new EmployeeController(repository);
	static PayDayController payDayController = new PayDayController(repository);
	
	@Override
	public void root() {
		get("/", (request, response) -> {
		    return new VelocityTemplateEngine().render(
			        new ModelAndView(new HashMap(), "home.vtl")
			    );
			});
	}

	@Override
	public void toGeneratePayrrolls() {
		get("/generar_papeletas", (req, res) -> {
		    return new VelocityTemplateEngine().render(
			        new ModelAndView(new HashMap(), "PapeletasDePago/generar_papeletas.vtl")
			    );
			});
	}

	@Override
	public void toSeeregistrationFormNewEmployee() {
		get("/registrar_Nuevo_Empleado", (req, res) -> {
		    return new VelocityTemplateEngine().render(
			        new ModelAndView(new HashMap(), "home.vtl")
			    );
			});
	}

	@Override
	public void toViewEmployees() {
		HashMap<String,Object> view = new HashMap<String, Object>();
		get("/ver_lista_empleados", (request, response) -> {
			ArrayList<Employee> employees = new ArrayList<>();
			employees = employeeController.getListOfAllEmployees();
			view.put("employees", employees);
		      return new ModelAndView(view, "Employee/showEmployees.vtl");
		    }, new VelocityTemplateEngine());
	}

	@Override
	public void toViewEmployee() {
		HashMap<String,Object> view = new HashMap<String, Object>();
		get("/ver_empleado/:id", (request, response) -> {
			ArrayList<PayCheck> paychecks=new ArrayList<>();
			paychecks = employeeController.getAllPaychecksOfEmployee(Integer.parseInt(request.params(":id")));
			Employee employee = employeeController.getEmployee(Integer.parseInt(request.params(":id")));
			view.put("employee", employee);
			view.put("paychecks", paychecks);
		      return new ModelAndView(view, "Employee/showEmployee.vtl");
		    }, new VelocityTemplateEngine());
	}

	@Override
	public void toAddHours() {
		HashMap<String,Object> view = new HashMap<String, Object>();
		get("/addHours/:id", (request, response) -> {
			
			Employee employee = employeeController.getEmployee(Integer.parseInt(request.params(":id")));
			view.put("employee", employee);
			ArrayList<TimeCard> timeCards=new ArrayList<>();
			timeCards= employeeController.getTimeCards(Integer.parseInt(request.params(":id")));
			view.put("timeCards", timeCards);
		      return new ModelAndView(view, "Employee/addHoursForm.vtl");
		    }, new VelocityTemplateEngine());
	}

	@Override
	public void toAddSales() {
		HashMap<String,Object> view = new HashMap<String, Object>();
		get("/addSales/:id", (request, response) -> {
			
			Employee employee = employeeController.getEmployee(Integer.parseInt(request.params(":id")));
			view.put("employee", employee);
			ArrayList<SalesReceipt> receipts=new ArrayList<>();
			receipts= employeeController.getReceipts(Integer.parseInt(request.params(":id")));
			view.put("receipts", receipts);
		      return new ModelAndView(view, "Employee/addSalesForm.vtl");
		    }, new VelocityTemplateEngine());
	}

	@Override
	public void toSeeRegistrationFormForNewEmployee() {
		get("/newEmployee", (req, res) -> {
		    return new VelocityTemplateEngine().render(
		        new ModelAndView(new HashMap(), "Employee/registerEmployee.vtl")
		    );
		});

	}

	@Override
	public void toAddHoursToEmployee() {
		HashMap<String,Object> view = new HashMap<String, Object>();
		get("/addHours", (req, res) -> {
			ArrayList<Employee> employees=new ArrayList<>();
			employees =employeeController.getListOfAllHourlyEmployees();
			view.put("employees", employees);
		      return new ModelAndView(view, "Employee/viewHourlyEmployees.vtl");
		    }, new VelocityTemplateEngine());
	}

	@Override
	public void toAddSalesToEmployee() {
		HashMap<String,Object> view = new HashMap<String, Object>();
		get("/addSales", (req, res) -> {
			ArrayList<Employee> employees=new ArrayList<>();
			employees =employeeController.getListOfAllCommissionedEmployees();
			view.put("employees", employees);
		      return new ModelAndView(view, "Employee/viewCommissionedEmployees.vtl");
		    }, new VelocityTemplateEngine());
	}

	@Override
	public void employeeCreatedSuccessfully() {
		HashMap<String,Object> view = new HashMap<String, Object>();
		post("/registrarEmpleadoSueldoFijo", (request, response) -> {
			view.put("message",employeeController.registrar_empleado_asalariado(request.queryParams("nombre_empleado"),request.queryParams("direccion_empleado"),request.queryParams("ci"), request.queryParams("amount"))); 
			return new ModelAndView(view, "Messages/employeeCreatedSuccessfully.vtl");
	    }, new VelocityTemplateEngine());

	}

	@Override
	public void hourlyEmployeeCreatedSuccessfully() {
		HashMap<String,Object> view = new HashMap<String, Object>();
		post("/registrarEmpleadoPorHoras", (request, response) -> {
			view.put("message",employeeController.registrar_empleado_por_horas(request.queryParams("nombre_empleado"),request.queryParams("direccion_empleado"),request.queryParams("ci"), request.queryParams("amount")));
			return new ModelAndView(view, "Messages/employeeCreatedSuccessfully.vtl");
	    }, new VelocityTemplateEngine());
	}

	@Override
	public void comisionedEmployeeCreatedSuccessfully() {
		HashMap<String,Object> view = new HashMap<String, Object>();
		post("/registrarEmpleadoSueldoFijoComisionado", (request, response) -> {
			view.put("message",employeeController.registrar_empleado_asalariadoComision(request.queryParams("nombre_empleado"),request.queryParams("direccion_empleado"),request.queryParams("ci"), request.queryParams("amount"),request.queryParams("comision")));
			
			return new ModelAndView(view, "Messages/employeeCreatedSuccessfully.vtl");
	    }, new VelocityTemplateEngine());
	}

	@Override
	public void paymentsMadeSuccessfully() {
		post("/pagarATodosLosEmpleados", (request, response) -> {
			payDayController.pagarATodosLosEmpleados(request.queryParams("year"),request.queryParams("month"),request.queryParams("day"));
			 return new VelocityTemplateEngine().render(
				        new ModelAndView(new HashMap(), "Messages/paymentsMadeSuccessfully.vtl")
				    );
				});
	}

	@Override
	public void hoursSuccessfullyAdded() {
		HashMap<String,Object> view = new HashMap<String, Object>();
		post("/addHourstoEmployee", (request, response) -> {
			employeeController.addHoursToEmployee(request.queryParams("employeeId"),request.queryParams("hours"),request.queryParams("year"),request.queryParams("month"),request.queryParams("day"));
			view.put("employeeId",request.queryParams("employeeId")); 
			return new ModelAndView(view, "Messages/hoursSuccessfullyAdded.vtl");
	    }, new VelocityTemplateEngine());
	}

	@Override
	public void satisfactoryAggregateSalesAmount() {
		HashMap<String,Object> view = new HashMap<String, Object>();
		post("/addSalestoEmployee", (request, response) -> {
			employeeController.addSalesToEmployee(request.queryParams("employeeId"),request.queryParams("sale"),request.queryParams("year"),request.queryParams("month"),request.queryParams("day"));
			view.put("employeeId",request.queryParams("employeeId")); 
			return new ModelAndView(view, "Messages/satisfactoryAggregateSalesAmount.vtl");
	    }, new VelocityTemplateEngine());

	}

	@Override
	public void viewPaychecks() {
		HashMap<String,Object> view = new HashMap<String, Object>();
		get("/verPaychecks", (request, response) -> {
			String json;
			json = employeeController.getAllPaychecksAsJson();
			view.put("jsons", json);
		      return new ModelAndView(view, "Employee/viewPaychecksJson.vtl");
		    }, new VelocityTemplateEngine());
		}


}
