package payrollcasestudy.transactions;

import payrollcasestudy.boundaries.PayrollDatabase;
import payrollcasestudy.entities.Employee;
import payrollcasestudy.entities.PayCheck;

import java.util.*;

public class PaydayTransaction implements Transaction{
    private Calendar payDate;
    private PayrollDatabase database = PayrollDatabase.globalPayrollDatabase;
    private Map<Integer, PayCheck> payChecks = new HashMap<Integer, PayCheck>();
    
    public PaydayTransaction(Calendar payDate) {
        this.payDate = payDate;
    }

    public void execute() {
        for (Integer employeeId: database.getAllEmployeeIds()){
            Employee employee = database.getEmployee(employeeId);
            if (employee.isPayDate(payDate)){
                PayCheck payCheck = new PayCheck(employee.getPayPeriodStartDay(payDate),payDate);
                //payCheck=new PayCheck(new GregorianCalendar(5,5,2001),new GregorianCalendar(5,5,2005));
        		
                payChecks.put(employeeId, payCheck);               
                employee.payDay(payCheck);
                PayrollDatabase.globalPayrollDatabase.addPayCheck(employeeId, payCheck);
            }
        }
    }

    public PayCheck getPaycheck(int employeeId) {
        return payChecks.get(employeeId);
    }
     
}
