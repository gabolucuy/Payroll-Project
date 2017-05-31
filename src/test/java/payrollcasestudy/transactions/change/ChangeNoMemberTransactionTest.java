package payrollcasestudy.transactions.change;

import org.junit.Rule;
import org.junit.Test;
import payrollcasestudy.DatabaseResource;
import payrollcasestudy.boundaries.MemoryDB;

import payrollcasestudy.boundaries.Repository;
import payrollcasestudy.entities.Employee;
import payrollcasestudy.entities.affiliations.NewUnionAffiliation;
import payrollcasestudy.entities.affiliations.NormalAffiliation;
import payrollcasestudy.entities.affiliations.UnionAffiliation;
import payrollcasestudy.transactions.Transaction;
import payrollcasestudy.transactions.add.AddHourlyEmployeeTransaction;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class ChangeNoMemberTransactionTest {
	
	public Repository repository = new MemoryDB();

    @Test
    public void testChangeMemberTransaction() throws Exception {
        

        int employeeId = 2;
        int memberId = 7734;
        Transaction addEmployeeTransaction =
                new AddHourlyEmployeeTransaction(employeeId, "Bill", "Home", 15.25);
        addEmployeeTransaction.execute(repository);

        Employee employee = repository.getEmployee(employeeId);
        NewUnionAffiliation unionAffiliation = new NormalAffiliation(memberId,92.1);
        employee.setUnionAffiliation(unionAffiliation);
        assertThat(employee.getUnionAffiliation(), is(unionAffiliation));

        repository.addUnionMember(memberId, employee);
        assertThat(repository.getUnionMember(memberId), is(employee));

        Transaction noMemberTransaction = new ChangeNoMemberTransaction(employeeId);
        noMemberTransaction.execute(repository);

        employee = repository.getEmployee(employeeId);
        assertThat(employee.getUnionAffiliation(), is(NewUnionAffiliation.NO_AFFILIATION));

        assertThat(repository.getUnionMember(memberId), is(nullValue()));
    }
}
