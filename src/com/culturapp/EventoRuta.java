package com.culturapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.net.ConnectivityManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class EventoRuta extends Activity {
	
	private WebView mapa;
	private JSONObject evento;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.evento_ruta);
		
		this.mapa = (WebView) findViewById(R.id.wvMapa);
		this.mapa.getSettings().setJavaScriptEnabled(true);
		this.mapa.getSettings().setGeolocationEnabled(true);
		String MAP_URL = "http://pruebaandroid.tk/ServerFestivalImagen/Vista/mapaAndroid.php";
		final ProgressBar progress = (ProgressBar) findViewById(R.id.progress);
		mapa.setWebViewClient(new WebViewClient() {
			@Override
			public void onPageFinished(WebView view, String url) {
				ruta();
				progress.setVisibility(View.INVISIBLE);
			}

		});
		
		if (this.hayInternet()) {
			this.mapa.loadUrl(MAP_URL);
		}
		
		else{
			String mapaNoDisponible =     "<html><head></head><body>" +
		            "<b>Necesitas una conexión a internet para ver la ubicación.</b>" +
		            "</body></html>";
		            mapa.loadDataWithBaseURL("file:///android_asset/", mapaNoDisponible, "text/html", "utf-8", null);
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

	private void ruta() {
		/*
		 * {'valores':[{'descripcion':'Aqui estoy yo','latitud':5.056420,'longitud':-75.493080,'categoria':'talleres'},
		 * {'descripcion':'Taller de transmedia <br> 10:00 am','latitud':5.065547,'longitud':-75.510922,
		 * 'categoria':'talleres'}]};
		 */
		JSONObject paqueteDatos = new JSONObject();
		JSONArray jSonConsulta = new JSONArray();
		JSONObject elemento = new JSONObject();
		try {
			elemento.put("descripcion", "Apolo");
			elemento.put("latitud", "5.069838");
			elemento.put("longitud", "-75.516974");
			jSonConsulta.put(elemento);
			paqueteDatos.put("puntos", jSonConsulta);
			String consulta = "'" + paqueteDatos.toString() + "'";
			String centerURL = "javascript:ubicarPuntos(" + consulta + ")";
			this.mapa.loadUrl(centerURL);
		} catch (JSONException e1) {
		}
	}
}
