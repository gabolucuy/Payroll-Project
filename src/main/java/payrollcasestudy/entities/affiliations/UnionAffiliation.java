package payrollcasestudy.entities.affiliations;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import payrollcasestudy.entities.Employee;
import payrollcasestudy.entities.PayCheck;
import payrollcasestudy.entities.ServiceCharge;
import payrollcasestudy.entities.paymentclassifications.PaymentClassification;
//import payrollcasestudy.entities.paymentclassifications.PaymentClassification.isInPayPeriod;

public class UnionAffiliation {

	public static final UnionAffiliation NO_AFFILIATION = new UnionAffiliation(0,0);
	 private int memberId;
	 private double weeklyUnionDues;
	 private Map<Calendar, ServiceCharge> serviceCharges = new HashMap<Calendar, ServiceCharge>();
	 
	public UnionAffiliation(int memberId, double weeklyUnionDues) {
		this.memberId=memberId;
		this.weeklyUnionDues=weeklyUnionDues;
		
	}
	public ServiceCharge getServiceCharge(Calendar date) {
		return serviceCharges.get(date);
	}

	public Double getDues() {
		return weeklyUnionDues;
	}

	public void addServiceCharge(Calendar date,double amount) {
		this.serviceCharges.put(date,new ServiceCharge(date,amount));
	}
	 public int NumberOfWeeks(PayCheck paycheck){
		 int numberOfWeeks = 0;
	    	Calendar periodEnd = paycheck.getPayPeriodEnd();
	    	Calendar periodStart = paycheck.getPayPeriodStart();
	    	while(!periodStart.after(periodEnd)){
	    		if(periodStart.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY)
	    		{
	    			numberOfWeeks++;
	    		}
	    		periodStart.add(Calendar.DAY_OF_MONTH, 1);
	    	}
	    	return numberOfWeeks;
	  }
	 public double calculteDeductions(PayCheck paycheck){
		 double totalDeductions = 0;
		 totalDeductions += NumberOfWeeks(paycheck);
		 for(ServiceCharge serviceCharge:serviceCharges.values()){
			 if(PaymentClassification.isInPayPeriod(serviceCharge.getDate(),paycheck)){
				 totalDeductions+= serviceCharge.getAmount();
			 }
		 }
		 return totalDeductions;
	 }
	
	

}
