package com.culturapp;

import android.os.Bundle;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;

public class Evento extends TabActivity implements OnTabChangeListener {

	TabHost tabHost;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.evento);
		
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
		tabHost.getTabWidget().getChildAt(1).setBackgroundResource(R.drawable.barra_superior);
        
        this.tabHost.setOnTabChangedListener(this);
	}
	
	public void atras(View v){
		this.finish();
	}

	@Override
	public void onTabChanged(String tabId) {
		// TODO Auto-generated method stub
		if(tabId.equals("pestanaa")){
			tabHost.getTabWidget().getChildAt(0).setBackgroundResource(R.drawable.barra_superior2);
			tabHost.getTabWidget().getChildAt(1).setBackgroundResource(R.drawable.barra_superior);
		}
		else{
			tabHost.getTabWidget().getChildAt(1).setBackgroundResource(R.drawable.barra_superior2);
			tabHost.getTabWidget().getChildAt(0).setBackgroundResource(R.drawable.barra_superior);
		}
	}
}
