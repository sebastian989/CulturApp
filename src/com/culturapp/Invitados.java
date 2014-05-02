package com.culturapp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

public class Invitados extends Activity {
	
	private Typeface fuenteTitulo;
	private Typeface fuenteParrafo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.invitados);
		
		this.fuenteParrafo = Typeface.createFromAsset(getAssets(),"HelveticaNeueLTStd-Lt.otf");
		this.fuenteTitulo = Typeface.createFromAsset(getAssets(),"HelveticaNeueLTStd-Md.otf");
		
		TextView lblBar = (TextView) findViewById(R.id.lblBar);
		TextView lblAcerca = (TextView) findViewById(R.id.lblAcerca);
		
		lblBar.setTypeface(fuenteTitulo);
		lblAcerca.setTypeface(fuenteParrafo);
	}
	
	public void invitados(View v){
		Intent intent = new Intent(this, Invitados.class);
		startActivity(intent);
	}
	
	public void atras(View v){
		this.finish();
	}
}
