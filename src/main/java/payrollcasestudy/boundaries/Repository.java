package payrollcasestudy.boundaries;

import java.util.ArrayList;
import java.util.Set;

import payrollcasestudy.entities.Employee;
import payrollcasestudy.entities.PayCheck;
import payrollcasestudy.entities.paymentclassifications.CommissionedPaymentClassification;
import payrollcasestudy.entities.paymentclassifications.HourlyPaymentClassification;

public interface Repository {
		public Employee getEmployee(int employeeId) ;

	    public void addEmployee(int employeeId, Employee employee) ;
	    public void clear();
	    public void deleteEmployee(int employeeId) ;
	    public Employee getUnionMember(int memberId) ;
	    public void addPayCheck(int memberId, PayCheck paycheck);
	    public void addUnionMember(int memberId, Employee employee);
	    public void deleteUnionMember(int memberId) ;
	    public Set<Integer> getAllEmployeeIds() ;
	    public PayCheck getPaycheck(int memberId);
	    public ArrayList<Employee> getAllEmployees();
	    public ArrayList<PayCheck> getAllPaychecksOfEmployee(int memberId);  
		public ArrayList<Employee> getAllHourlyEmployees();
		public ArrayList<Employee> getAllCommissionedEmployees();
		
}
