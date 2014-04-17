package com.culturapp;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.TextView;

public class Noticia extends Activity {
	
	private TextView lblTitulo;
	private TextView lblFecha;
	private TextView lblDescripcion;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.noticia);
		
		this.lblTitulo = (TextView) findViewById(R.id.lblTitulo);
		this.lblFecha = (TextView) findViewById(R.id.lblFecha);
		this.lblDescripcion = (TextView) findViewById(R.id.lblDescripcion);
		
		String titulo = getIntent().getStringExtra("TITULO");
		String fecha = getIntent().getStringExtra("FECHA");
		String descripcion = getIntent().getStringExtra("DESCRIPCION");
		
		this.lblTitulo.setText(titulo);
		this.lblFecha.setText(fecha);
		this.lblDescripcion.setText(descripcion);
	}
	
	public void atras(View v){
		this.finish();
	}

}
