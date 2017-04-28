package payrollcasestudy.transactions.change;

import payrollcasestudy.entities.Employee;
import payrollcasestudy.entities.affiliations.UnionAffiliation;

public class ChangeMemberTransaction extends ChangeEmployeeTransaction{

	private int memberId;
	private double d;

	public ChangeMemberTransaction(int employeeId, int memberId, double d) {
		super(employeeId);
		this.memberId = memberId;
		this.d = d;
	}

	@Override
	public void changeEmployee(Employee employee) {
		employee.setUnionAffiliation(new UnionAffiliation(memberId,d));
		database.addUnionMember(memberId, employee);
		
	}

}
