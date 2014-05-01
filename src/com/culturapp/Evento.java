package com.culturapp;

import org.json.JSONException;
import org.json.JSONObject;

import com.recursos.ConfiguracionGlobal;

import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TabHost.OnTabChangeListener;

public class Evento extends TabActivity implements OnTabChangeListener, OnClickListener {

	TabHost tabHost;
	private LocationManager lm;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.evento);
		
		TextView lblTitulo = (TextView) findViewById(R.id.lblTituloEvento);
		this.lm = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
		if(ConfiguracionGlobal.getSingletonObject().getEvento()!=null){
			JSONObject evento = ConfiguracionGlobal.getSingletonObject().getEvento();
			try {
				lblTitulo.setText(evento.getString("titulo"));
			} catch (JSONException e) {}
		}
		
		tabHost = getTabHost();
        TabHost.TabSpec spec;
        Intent intent;
        Resources res = getResources();
        
        //Tab 1
        intent = new Intent().setClass(this, EventoInformacion.class);
        spec = tabHost.newTabSpec("pestanaa").setIndicator("Evento", res.getDrawable(R.drawable.pestana1)).setContent(intent);
        tabHost.addTab(spec);
        //tabHost.getTabWidget().setBackgroundColor(Color.BLACK);
        
        //Tab2
        intent = new Intent().setClass(this, EventoRuta.class);
        spec = tabHost.newTabSpec("pestanab").setIndicator("Ruta", res.getDrawable(R.drawable.pestana2)).setContent(intent);
        tabHost.addTab(spec);
        
        tabHost.getTabWidget().getChildAt(0).setBackgroundResource(R.drawable.barra_superior2);
		tabHost.getTabWidget().getChildAt(1).setBackgroundResource(R.drawable.barra_superior2);
        
		 this.tabHost.setOnTabChangedListener(this);
		 this.tabHost.getTabWidget().getChildAt(1).setOnClickListener(this);
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
	
	public void atras(View v){
		this.finish();
	}
	
	@Override
	public void onTabChanged(String tabId) {
		// TODO Auto-generated method stub
		if (tabId.equals("pestanab")) {
			if (this.hayInternet()) {
				if (!lm.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER)
						&& !lm.isProviderEnabled(android.location.LocationManager.NETWORK_PROVIDER)) {
					Toast.makeText(this,
							"Necesitas activar un proveedor de ubicación",
							Toast.LENGTH_LONG).show();
				}
			}
			else{
				Toast.makeText(this,"Necesitas una conexión a internet para calcular la rura.",Toast.LENGTH_LONG).show();
			}
		}
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
			if (this.hayInternet()) {
				tabHost.setCurrentTab(1);
			}
			else{
				Toast.makeText(this,"Necesitas una conexión a internet para calcular la rura.",Toast.LENGTH_LONG).show();
			}
	}
}
