package payrollcasestudy.transactions.change;

import payrollcasestudy.entities.Employee;
import payrollcasestudy.entities.ServiceCharge;
import payrollcasestudy.entities.affiliations.NewUnionAffiliation;
import payrollcasestudy.transactions.Transaction;

public class ChangeNoMemberTransaction extends ChangeEmployeeTransaction {
	
	
	public ChangeNoMemberTransaction(int employeeId)
	{
		super(employeeId);
	}

	@Override
	public void changeEmployee(Employee employee) {
		int memberId = employee.getUnionAffiliation().getNumberId();
		employee.setUnionAffiliation(NewUnionAffiliation.NO_AFFILIATION);
		database.deleteUnionMember(memberId);
	}

	

}
