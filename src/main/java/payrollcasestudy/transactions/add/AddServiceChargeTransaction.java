package payrollcasestudy.transactions.add;

import java.util.Calendar;

import payrollcasestudy.transactions.Transaction;

public class AddServiceChargeTransaction implements Transaction {

	 private int memberId;
	 private Calendar payDate;
	 private double serviceCharge;

	public AddServiceChargeTransaction(int memberId, Calendar payDate, double serviceCharge) {
		this.memberId=memberId;
		this.payDate=payDate;
		this.serviceCharge=serviceCharge;
	}

	public void execute() {
		// TODO Auto-generated method stub

	}

}
