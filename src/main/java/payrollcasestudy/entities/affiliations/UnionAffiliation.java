package payrollcasestudy.entities.affiliations;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.hamcrest.Matcher;

import payrollcasestudy.entities.PayCheck;
import payrollcasestudy.entities.ServiceCharge;
import static payrollcasestudy.entities.paymentclassifications.PaymentClassification.isInPayPeriod;

public class UnionAffiliation {
	private int numberId;
	private double amount;
	private Map<Calendar, ServiceCharge> serviceCharges = new HashMap<Calendar, ServiceCharge>();
	public static final UnionAffiliation NO_AFFILIATION = new UnionAffiliation(0,0);
	
	public UnionAffiliation(int numberId, double amount){
		this.numberId = numberId;
		this.amount = amount;
	}

	public ServiceCharge getServiceCharge(Calendar date) {
		  return serviceCharges.get(date);
	}

	public void addServiceCharge(Calendar date, double amount) {
		this.serviceCharges.put(date, new ServiceCharge(date, amount));
	}
	
	public int getNumberId()
	{
		return numberId;
	}

	public Double getDues() {
		return amount;
	}
	
	public double calculateDeduction(PayCheck payCheck) {
        double totalDeductions = 0;
        totalDeductions += numberOfFridaysInPayPeriod(payCheck.getPayPeriodStart(), payCheck.getPayPeriodEnd()) * amount;
        for (ServiceCharge serviceCharge : serviceCharges.values()){
            if (isInPayPeriod(serviceCharge.getDate(), payCheck)){
                totalDeductions += serviceCharge.getAmount();
            }
        }
        return totalDeductions;
    }

	private double numberOfFridaysInPayPeriod(Calendar payPeriodStart, Calendar payPeriodEnd) {
        int numberOfFridays = 0;
        while (!payPeriodStart.after(payPeriodEnd)){
            if (payPeriodStart.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY){
                numberOfFridays++;
            }
            payPeriodStart.add(Calendar.DAY_OF_MONTH, 1);
        }
        return numberOfFridays;
	}

	
	
	
}
