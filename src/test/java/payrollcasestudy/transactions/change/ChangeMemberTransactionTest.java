package payrollcasestudy.transactions.change;

import org.junit.Rule;
import org.junit.Test;
import payrollcasestudy.DatabaseResource;
import payrollcasestudy.boundaries.MemoryDB;
import payrollcasestudy.boundaries.Repository;
import payrollcasestudy.entities.Employee;
import payrollcasestudy.entities.affiliations.NewUnionAffiliation;
import payrollcasestudy.transactions.add.AddEmployeeTransaction;
import payrollcasestudy.transactions.add.AddHourlyEmployeeTransaction;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static payrollcasestudy.TestConstants.*;

public class ChangeMemberTransactionTest {

	public Repository repository = new MemoryDB();

    @Test
    public void testChangeMemberTransaction() throws Exception {
        int employeeId = 2;
        int memberId = 7734;
        AddEmployeeTransaction addEmployeeTransaction =
                new AddHourlyEmployeeTransaction(employeeId, "Bill", "Home", 15.25);
        addEmployeeTransaction.execute(repository);

        ChangeEmployeeTransaction changeMemberTransaction =
                new ChangeMemberTransaction(employeeId, memberId, 99.42);
        changeMemberTransaction.execute(repository);

        Employee employee = repository.getEmployee(employeeId);
        assertThat(employee.getUnionAffiliation(), is(notNullValue()));

        NewUnionAffiliation unionAffiliation = employee.getUnionAffiliation();
        assertThat(unionAffiliation.getDues(), is(closeTo(99.42, FLOAT_ACCURACY)));

        Employee member = repository.getUnionMember(memberId);
        assertThat(member, is(employee));
    }
    
    @Test
    public void testChangeSocialSecurityMemberTransaction() throws Exception {
        int employeeId = 2;
        int memberId = 7734;
        double deductionsForSocialSecurityMember = 0.12;
        AddEmployeeTransaction addEmployeeTransaction =
                new AddHourlyEmployeeTransaction(employeeId, "Bill", "Home", 15.25);
        addEmployeeTransaction.execute(repository);

        ChangeEmployeeTransaction changeMemberTransaction =
                new ChangeSocialSecurityMemberTransaction(employeeId, memberId);
        changeMemberTransaction.execute(repository);

        Employee employee = repository.getEmployee(employeeId);
        assertThat(employee.getUnionAffiliation(), is(notNullValue()));

        NewUnionAffiliation unionAffiliation = employee.getUnionAffiliation();
        assertThat(unionAffiliation.getDues(), is(closeTo(deductionsForSocialSecurityMember, FLOAT_ACCURACY)));

        Employee member = repository.getUnionMember(memberId);
        assertThat(member, is(employee));
    }
   
}
