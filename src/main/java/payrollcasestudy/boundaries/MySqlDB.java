package payrollcasestudy.boundaries;

import java.sql.Connection;
import java.sql.DriverManager;
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
	@Override
	public Employee getEmployee(int employeeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addEmployee(int employeeId, Employee employee) {
		int preparedStatement;
		try {
			Connection connection = DriverManager.getConnection(localhost, user, password);
			String employeeQuery = "INSERT INTO payroll.employee "
					+ "(member_id, name, address, paymentClassification) "
					+ "VALUES ('"+employee.getEmployeeId()+"', '"+employee.getName()+"', '"+employee.getAddress()+"', 'salaried')";
			String employeePaymentClasificationQuery = getEmployeePaymentClasificationQuery(employee);
			Statement statement = (Statement) connection.createStatement();
			Statement statement1 = (Statement) connection.createStatement();
			preparedStatement = ((java.sql.Statement) statement).executeUpdate(employeeQuery);
			preparedStatement = ((java.sql.Statement) statement1).executeUpdate(employeePaymentClasificationQuery);
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private String getEmployeePaymentClasificationQuery(Employee employee) {
		String query = "";
		if(employee.getPaymentClassification() instanceof  CommissionedPaymentClassification){
			query = commissionedQuery(employee);
		}
		if(employee.getPaymentClassification() instanceof  HourlyPaymentClassification){
			query = hourlyQuery(employee);
		}
		if(employee.getPaymentClassification() instanceof  SalariedClassification){
			query = salariedQuery(employee);
		}
		return query;
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
	public ArrayList<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<PayCheck> getAllPaychecksOfEmployee(int memberId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Employee> getAllHourlyEmployees() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Employee> getAllCommissionedEmployees() {
		// TODO Auto-generated method stub
		return null;
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
