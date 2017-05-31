package payrollcasestudy.transactions.add;

import org.junit.Rule;
import org.junit.Test;
import payrollcasestudy.DatabaseResource;
import payrollcasestudy.boundaries.MemoryDB;
import payrollcasestudy.boundaries.Repository;
import payrollcasestudy.entities.Employee;
import payrollcasestudy.entities.ServiceCharge;
import payrollcasestudy.entities.affiliations.NewUnionAffiliation;
import payrollcasestudy.entities.affiliations.NormalAffiliation;
import payrollcasestudy.entities.affiliations.UnionAffiliation;
import payrollcasestudy.transactions.Transaction;
import payrollcasestudy.transactions.add.AddServiceChargeTransaction;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static payrollcasestudy.TestConstants.*;

public class AddServiceChargeTransactionTest {

	public Repository repository = new MemoryDB();

    @Test
    public void testAddServiceCharge() throws Exception {
        int employeeId = 2;
        Transaction addEmployeeTransaction =
                new AddHourlyEmployeeTransaction(employeeId, "Bill", "Home", 15.25);
        addEmployeeTransaction.execute(repository);

        Employee employee = repository.getEmployee(employeeId);
        assertThat(employee, is(notNullValue()));

        int memberId = 86; //Maxwell Smart
        NewUnionAffiliation unionAffiliation = new NormalAffiliation(memberId,12.5);
        employee.setUnionAffiliation(unionAffiliation);
        repository.addUnionMember(memberId, employee);
        assertThat(repository.getUnionMember(memberId), is(notNullValue()));

        Calendar date = new GregorianCalendar(2001, 11, 01);
        AddServiceChargeTransaction addServiceChargeTransaction = new AddServiceChargeTransaction(memberId, date, 12.95);
        addServiceChargeTransaction.execute(repository);
        ServiceCharge serviceCharge = unionAffiliation.getServiceCharge(date);
        assertThat(serviceCharge, is(notNullValue()));
        assertThat(serviceCharge.getAmount(), is(closeTo(12.95, FLOAT_ACCURACY)));
    }
}
