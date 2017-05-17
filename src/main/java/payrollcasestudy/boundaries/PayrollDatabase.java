package payrollcasestudy.boundaries;

import payrollcasestudy.entities.Employee;
import payrollcasestudy.entities.PayCheck;
import payrollcasestudy.entities.paymentclassifications.CommissionedPaymentClassification;
import payrollcasestudy.entities.paymentclassifications.HourlyPaymentClassification;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

import java.util.*;

/**
 * Listing 19-3
 * Listing 19-4
 */
public class PayrollDatabase {
    public static PayrollDatabase globalPayrollDatabase = new PayrollDatabase();

    private Map<Integer, Employee> employees = new HashMap<Integer, Employee>();
    public Map<Integer, Employee> unionMembers = new HashMap<Integer, Employee>();
    public Map<Integer, PayCheck> globalPaydaysList = new HashMap<Integer, PayCheck>();

    public Employee getEmployee(int employeeId) {
        return employees.get(employeeId);
    }

    public void addEmployee(int employeeId, Employee employee) {
        employees.put(employeeId, employee);
    }

    public void clear(){
        employees.clear();
        unionMembers.clear();
    }

    public void deleteEmployee(int employeeId) {
        employees.put(employeeId, null);
    }

    public Employee getUnionMember(int memberId) {
        return unionMembers.get(memberId);
    }
    public PayCheck getPaycheck(int memberId){
    	return globalPaydaysList.get(memberId);
    }
    public void addPayCheck(int memberId, PayCheck paycheck) {
    	globalPaydaysList.put(memberId, paycheck);
    }
    public void addUnionMember(int memberId, Employee employee) {
        unionMembers.put(memberId, employee);
    }

    public void deleteUnionMember(int memberId) {
        unionMembers.remove(memberId);
    }

    public Set<Integer> getAllEmployeeIds() {
        return employees.keySet();
    }
    public ArrayList<Employee> getAllEmployees(){
    	ArrayList<Employee> listOfEmployees = new ArrayList<>();
    	for(Employee employee : employees.values()){
    		listOfEmployees.add(employee);
		}
    	return listOfEmployees;
    }

	public ArrayList<Employee> getAllHourlyEmployees() {
		ArrayList<Employee> listOfEmployees = new ArrayList<>();
    	for(Employee employee : employees.values()){
    		if(employee.getPaymentClassification() instanceof  HourlyPaymentClassification)
    		listOfEmployees.add(employee);
		}
    	return listOfEmployees;
		
	}
	
	public ArrayList<Employee> getAllCommissionedEmployees() {
		ArrayList<Employee> listOfEmployees = new ArrayList<>();
    	for(Employee employee : employees.values()){
    		if(employee.getPaymentClassification() instanceof  CommissionedPaymentClassification)
    		listOfEmployees.add(employee);
		}
    	return listOfEmployees;
		
	}
	    
    
    
    
    
}
