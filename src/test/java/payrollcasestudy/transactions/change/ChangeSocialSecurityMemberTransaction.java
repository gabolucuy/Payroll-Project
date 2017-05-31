package payrollcasestudy.transactions.change;

import payrollcasestudy.entities.Employee;
import payrollcasestudy.entities.affiliations.SocialSecurityUnionAffiliation;

public class ChangeSocialSecurityMemberTransaction extends ChangeEmployeeTransaction {
	private int memberId;
	
	public ChangeSocialSecurityMemberTransaction(int employeeId, int memberId) {
		super(employeeId);
		this.memberId = memberId;
		}
	
	@Override
	public void changeEmployee(Employee employee) {
		employee.setUnionAffiliation(new SocialSecurityUnionAffiliation(memberId));
		database.addUnionMember(memberId, employee);
		
	}

	

}
