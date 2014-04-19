package com.recursos;

import org.json.JSONArray;

public class ConfiguracionGlobal {

	private static ConfiguracionGlobal singletonObject;
	private JSONArray programacion;

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
}
