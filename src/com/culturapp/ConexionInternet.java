package com.culturapp;

import android.net.ConnectivityManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

public class ConexionInternet extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.conexion_internet);
	}

	public void validar(View v){
		if(this.hayInternet()){
			Intent intent = new Intent(this, com.culturapp.Menu.class);
			startActivity(intent);
			this.finish();
		}
		else{
			Toast.makeText(this, "Ninguna conexión establecida \n Por favor intenta de nuevo.", Toast.LENGTH_LONG).show();
		}
	}
	
	private boolean hayInternet() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		if (cm.getActiveNetworkInfo() != null
				&& cm.getActiveNetworkInfo().isAvailable()
				&& cm.getActiveNetworkInfo().isConnected()) {
			return true;
		} else {
			return false;
		}
	}
}
