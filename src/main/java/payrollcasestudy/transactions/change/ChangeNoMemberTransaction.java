package payrollcasestudy.transactions.change;

import payrollcasestudy.boundaries.PayrollDatabase;
import payrollcasestudy.entities.Employee;
import payrollcasestudy.entities.affiliations.UnionAffiliation;
import payrollcasestudy.transactions.Transaction;

public class ChangeNoMemberTransaction implements Transaction {
	PayrollDatabase database = PayrollDatabase.globalPayrollDatabase;
    private int employeeId;
    private int memberId;
    
	public ChangeNoMemberTransaction(int employeeId, int memberId) {
		this.employeeId = employeeId;
		this.memberId = memberId;
	}

	public void execute() {
		Employee employee = database.getEmployee(employeeId);
		if (employee!=null)
			{
				employee.setUnionAffiliation(UnionAffiliation.NO_AFFILIATION);
				if(database.getUnionMember(memberId)!=null)
					database.deleteUnionMember(memberId);
        	}
		}

}
