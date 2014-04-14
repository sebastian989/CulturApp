package com.culturapp;

import android.os.Bundle;
import android.view.View;
import android.app.Activity;
import android.content.Intent;

public class Menu extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);
	}
	
	public void invitados(View v){
		Intent intent = new Intent(this, Invitados.class);
		startActivity(intent);
	}
	
	public void noticias(View v){
		Intent intent = new Intent(this, Noticias.class);
		startActivity(intent);
	}
	
	public void social(View v){
		Intent intent = new Intent(this, Social.class);
		startActivity(intent);
	}
}
