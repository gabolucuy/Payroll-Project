package payrollcasestudy.transactions;

import payrollcasestudy.boundaries.PayrollDatabase;
import payrollcasestudy.entities.Employee;
import payrollcasestudy.entities.PayCheck;
import payrollcasestudy.transactions.add.AddTimeCardTransaction;

import java.util.*;

public class PaydayTransaction implements Transaction{
    private Calendar payDate;
    private int counter=1;
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
                payCheck.setMemberId(employeeId);
                payChecks.put(employeeId, payCheck);               
                employee.payDay(payCheck);
                PayrollDatabase.globalPayrollDatabase.addPayCheck(counter, payCheck);
                counter++;
            }
        }
    }

    public PayCheck getPaycheck(int employeeId) {
        return payChecks.get(employeeId);
    }
     
}
