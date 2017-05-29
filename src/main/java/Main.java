import controller.EmployeeController;
import controller.PayDayController;
import payrollcasestudy.boundaries.MemoryDB;
import payrollcasestudy.boundaries.MySqlDB;
import payrollcasestudy.boundaries.Repository;
import view.AsHtml;
import view.AsJson;
import view.TypeOfView;

public class Main {
	public static void main(String[] args) {
		Repository repository =  new  MemoryDB();
		EmployeeController employeeController = new EmployeeController(repository);
		PayDayController payDayController = new PayDayController(repository);
		TypeOfView view = new AsHtml(employeeController,payDayController);
		view.init();
		}
}
