package payrollcasestudy.transactions.add;

import java.util.Calendar;

import payrollcasestudy.boundaries.PayrollDatabase;
import payrollcasestudy.entities.Employee;
import payrollcasestudy.transactions.Transaction;
import payrollcasestudy.entities.ServiceCharge;

public class AddServiceChargeTransaction implements Transaction {

	 private int memberId;
	 private Calendar payDate;
	 private ServiceCharge serviceCharge;
	 
	public AddServiceChargeTransaction(int memberId, Calendar payDate, double serviceCharge) {
		this.memberId=memberId;
		this.serviceCharge=new ServiceCharge(payDate,serviceCharge);
	}

	public void execute() {
		Employee employee = PayrollDatabase.globalPayrollDatabase.getUnionMember(memberId);
		employee.addServiceChargeTransaccion(payDate, serviceCharge);
		
	}

}
