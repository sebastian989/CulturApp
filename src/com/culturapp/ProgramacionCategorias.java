package com.culturapp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;

public class ProgramacionCategorias extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.programacion_categorias);	
	}
	
	public void atras(View v){
		this.finish();
	}
	
	public void mostrarEventos(View v){
		String categoria="";
		Intent intent = new Intent(this, ProgramacionEventos.class);
		switch (v.getId()) {
		case R.id.seminarioint:
			categoria = "Seminario internacional";
			break;
		
		case R.id.paisajes:
			categoria = "Paisajes sonoros";
			break;
			
		case R.id.eventosesp:
			categoria = "Eventos especiales";
			break;
			
		case R.id.talleres:
			categoria = "Talleres";
			break;
			
		case R.id.exposiciones:
			categoria = "Exposiciones";
			break;
			
		case R.id.cine:
			categoria = "Cine y Digital";
			break;
		
		case R.id.academico:
			categoria = "Foro academico";
			break;
			
		case R.id.mercado:
			categoria = "Mercado";
			break;
		default:
			break;
		}
		intent.putExtra("CATEGORIA", categoria);
		startActivity(intent);
	}
}
