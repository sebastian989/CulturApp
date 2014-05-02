package com.culturapp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

public class ProgramacionCategorias extends Activity{
	
	private Typeface fuenteTitulo;
	private Typeface fuenteParrafo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.programacion_categorias);	
		
		this.fuenteParrafo = Typeface.createFromAsset(getAssets(),"HelveticaNeueLTStd-Lt.otf");
		this.fuenteTitulo = Typeface.createFromAsset(getAssets(),"HelveticaNeueLTStd-Md.otf");
		
		TextView bar = (TextView) findViewById(R.id.lblBar);
		TextView lbla = (TextView) findViewById(R.id.lbla);
		TextView lblb = (TextView) findViewById(R.id.lblb);
		TextView lblc = (TextView) findViewById(R.id.lblc);
		TextView lbld = (TextView) findViewById(R.id.lbld);
		TextView lble = (TextView) findViewById(R.id.lble);
		TextView lblf = (TextView) findViewById(R.id.lblf);
		TextView lblg = (TextView) findViewById(R.id.lblg);
		TextView lblh = (TextView) findViewById(R.id.lblh);
		
		bar.setTypeface(fuenteParrafo);
		lbla.setTypeface(fuenteParrafo);
		lblb.setTypeface(fuenteParrafo);
		lblc.setTypeface(fuenteParrafo);
		lbld.setTypeface(fuenteParrafo);
		lble.setTypeface(fuenteParrafo);
		lblf.setTypeface(fuenteParrafo);
		lblg.setTypeface(fuenteParrafo);
		lblh.setTypeface(fuenteParrafo);
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
