package payrollcasestudy.transactions.change;

import payrollcasestudy.boundaries.PayrollDatabase;
import payrollcasestudy.entities.Employee;
import payrollcasestudy.entities.affiliations.UnionAffiliation;
import payrollcasestudy.transactions.Transaction;

public class ChangeMemberTransaction implements Transaction {

	PayrollDatabase database = PayrollDatabase.globalPayrollDatabase;
    private int employeeId;
    private int memberId;
    private double weeklyUnionDues;
    
	public ChangeMemberTransaction(int employeeId, int memberId, double weeklyUnionDues) {
		this.employeeId=employeeId;
		this.memberId=memberId;
		this.weeklyUnionDues=weeklyUnionDues;
	}

	public void execute() {
		Employee employee = database.getEmployee(employeeId);
		if (employee!=null)
		{
			UnionAffiliation unionAffiliation = new UnionAffiliation(memberId, weeklyUnionDues);
			employee.setUnionAffiliation(unionAffiliation);
			database.addUnionMember(memberId, employee);
    	}
	}

}
