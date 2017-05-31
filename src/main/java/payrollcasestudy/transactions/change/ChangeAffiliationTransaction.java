package payrollcasestudy.transactions.change;

import payrollcasestudy.entities.Employee;
import payrollcasestudy.entities.affiliations.NewUnionAffiliation;


public abstract class ChangeAffiliationTransaction extends ChangeEmployeeTransaction{

    public ChangeAffiliationTransaction(int employeeId) {
        super(employeeId);
    }

    @Override
    public void changeEmployee(Employee employee) {
        recordMembership(employee);
        employee.setUnionAffiliation(getAffiliation());
    }

    protected abstract NewUnionAffiliation getAffiliation();

    protected abstract void recordMembership(Employee employee);

}
