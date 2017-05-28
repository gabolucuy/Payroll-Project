package Gson;
import java.util.ArrayList;

import com.google.gson.Gson;

import payrollcasestudy.entities.Employee;
import payrollcasestudy.entities.PayCheck;

public class ToGson {
	public String ToGson(ArrayList<PayCheck> paychecks){
		String jsonList="";
		Gson gson = new Gson();
		for(PayCheck paycheck : paychecks){
		jsonList = jsonList + gson.toJson(paycheck);
		}
		
		return jsonList;
	}
}
