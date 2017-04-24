package payrollcasestudy.entities.affiliations;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import payrollcasestudy.entities.ServiceCharge;

public class UnionAffiliation {

	public static final UnionAffiliation NO_AFFILIATION = null;
	 private int memberId;
	 private double weeklyUnionDues;
	 private ServiceCharge serviceCharge;
	 
	public UnionAffiliation(int memberId, double weeklyUnionDues) {
		this.memberId=memberId;
		this.weeklyUnionDues=weeklyUnionDues;
		
	}


	public ServiceCharge getServiceCharge(Calendar date) {
		if (serviceCharge.getDate()==date)
			return serviceCharge;
		else
			return null;
	}

	public Double getDues() {
		return weeklyUnionDues;
	}


	public void addServiceCharge(ServiceCharge serviceCharge2) {
		serviceCharge= serviceCharge2;
	}

}
