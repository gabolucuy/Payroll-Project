import view.AsHtml;
import view.AsJson;
import view.ToShow;

public class Main {
	private static ToShow view = new AsJson();
	
	public static void main(String[] args) 
		{
			view.root();
			view.toGeneratePayrrolls();		
			view.toSeeregistrationFormNewEmployee();		
			view.toViewEmployees();
			view.toViewEmployee();
			view.toAddHours();
			view.toAddSales();	
			view.toSeeRegistrationFormForNewEmployee();	
			view.toAddHoursToEmployee();
			view.toAddSalesToEmployee();
			view.employeeCreatedSuccessfully();
			view.hourlyEmployeeCreatedSuccessfully();
			view.comisionedEmployeeCreatedSuccessfully();
			view.paymentsMadeSuccessfully();
			view.hoursSuccessfullyAdded();
			view.satisfactoryAggregateSalesAmount();
			view.viewPaychecks();
		
		}
	

}
