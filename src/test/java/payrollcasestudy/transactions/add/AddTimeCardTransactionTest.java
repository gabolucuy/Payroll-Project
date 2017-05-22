package payrollcasestudy.transactions.add;

import org.junit.Rule;
import org.junit.Test;
import payrollcasestudy.DatabaseResource;
import payrollcasestudy.boundaries.MemoryDB;
import payrollcasestudy.boundaries.Repository;
import payrollcasestudy.entities.Employee;
import payrollcasestudy.entities.TimeCard;
import payrollcasestudy.entities.paymentclassifications.HourlyPaymentClassification;
import payrollcasestudy.entities.paymentclassifications.PaymentClassification;
import payrollcasestudy.transactions.Transaction;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class AddTimeCardTransactionTest {

	public Repository repository = new MemoryDB();

    @Test
    public void testTimeCardTransaction(){
        int employeeId = 2;
        AddHourlyEmployeeTransaction addHourlyEmployee =
                new AddHourlyEmployeeTransaction(employeeId, "Billy", "Home", 15.25);
        addHourlyEmployee.execute(repository);

        Calendar date = new GregorianCalendar(2001,10,31);
        Transaction timeCardTransaction = new AddTimeCardTransaction(date, 8.0, employeeId);
        timeCardTransaction.execute(repository);

        Employee employee = repository.getEmployee(employeeId);
        assertThat(employee, is(notNullValue()));

        PaymentClassification paymentClassification = employee.getPaymentClassification();
        HourlyPaymentClassification hourlyPaymentClassification = (HourlyPaymentClassification) paymentClassification;
        assertThat(hourlyPaymentClassification, is(notNullValue()));

        TimeCard timeCard =  hourlyPaymentClassification.getTimeCard(date);
        assertNotNull(timeCard);
        assertThat(timeCard.getHours() , is(8.0));
    }
}
