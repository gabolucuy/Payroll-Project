package Gson;

import java.awt.Window.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import payrollcasestudy.transactions.add.AddTimeCardTransaction;

public class FromGson {
	public ArrayList<AddTimeCardTransaction> FromGson(String json){
		
		Gson gson = new Gson();
		java.lang.reflect.Type tipoListaTransacciones = new TypeToken<ArrayList<AddTimeCardTransaction>>(){}.getType();
		ArrayList<AddTimeCardTransaction> listaTransacciones = gson.fromJson(json, tipoListaTransacciones);
		
		return listaTransacciones;
	}
}
