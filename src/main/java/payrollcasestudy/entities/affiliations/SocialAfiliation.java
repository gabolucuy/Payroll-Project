package payrollcasestudy.entities.affiliations;

import static payrollcasestudy.entities.paymentclassifications.PaymentClassification.isInPayPeriod;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import payrollcasestudy.entities.PayCheck;
import payrollcasestudy.entities.ServiceCharge;

public class SocialAfiliation implements Afiliation {
	private int numberId;
	private double amount = 0.12;
	private Map<Calendar, ServiceCharge> serviceCharges = new HashMap<Calendar, ServiceCharge>();
	public static final SocialAfiliation NO_AFFILIATION = new SocialAfiliation(0);
	public SocialAfiliation(int numberId){
		this.numberId = numberId;
	}
	@Override
	public ServiceCharge getServiceCharge(Calendar date) {
		return serviceCharges.get(date);
	}

	@Override
	public void addServiceCharge(Calendar date, double amount) {
		this.serviceCharges.put(date, new ServiceCharge(date, amount));

	}

	@Override
	public int getNumberId() {
		return numberId;
	}

	@Override
	public Double getDues() {
		return amount;
	}

	@Override
	public double calculateDeduction(PayCheck payCheck) {
		 double totalDeductions = 0;
	        for (ServiceCharge serviceCharge : serviceCharges.values()){
	            if (isInPayPeriod(serviceCharge.getDate(), payCheck)){
	                totalDeductions += serviceCharge.getAmount();
	            }
	        }
	        return totalDeductions;
	}
	

	

}
