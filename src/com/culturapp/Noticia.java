package com.culturapp;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

public class Noticia extends Activity {
	
	private TextView lblTitulo;
	private TextView lblFecha;
	private TextView lblDescripcion;
	private Typeface fuenteTitulo;
	private Typeface fuenteParrafo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.noticia);
		
		this.fuenteParrafo = Typeface.createFromAsset(getAssets(),"HelveticaNeueLTStd-Lt.otf");
		this.fuenteTitulo = Typeface.createFromAsset(getAssets(),"HelveticaNeueLTStd-Md.otf");
		
		this.lblTitulo = (TextView) findViewById(R.id.lblTitulo);
		this.lblFecha = (TextView) findViewById(R.id.lblFecha);
		this.lblDescripcion = (TextView) findViewById(R.id.lblDescripcion);
		TextView bar = (TextView) findViewById(R.id.lblBar);
		
		this.lblTitulo.setTypeface(fuenteTitulo);
		this.lblFecha.setTypeface(fuenteParrafo);
		this.lblDescripcion.setTypeface(fuenteParrafo);
		bar.setTypeface(fuenteTitulo);
		
		String titulo = getIntent().getStringExtra("TITULO");
		String fecha = getIntent().getStringExtra("FECHA");
		String descripcion = getIntent().getStringExtra("DESCRIPCION");
		
		this.lblTitulo.setText(titulo.toUpperCase());
		this.lblFecha.setText(fecha);
		this.lblDescripcion.setText(descripcion);
	}
	
	public void atras(View v){
		this.finish();
	}

}
