package com.culturapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.recursos.ConfiguracionGlobal;
import com.recursos.WebService;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;

public class Menu extends Activity {
	
	private WebService ws;
	private ProgressDialog dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);
		
		this.ws = new WebService();
		this.dialog = new ProgressDialog(Menu.this);
		new Sincronizar().execute();
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
	
	public void programacion(View v){
		Intent intent = new Intent(this, ProgramacionCategorias.class);
		startActivity(intent);
	}
	
	private class Sincronizar extends AsyncTask<Void, Void, Void> {

		private JSONArray respuesta;
		JSONArray stubArray;//---STUB---
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog.setTitle("Sincronizando");
			dialog.setMessage("Espere un momento...");
			dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			dialog.setCancelable(false);
			dialog.show();
		}

		@Override
		protected Void doInBackground(Void... arg0) {
			
			//---STUB---
			this.stubArray = new JSONArray();
			JSONObject evento = new JSONObject();
			try {
				evento.put("titulo", "evento1");
				evento.put("fecha", "2014-05-05 18:06");
				evento.put("descripcion", "Este es un evento de prueva en el cual se evaluara el funcionamiento y el comportamiento de" +
						"la aplicacion movil CulturApp.");
				evento.put("categoria", "talleres");
				evento.put("lugar", "Carrera 23 # 30 - 17 Fundadores");
				evento.put("latitud", "4.34343");
				evento.put("longitud", "-57.34343");
				evento.put("costo", "0");
				stubArray.put(evento);
				
				evento = new JSONObject();
				evento.put("titulo", "evento2");
				evento.put("fecha", "2014-05-07 18:06");
				evento.put("descripcion", "Este es un evento de prueva en el cual se evaluara el funcionamiento y el comportamiento de" +
						"la aplicacion movil CulturApp.");
				evento.put("categoria", "exposiciones");
				evento.put("lugar", "Carrera 23 # 30 - 17 U de Caldas");
				evento.put("latitud", "4.34343");
				evento.put("longitud", "-57.34343");
				evento.put("costo", "0");
				stubArray.put(evento);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//----------
			
			String[] parametros = {"url,http://citytaxiapp.com/emprendimiento/emprendimiento/Controller/facade_blog.php?method=mostrar_aportes"};
			respuesta = ws.conectar(parametros);
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			
			ConfiguracionGlobal.getSingletonObject().setProgramacion(stubArray);
			dialog.dismiss();
		}
	}
}
