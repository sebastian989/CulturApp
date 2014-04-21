package com.recursos;

import org.json.JSONArray;
import org.json.JSONObject;

public class ConfiguracionGlobal {

	private static ConfiguracionGlobal singletonObject;
	private JSONArray programacion;
	private String pushId;
	private JSONObject evento;


	/**
	 * 
	 * @return Object GlobalConiguration 
	 */
	public static synchronized ConfiguracionGlobal getSingletonObject() {
		if (singletonObject == null) {
			singletonObject = new ConfiguracionGlobal();
		}
		return singletonObject;
	}

	public JSONArray getProgramacion() {
		return programacion;
	}

	public void setProgramacion(JSONArray programacion) {
		this.programacion = programacion;
	}

	public JSONObject getEvento() {
		return evento;
	}

	public void setEvento(JSONObject evento) {
		this.evento = evento;
	}	
	
	public String getPushId() {
		return pushId;
	}

	public void setPushId(String p) {
		this.pushId = p;
	}	
	
}
