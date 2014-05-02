package com.culturapp;

import org.json.JSONException;
import org.json.JSONObject;

import com.recursos.ConfiguracionGlobal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.app.Activity;
import android.graphics.Typeface;

public class EventoInformacion extends Activity {
	
	private Typeface fuenteTitulo;
	private Typeface fuenteParrafo;
	private Typeface fuenteEspecial;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.evento_informacion);
		
		this.fuenteParrafo = Typeface.createFromAsset(getAssets(),"HelveticaNeueLTStd-Lt.otf");
		this.fuenteTitulo = Typeface.createFromAsset(getAssets(),"HelveticaNeueLTStd-Md.otf");
		this.fuenteEspecial = Typeface.createFromAsset(getAssets(),"HelveticaNeueLTStd-UltLt.otf");
		
		if(ConfiguracionGlobal.getSingletonObject().getEvento() != null){
			JSONObject evento = ConfiguracionGlobal.getSingletonObject().getEvento();
			this.mostrarEvento(evento);
		}
	}

	public void mostrarEvento(JSONObject evento){
		TextView lblHora = (TextView) findViewById(R.id.lblHora);
		lblHora.setTypeface(fuenteEspecial);
		TextView lblFecha = (TextView) findViewById(R.id.lblFecha);
		lblFecha.setTypeface(fuenteParrafo);
		TextView lblDescripcion = (TextView) findViewById(R.id.lblDescripcion);
		lblDescripcion.setTypeface(fuenteParrafo);
		try {
			String titulo = evento.getString("titulo");
			String fecha = this.obtenerFecha(evento.getString("fecha"));
			String hora = this.obtenerHora(evento.getString("fecha"));
			String descripcion = evento.getString("descripcion");
			lblHora.setText(hora);
			lblFecha.setText(fecha);
			lblDescripcion.setText(descripcion);
		} catch (JSONException e) {}
	}
	
	public String obtenerFecha(String fecha){
		String [] arrayFecha = fecha.split(" ");
		String [] ddmmyy = arrayFecha[0].split("-");
		String formatoFecha = ddmmyy[2]+"/"+ddmmyy[1]+"/"+ddmmyy[0];
		return formatoFecha;
	}
	
	public String obtenerHora(String fecha){
		String [] arrayFecha = fecha.split(" ");
		String [] horaMilitar = arrayFecha[1].split(":");
		String hora="";
		if(Integer.parseInt(horaMilitar[0])>12){
			int horaNormal = Integer.parseInt(horaMilitar[0]) - 12;
			hora = String.valueOf(horaNormal) + ":" + horaMilitar[1] + " p.m";
		}
		else{
			hora = horaMilitar[0] + ":" + horaMilitar[1] + " a.m";
		}
		return hora;
	}
}
