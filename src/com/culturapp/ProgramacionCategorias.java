package com.culturapp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

public class ProgramacionCategorias extends Activity implements OnClickListener{

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
			categoria = "seminario";
			break;
		
		case R.id.paisajes:
			categoria = "paisajes";
			break;
			
		case R.id.eventosesp:
			categoria = "evento";
			break;
			
		case R.id.talleres:
			categoria = "talleres";
			break;
			
		case R.id.exposiciones:
			categoria = "exposiciones";
			break;
			
		case R.id.cine:
			categoria = "cine";
			break;
		
		case R.id.academico:
			categoria = "academico";
			break;
			
		case R.id.mercado:
			categoria = "mercado";
			break;
		default:
			break;
		}
		intent.putExtra("CATEGORIA", categoria);
		startActivity(intent);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

}
