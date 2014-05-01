package com.culturapp;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.recursos.ConfiguracionGlobal;

import android.net.ConnectivityManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class Mapa extends Activity {
	
	private String fecha;
	private int diaActual;
	private WebView mapa;
	private ArrayList<ImageView> lstBtnsDias;
	private ArrayList<Integer> lstImagenes;
	private ArrayList<Integer> lstImagenesSelec;
	private JSONArray programacion;
	private String MAP_URL;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mapa);
		
		this.cargarBotones();
		this.cargarImagenes();
		this.cargarImagenesSeleccionadas();
		this.fecha = "2014-05-05";
		this.diaActual = 0;
		
		if(ConfiguracionGlobal.getSingletonObject().getProgramacion() != null){
			this.programacion = ConfiguracionGlobal.getSingletonObject().getProgramacion();
		}
		
		this.mapa = (WebView) findViewById(R.id.wvMapa);
		this.mapa.getSettings().setJavaScriptEnabled(true);
		this.mapa.getSettings().setGeolocationEnabled(true);
		this.MAP_URL = "http://pruebaandroid.tk/ServerFestivalImagen/Vista/mapaAndroid.php";
		this.cargarMapa();
	}
	
	private void cargarMapa(){
		final ProgressBar progress = (ProgressBar) findViewById(R.id.progress);
		mapa.setWebViewClient(new WebViewClient() {
			@Override
			public void onPageFinished(WebView view, String url) {
				ubicarObras();
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
	
	public void atras(View v){
		this.finish();
	}
	
	public void cambiarDia(View v){
		int diaSeleccionado=this.diaActual;
		switch (v.getId()) {
		case R.id.btn1:
			this.fecha = "2014-05-05";
			diaSeleccionado = 0;
			break;
		case R.id.btn2:
			this.fecha = "2014-05-06";
			diaSeleccionado = 1;
			break;
		case R.id.btn3:
			this.fecha = "2014-05-07";
			diaSeleccionado = 2;
			break;
		case R.id.btn4:
			this.fecha = "2014-05-08";
			diaSeleccionado = 3;
			break;
		case R.id.btn5:
			this.fecha = "2014-05-09";
			diaSeleccionado = 4;
			break;
		case R.id.btn6:
			this.fecha = "2014-05-10";
			diaSeleccionado = 5;
			break;

		default:
			break;
		}
		if(diaSeleccionado != this.diaActual){
			this.lstBtnsDias.get(this.diaActual).setImageResource(this.lstImagenes.get(this.diaActual));
			this.lstBtnsDias.get(diaSeleccionado).setImageResource(this.lstImagenesSelec.get(diaSeleccionado));
			this.diaActual = diaSeleccionado;
			this.ubicarObras();
		}
	}
	
	private void cargarBotones(){
		this.lstBtnsDias = new ArrayList<ImageView>();
		this.lstBtnsDias.add((ImageView) findViewById(R.id.btn1));
		this.lstBtnsDias.add((ImageView) findViewById(R.id.btn2));
		this.lstBtnsDias.add((ImageView) findViewById(R.id.btn3));
		this.lstBtnsDias.add((ImageView) findViewById(R.id.btn4));
		this.lstBtnsDias.add((ImageView) findViewById(R.id.btn5));
		this.lstBtnsDias.add((ImageView) findViewById(R.id.btn6));
	}
	
	private void cargarImagenes(){
		this.lstImagenes = new ArrayList<Integer>();
		this.lstImagenes.add(R.drawable.prog_boton_dia_a);
		this.lstImagenes.add(R.drawable.prog_boton_diab);
		this.lstImagenes.add(R.drawable.prog_boton_diac);
		this.lstImagenes.add(R.drawable.prog_boton_diad);
		this.lstImagenes.add(R.drawable.prog_boton_diae);
		this.lstImagenes.add(R.drawable.prog_boton_diaf);
	}

	private void cargarImagenesSeleccionadas(){
		this.lstImagenesSelec = new ArrayList<Integer>();
		this.lstImagenesSelec.add(R.drawable.prog_select_boton_diaa);
		this.lstImagenesSelec.add(R.drawable.prog_select_boton_diab);
		this.lstImagenesSelec.add(R.drawable.prog_select_boton_diac);
		this.lstImagenesSelec.add(R.drawable.prog_select_boton_diad);
		this.lstImagenesSelec.add(R.drawable.prog_select_boton_diae);
		this.lstImagenesSelec.add(R.drawable.prog_select_boton_diaf);
	}
	
	/*
	 * UbicarEventos('5.056746,-75.48951, Universidad de Caldas Talleres <br/> 10:00 am <br/> $10000 ,
	 * talleres,5.056746,-75.48951,Talleres <br/> 14:30 pm <br/> $30000,talleres');
	 */
	private void ubicarObras(){
		String parametro = "";
		for (int i = 0; i < this.programacion.length(); i++) {
			JSONObject jObj;
			try {
				jObj = this.programacion.getJSONObject(i);
				String[] fechaEvento = jObj.getString("fecha").split(" ");
				if (fechaEvento[0].equals(this.fecha)) {
					String latitud = jObj.getString("latitud");
					String longitud = jObj.getString("longitud");
					String descripcion = jObj.getString("titulo");
					String hora;
					String [] horaMilitar = fechaEvento[1].split(":");
					if(Integer.parseInt(horaMilitar[0])>12){
						int horaNormal = Integer.parseInt(horaMilitar[0]) - 12;
						hora = String.valueOf(horaNormal) + ":" + horaMilitar[1] + " p.m";
					}
					else{
						hora = horaMilitar[0] + ":" + horaMilitar[1] + " a.m";
					}
					String costo = "$"+jObj.getString("costo");
					String categoria = jObj.getString("categoria");
					
					parametro = parametro + latitud + "," + longitud + "," + descripcion + "<br>" + hora + "<br>" +
							costo + "," + categoria + ",";
			}
			}catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (parametro.length() == 0) {
			Toast.makeText(getApplicationContext(),"No hay obras en " + " para esta fecha :(",Toast.LENGTH_SHORT).show();
		}
		else{
			String consulta = "'" + parametro + "'";;
			String centerURL = "javascript:UbicarEventos(" + consulta + ")";
			mapa.loadUrl(centerURL);
		}
	}
}
