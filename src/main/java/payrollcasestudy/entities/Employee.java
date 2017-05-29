package payrollcasestudy.entities;

import payrollcasestudy.entities.affiliations.UnionAffiliation;
import payrollcasestudy.entities.paymentclassifications.CommissionedPaymentClassification;
import payrollcasestudy.entities.paymentclassifications.HourlyPaymentClassification;
import payrollcasestudy.entities.paymentclassifications.PaymentClassification;
import payrollcasestudy.entities.paymentclassifications.SalariedClassification;
import payrollcasestudy.entities.paymentmethods.PaymentMethod;
import payrollcasestudy.entities.paymentschedule.PaymentSchedule;

import java.util.Calendar;

public class Employee {
    private PaymentClassification paymentClassification;
    private PaymentSchedule paymentSchedule;
    private PaymentMethod paymentMethod;
    private int employeeId;
    private String name;
    private String address;
    private double hoursOfWork;
    private UnionAffiliation unionAffiliation = UnionAffiliation.NO_AFFILIATION;

    public Employee(int employeeId, String name, String address) {
        this.employeeId = employeeId;
        this.name = name;
        this.address = address;
        unionAffiliation = UnionAffiliation.NO_AFFILIATION;
        
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
    
    public void setHoursOfWork(double hours){
    	this.hoursOfWork=hours;
    }
    
   
    public int getEmployeeId(){
    	return employeeId;
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
        double grossPay = paymentClassification.calculatePay(payCheck);
        double deductions = unionAffiliation.calculateDeduction(payCheck);
        double netPay = grossPay - deductions;
        payCheck.setGrossPay(grossPay);
        payCheck.setNetPay(netPay);
        payCheck.setDeductions(deductions);
        System.out.println("********************************************");
        System.out.println(grossPay);
        paymentMethod.pay(payCheck);
    }

	public UnionAffiliation getUnionAffiliation() {
		// TODO Auto-generated method stub
		return unionAffiliation;
	}

	public void setUnionAffiliation(UnionAffiliation unionAffiliation) {
		// TODO Auto-generated method stub
		this.unionAffiliation = unionAffiliation;
	}
	 public double getHoursOfWork(){
	    	return hoursOfWork;
	    }
	
	 public String typeOfEmployee(){
		 if(paymentClassification instanceof  CommissionedPaymentClassification){
			 return "commissioned";
			}
		if(paymentClassification instanceof  HourlyPaymentClassification){
			return "hourly";
		}
		if(paymentClassification instanceof  SalariedClassification){
			return "salaried";
		}
		 return null;
	 }


}
