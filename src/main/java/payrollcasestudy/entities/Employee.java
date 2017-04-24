package payrollcasestudy.entities;

import payrollcasestudy.entities.paymentclassifications.PaymentClassification;
import payrollcasestudy.entities.paymentmethods.PaymentMethod;
import payrollcasestudy.entities.paymentschedule.PaymentSchedule;
import payrollcasestudy.entities.affiliations.UnionAffiliation;

import java.util.Calendar;

public class Employee {
    private PaymentClassification paymentClassification;
    private PaymentSchedule paymentSchedule;
    private PaymentMethod paymentMethod;
    private UnionAffiliation unionAffiliation = UnionAffiliation.NO_AFFILIATION; 
    private int employeeId;
    private String name;
    private String address;

    public Employee(int employeeId, String name, String address) {
        this.employeeId = employeeId;
        this.name = name;
        this.address = address;
    }

    public PaymentClassification getPaymentClassification() {
        return paymentClassification;
    }

    public void setPaymentClassification(PaymentClassification paymentClassification) {
        this.paymentClassification = paymentClassification;
    }

    public void setPaymentSchedule(PaymentSchedule paymentSchedule) {
        this.paymentSchedule = paymentSchedule;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getName() {
        return name;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public PaymentSchedule getPaymentSchedule() {
        return paymentSchedule;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isPayDate(Calendar payDate) {
        return paymentSchedule.isPayDate(payDate);
    }

    public Calendar getPayPeriodStartDay(Calendar payDate) {
        return paymentSchedule.getPayPeriodStartDate(payDate);
    }

    public void payDay(PayCheck payCheck) {
    	double discount=0;
    	if (unionAffiliation!=null)
    		discount = unionAffiliation.getDues();
        double grossPay = paymentClassification.calculatePay(payCheck);
    	double netPay = grossPay- discount;
        payCheck.setGrossPay(grossPay);
        payCheck.setNetPay(netPay);
        paymentMethod.pay(payCheck);
    }

	public UnionAffiliation getUnionAffiliation() {
		return unionAffiliation;
	}

	public void setUnionAffiliation(UnionAffiliation unionAffiliation2) {
		
		unionAffiliation=unionAffiliation2;
	}

	public void addServiceChargeTransaccion(Calendar payDate, ServiceCharge serviceCharge) {
		unionAffiliation.addServiceCharge(serviceCharge);
	}


}
