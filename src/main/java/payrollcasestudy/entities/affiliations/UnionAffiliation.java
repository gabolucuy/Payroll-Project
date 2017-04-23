package payrollcasestudy.entities.affiliations;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import payrollcasestudy.boundaries.PayrollDatabase;
import payrollcasestudy.entities.Employee;
import payrollcasestudy.entities.ServiceCharge;

public class UnionAffiliation {

	public static final UnionAffiliation NO_AFFILIATION = null;
	 private int memberId;
	 private double weeklyUnionDues;
	 private Map<Calendar, ServiceCharge> serviceCharge = new HashMap<Calendar, ServiceCharge>();
	 
	public UnionAffiliation(int memberId, double weeklyUnionDues) {
		this.memberId=memberId;
		this.weeklyUnionDues=weeklyUnionDues;
		
	}


	public ServiceCharge getServiceCharge(Calendar date) {
		return serviceCharge.get(date);
	}

	public Double getDues() {
		return weeklyUnionDues;
	}


	public void addServiceCharge(Calendar payDate, ServiceCharge serviceCharge2) {
		serviceCharge.put(payDate, serviceCharge2);
	}

}
