package com.culturapp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;

public class Invitados extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.invitados);
	}
	
	public void invitados(View v){
		Intent intent = new Intent(this, Invitados.class);
		startActivity(intent);
	}
	
	public void atras(View v){
		this.finish();
	}
}
