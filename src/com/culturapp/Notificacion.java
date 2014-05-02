package com.culturapp;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class Notificacion extends Activity {
	
	private Typeface fuenteTitulo;
	private Typeface fuenteParrafo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notificacion);
		
		this.fuenteParrafo = Typeface.createFromAsset(getAssets(),"HelveticaNeueLTStd-Lt.otf");
		this.fuenteTitulo = Typeface.createFromAsset(getAssets(),"HelveticaNeueLTStd-Md.otf");
		
		Bundle b = getIntent().getBundleExtra("extra");
		String titulo = b.getString("titles");
		String descripcion = b.getString("message");
		
		TextView lblBar = (TextView) findViewById(R.id.lblBar);
		lblBar.setTypeface(fuenteTitulo);
		TextView lblTitulo = (TextView) findViewById(R.id.lblTitulo);
		lblTitulo.setTypeface(fuenteTitulo);
		TextView lblDesc = (TextView) findViewById(R.id.lblDesc);
		lblDesc.setTypeface(fuenteParrafo);
		
		lblTitulo.setText(titulo);
		lblDesc.setText(descripcion);
	}

	public void atras(View v){
		this.finish();
	}
}
