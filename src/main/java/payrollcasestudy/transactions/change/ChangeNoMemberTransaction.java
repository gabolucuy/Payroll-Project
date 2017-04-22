package payrollcasestudy.transactions.change;

import static org.hamcrest.Matchers.nullValue;

import payrollcasestudy.boundaries.PayrollDatabase;
import payrollcasestudy.entities.Employee;
import payrollcasestudy.entities.affiliations.UnionAffiliation;
import payrollcasestudy.transactions.Transaction;

public class ChangeNoMemberTransaction implements Transaction {
	PayrollDatabase database = PayrollDatabase.globalPayrollDatabase;
    private int employeeId;

	public ChangeNoMemberTransaction(int employeeId) {
		this.employeeId = employeeId;
	}

	public void execute() {
		Employee employee = database.getEmployee(employeeId);
        employee.setUnionAffiliation(null);
	}

}
