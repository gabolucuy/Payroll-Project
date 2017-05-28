package Gson;
import java.util.ArrayList;

import com.google.gson.Gson;
import payrollcasestudy.entities.PayCheck;

public class ToGson {
	public String ToGson(ArrayList<PayCheck> paycheck){
		Gson gson = new Gson();
//		for(PayCheck paychecks : paycheck.values()){
//			
//	}
		String json = gson.toJson(paycheck);
		return json;
	}
}
