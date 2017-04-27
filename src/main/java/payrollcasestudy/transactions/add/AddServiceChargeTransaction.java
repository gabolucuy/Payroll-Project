package payrollcasestudy.transactions.add;

import java.util.Calendar;

import payrollcasestudy.boundaries.PayrollDatabase;
import payrollcasestudy.entities.Employee;
import payrollcasestudy.transactions.Transaction;
import payrollcasestudy.entities.ServiceCharge;

public class AddServiceChargeTransaction implements Transaction {

	 private int memberId;
	 private Calendar payDate;
	 private double amount;
	 
	public AddServiceChargeTransaction(int memberId, Calendar payDate, double amount) {
		this.memberId=memberId;
		this.payDate=payDate;
		this.amount=amount;
	}

	public void execute() {
		Employee employee = PayrollDatabase.globalPayrollDatabase.getUnionMember(memberId);
		if(employee != null)
			employee.getUnionAffiliation().addServiceCharge(payDate, amount);
		
	}

}
