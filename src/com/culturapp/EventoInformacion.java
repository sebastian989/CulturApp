package com.culturapp;

import org.json.JSONObject;

import com.recursos.ConfiguracionGlobal;

import android.os.Bundle;
import android.app.Activity;

public class EventoInformacion extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.evento_informacion);
		
		JSONObject evento;
		
		if(ConfiguracionGlobal.getSingletonObject().getEvento() != null){
			evento = ConfiguracionGlobal.getSingletonObject().getEvento();
			
		}
	}

}
