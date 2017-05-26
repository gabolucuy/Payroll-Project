package payrollcasestudy.boundaries;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Set;

import com.mysql.jdbc.Statement;

import payrollcasestudy.entities.Employee;
import payrollcasestudy.entities.PayCheck;
import payrollcasestudy.entities.paymentclassifications.CommissionedPaymentClassification;
import payrollcasestudy.entities.paymentclassifications.HourlyPaymentClassification;
import payrollcasestudy.entities.paymentclassifications.PaymentClassification;
import payrollcasestudy.entities.paymentclassifications.SalariedClassification;

public class MySqlDB implements Repository {
	private String localhost = "jdbc:mysql://localhost/payroll";
	private String user = "admin";	
	private String password = "admin";
	Connection connection = null;
	public MySqlDB(){
		try {
			connection = DriverManager.getConnection(localhost, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void addEmployee(int employeeId, Employee employee) {
		Statement statement = null;
		Statement statement1 = null;
		try {
			String employeeQuery = addEmployeeQuery(employee);
			String employeePaymentClasificationQuery = getEmployeePaymentClasificationQuery(employee);
			 statement = (Statement) connection.createStatement();
			 statement1 = (Statement) connection.createStatement();
			 statement.executeUpdate(employeeQuery);
			 statement1.executeUpdate(employeePaymentClasificationQuery);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private String getEmployeePaymentClasificationQuery(Employee employee) {
		if(employee.getPaymentClassification() instanceof  CommissionedPaymentClassification){
			return commissionedQuery(employee);
		}
		if(employee.getPaymentClassification() instanceof  HourlyPaymentClassification){
			return hourlyQuery(employee);
		}
		if(employee.getPaymentClassification() instanceof  SalariedClassification){
			return salariedQuery(employee);
		}
		return null;
	}
	
	
	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteEmployee(int employeeId) {
		// TODO Auto-generated method stub

	}

	@Override
	public Employee getUnionMember(int memberId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addPayCheck(int memberId, PayCheck paycheck) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addUnionMember(int memberId, Employee employee) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteUnionMember(int memberId) {
		// TODO Auto-generated method stub

	}

	@Override
	public Set<Integer> getAllEmployeeIds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PayCheck getPaycheck(int memberId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee getEmployee(int employeeId) {
		Statement statement = null;
		try {
			statement = (Statement) connection.createStatement();
			ResultSet employeeDB = statement.executeQuery(getOneEmployeeQuery(employeeId));
			if (employeeDB.next()) {
			Employee employee = new Employee(Integer.parseInt(employeeDB.getString("member_id")),
					employeeDB.getString("name"),employeeDB.getString("address"));return employee;}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	private String getOneEmployeeQuery(int employeeId) {
		return "SELECT member_id, name, address, paymentClassification FROM payroll.employee WHERE member_id='"+employeeId+"'";
	}

	@Override
	public ArrayList<Employee> getAllEmployees() {
		ArrayList<Employee> employeesList = new ArrayList<Employee>();
		Statement statement = null;
		try{
			statement = (Statement) connection.createStatement();
			ResultSet employeeDB = statement.executeQuery(allEmployeesQuery());
			while(employeeDB.next()){
				Employee employee = new Employee(Integer.parseInt(employeeDB.getString("member_id")),
						employeeDB.getString("name"),employeeDB.getString("address"));
				employeesList.add(employee);
			}
			return employeesList;
		}catch (Exception e){
			System.err.println(e);
			return employeesList;
		}
	}

	private String allEmployeesQuery() {
		return "SELECT * FROM payroll.employee";
	}

	@Override
	public ArrayList<PayCheck> getAllPaychecksOfEmployee(int memberId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ArrayList<Employee> getAllHourlyEmployees() {
		ArrayList<Employee> employeesList = new ArrayList<Employee>();
		Statement statement = null;
		try{
			statement = (Statement) connection.createStatement();
			ResultSet employeeDB = statement.executeQuery(hourlyEmployeesQuery());
			while(employeeDB.next()){
				Employee employee = new Employee(Integer.parseInt(employeeDB.getString("member_id")),
						employeeDB.getString("name"),employeeDB.getString("address"));
				employeesList.add(employee);
			}
			return employeesList;
		}catch (Exception e){
			System.err.println(e);
			return employeesList;
		}
	}
	private String hourlyEmployeesQuery() {
		return "SELECT member_id, name, address, paymentClassification FROM payroll.employee WHERE paymentClassification='hourly'";
	}

	@Override
	public ArrayList<Employee> getAllCommissionedEmployees() {
		ArrayList<Employee> employeesList = new ArrayList<Employee>();
		Statement statement = null;
		try{
			statement = (Statement) connection.createStatement();
			ResultSet employeeDB = statement.executeQuery(commissionedEmployeesQuery());
			while(employeeDB.next()){
				Employee employee = new Employee(Integer.parseInt(employeeDB.getString("member_id")),
						employeeDB.getString("name"),employeeDB.getString("address"));
				employeesList.add(employee);
			}
			return employeesList;
		}catch (Exception e){
			System.err.println(e);
			return employeesList;
		}
	}
	private String commissionedEmployeesQuery() {
		return "SELECT member_id, name, address, paymentClassification FROM payroll.employee WHERE paymentClassification='commissioned'";
	}

	private String addEmployeeQuery(Employee employee) {
		return "INSERT INTO payroll.employee "
				+ "(member_id, name, address, paymentClassification) "
				+ "VALUES ('"+employee.getEmployeeId()+"', '"+employee.getName()+"', '"+employee.getAddress()+"', '"+employee.typeOfEmployee()+"')";
	}
	private String salariedQuery(Employee employee) {
		SalariedClassification salariedClassification =  (SalariedClassification) employee.getPaymentClassification();
		return "INSERT INTO payroll.salariedclassification "
				+ "(member_id,salary ) "
				+ "VALUES ('"+employee.getEmployeeId()+"', '"+salariedClassification.getSalary()+"')";
	}

	private String hourlyQuery(Employee employee) {
		HourlyPaymentClassification hourlyClassification =  (HourlyPaymentClassification) employee.getPaymentClassification(); 
		return "INSERT INTO payroll.hourlyclassification "
				+ "(member_id, hourlyRate) "
				+ "VALUES ('"+employee.getEmployeeId()+"', '"+hourlyClassification.getHourlyRate()+"')";
	}

	private String commissionedQuery(Employee employee) {
		CommissionedPaymentClassification commissionedPaymentClassification =  (CommissionedPaymentClassification) employee.getPaymentClassification();
		return "INSERT INTO payroll.commissionedclassification "
				+ "(member_id,commissionRate, monthlySalary) "
				+ "VALUES ('"+employee.getEmployeeId()+"', '"+commissionedPaymentClassification.getCommissionRate()+"' ,'"+commissionedPaymentClassification.getMonthlySalary()+"')";
	}

}
