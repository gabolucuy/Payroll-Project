import static spark.Spark.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import controller.EmployeeController;
import controller.PayDayController;
import controller.PayrollController;
import payrollcasestudy.boundaries.MemoryDB;
import payrollcasestudy.boundaries.PayrollDatabase;
import payrollcasestudy.boundaries.Repository;
import payrollcasestudy.entities.Employee;
import payrollcasestudy.entities.PayCheck;
import payrollcasestudy.entities.TimeCard;
import payrollcasestudy.entities.SalesReceipt;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;



public class Main {
	static EmployeeController employeeController;
	static PayrollController payrollController;
	static VelocityTemplateEngine velocity;
	private static PayrollDatabase payrollDatabase = new PayrollDatabase();
	private static Repository repository =  new  MemoryDB();
	
	public static void main(String[] args) 
		{
		root();
		
		toGeneratePayrrolls();
		
		toSeeregistrationFormNewEmployee();
		
		toViewEmployees();
		toViewEmployee();
		toAddHours();
		toAddSales();
		
		toSeeRegistrationFormForNewEmployee();
		toAddHoursToEmployee();
		toAddSalesToEmployee();
		employeeCreatedSuccessfully();
		hourlyEmployeeCreatedSuccessfully();
		comisionedEmployeeCreatedSuccessfully();
		paymentsMadeSuccessfully();
		hoursSuccessfullyAdded();
		satisfactoryAggregateSalesAmount();
		
		}

	private static void satisfactoryAggregateSalesAmount() {
		HashMap<String,Object> view = new HashMap<String, Object>();
		post("/addSalestoEmployee", (request, response) -> {
			EmployeeController.addSalesToEmployee(request.queryParams("employeeId"),request.queryParams("sale"),request.queryParams("year"),request.queryParams("month"),request.queryParams("day"), repository);
			view.put("employeeId",request.queryParams("employeeId")); 
			return new ModelAndView(view, "Messages/satisfactoryAggregateSalesAmount.vtl");
	    }, new VelocityTemplateEngine());
	}

	private static void hoursSuccessfullyAdded() {
		HashMap<String,Object> view = new HashMap<String, Object>();
		post("/addHourstoEmployee", (request, response) -> {
			EmployeeController.addHoursToEmployee(request.queryParams("employeeId"),request.queryParams("hours"),request.queryParams("year"),request.queryParams("month"),request.queryParams("day"), repository);
			view.put("employeeId",request.queryParams("employeeId")); 
			return new ModelAndView(view, "Messages/hoursSuccessfullyAdded.vtl");
	    }, new VelocityTemplateEngine());
	}

	private static void paymentsMadeSuccessfully() {
		post("/pagarATodosLosEmpleados", (request, response) -> {
			PayDayController.pagarATodosLosEmpleados(request.queryParams("year"),request.queryParams("month"),request.queryParams("day"), repository);
			return new VelocityTemplateEngine().render(
				        new ModelAndView(new HashMap(), "Messages/paymentsMadeSuccessfully.vtl")
				    );
				});
	}

	private static void comisionedEmployeeCreatedSuccessfully() {
		HashMap<String,Object> view = new HashMap<String, Object>();
		post("/registrarEmpleadoSueldoFijoComisionado", (request, response) -> {
			view.put("message",EmployeeController.registrar_empleado_asalariadoComision(request.queryParams("nombre_empleado"),request.queryParams("direccion_empleado"),request.queryParams("ci"), request.queryParams("amount"),request.queryParams("comision"), repository));
			return new ModelAndView(view, "Messages/employeeCreatedSuccessfully.vtl");
	    }, new VelocityTemplateEngine());
	}

	private static void hourlyEmployeeCreatedSuccessfully() {
		HashMap<String,Object> view = new HashMap<String, Object>();
		post("/registrarEmpleadoPorHoras", (request, response) -> {
			view.put("message",EmployeeController.registrar_empleado_por_horas(request.queryParams("nombre_empleado"),request.queryParams("direccion_empleado"),request.queryParams("ci"), request.queryParams("amount"), repository));
			return new ModelAndView(view, "Messages/employeeCreatedSuccessfully.vtl");
	    }, new VelocityTemplateEngine());
	}

	private static void employeeCreatedSuccessfully() {
		HashMap<String,Object> view = new HashMap<String, Object>();
		post("/registrarEmpleadoSueldoFijo", (request, response) -> {
			view.put("message",EmployeeController.registrar_empleado_asalariado(request.queryParams("nombre_empleado"),request.queryParams("direccion_empleado"),request.queryParams("ci"), request.queryParams("amount"), repository)); 
			return new ModelAndView(view, "Messages/employeeCreatedSuccessfully.vtl");
	    }, new VelocityTemplateEngine());
	}

	private static void toAddSalesToEmployee() {
		HashMap<String,Object> view = new HashMap<String, Object>();
		get("/addSales", (req, res) -> {
			ArrayList<Employee> employees=new ArrayList<>();
			employees =EmployeeController.getListOfAllCommissionedEmployees(repository);
			view.put("employees", employees);
		      return new ModelAndView(view, "Employee/viewCommissionedEmployees.vtl");
		    }, new VelocityTemplateEngine());
	}

	private static void toAddHoursToEmployee() {
		HashMap<String,Object> view = new HashMap<String, Object>();
		get("/addHours", (req, res) -> {
			ArrayList<Employee> employees=new ArrayList<>();
			employees =EmployeeController.getListOfAllHourlyEmployees(repository);
			view.put("employees", employees);
		      return new ModelAndView(view, "Employee/viewHourlyEmployees.vtl");
		    }, new VelocityTemplateEngine());
	}

	private static void toSeeRegistrationFormForNewEmployee() {
		get("/newEmployee", (req, res) -> {
		    return new VelocityTemplateEngine().render(
		        new ModelAndView(new HashMap(), "Employee/registerEmployee.vtl")
		    );
		});
	}

	private static void toAddSales() {
		HashMap<String,Object> view = new HashMap<String, Object>();
		get("/addSales/:id", (request, response) -> {
			
			Employee employee = EmployeeController.getEmployee(Integer.parseInt(request.params(":id")), repository);
			view.put("employee", employee);
			ArrayList<SalesReceipt> receipts=new ArrayList<>();
			receipts= EmployeeController.getReceipts(Integer.parseInt(request.params(":id")), repository);
			view.put("receipts", receipts);
		      return new ModelAndView(view, "Employee/addSalesForm.vtl");
		    }, new VelocityTemplateEngine());
	}

	private static void toAddHours() {
		HashMap<String,Object> view = new HashMap<String, Object>();
		get("/addHours/:id", (request, response) -> {
			
			Employee employee = EmployeeController.getEmployee(Integer.parseInt(request.params(":id")), repository);
			view.put("employee", employee);
			ArrayList<TimeCard> timeCards=new ArrayList<>();
			timeCards= EmployeeController.getTimeCards(Integer.parseInt(request.params(":id")), repository);
			view.put("timeCards", timeCards);
		      return new ModelAndView(view, "Employee/addHoursForm.vtl");
		    }, new VelocityTemplateEngine());
	}

	private static void toViewEmployee() {
		HashMap<String,Object> view = new HashMap<String, Object>();
		get("/ver_empleado/:id", (request, response) -> {
			ArrayList<PayCheck> paychecks=new ArrayList<>();
			paychecks =EmployeeController.getAllPaychecksOfEmployee(Integer.parseInt(request.params(":id")), repository);
			Employee employee = EmployeeController.getEmployee(Integer.parseInt(request.params(":id")), repository);
			view.put("employee", employee);
			view.put("paychecks", paychecks);
		      return new ModelAndView(view, "Employee/showEmployee.vtl");
		    }, new VelocityTemplateEngine());
	}

	private static void toViewEmployees() {
		HashMap<String,Object> view = new HashMap<String, Object>();
		get("/ver_lista_empleados", (request, response) -> {
			ArrayList<Employee> employees=new ArrayList<>();
			employees =EmployeeController.getListOfAllEmployees(repository);
			view.put("employees", employees);
		      return new ModelAndView(view, "Employee/showEmployees.vtl");
		    }, new VelocityTemplateEngine());
	}

	private static void toSeeregistrationFormNewEmployee() {
		get("/registrar_Nuevo_Empleado", (req, res) -> {
		    return new VelocityTemplateEngine().render(
			        new ModelAndView(new HashMap(), "home.vtl")
			    );
			});
	}

	private static void toGeneratePayrrolls() {
		get("/generar_papeletas", (req, res) -> {
		    return new VelocityTemplateEngine().render(
			        new ModelAndView(new HashMap(), "PapeletasDePago/generar_papeletas.vtl")
			    );
			});
	}

	private static void root() {
		get("/", (request, response) -> {
		    return new VelocityTemplateEngine().render(
			        new ModelAndView(new HashMap(), "home.vtl")
			    );
			});
	}
	

}
