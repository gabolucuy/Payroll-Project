package payrollcasestudy.entities.affiliations;

import java.util.Calendar;

import payrollcasestudy.entities.ServiceCharge;

public class UnionAffiliation {

	public static final UnionAffiliation NO_AFFILIATION = null;
	 private int memberId;
	 private double weeklyUnionDues;
	
	public UnionAffiliation(int memberId, double weeklyUnionDues) {
		this.memberId=memberId;
		this.weeklyUnionDues=weeklyUnionDues;
	}


	public ServiceCharge getServiceCharge(Calendar date) {
		
		return null;
	}

	public Double getDues() {
		return weeklyUnionDues;
	}

}
