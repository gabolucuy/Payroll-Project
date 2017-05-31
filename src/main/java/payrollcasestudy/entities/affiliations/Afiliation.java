package payrollcasestudy.entities.affiliations;

import static payrollcasestudy.entities.paymentclassifications.PaymentClassification.isInPayPeriod;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import payrollcasestudy.entities.PayCheck;
import payrollcasestudy.entities.ServiceCharge;

public interface  Afiliation {
	public ServiceCharge getServiceCharge(Calendar date);
	public void addServiceCharge(Calendar date, double amount);
	public int getNumberId();
	public Double getDues();
	public double calculateDeduction(PayCheck payCheck);
	public double numberOfFridaysInPayPeriod(Calendar payPeriodStart, Calendar payPeriodEnd);

}
