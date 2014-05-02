package com.culturapp;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.adaptadores.AdaptadorEvento;
import com.recursos.ConfiguracionGlobal;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ProgramacionEventos extends Activity implements OnItemClickListener {
	
	private JSONArray programacion;
	private JSONArray lstActual;
	private String categoria;
	private String fecha;
	private int diaActual;
	private ArrayList<ImageView> lstBtnsDias;
	private ArrayList<Integer> lstImagenes;
	private ArrayList<Integer> lstImagenesSelec;
	private ListView lstEventos;
	private Typeface fuenteTitulo;
	private Typeface fuenteParrafo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.programacion_eventos);
		
		this.fuenteParrafo = Typeface.createFromAsset(getAssets(),"HelveticaNeueLTStd-Lt.otf");
		this.fuenteTitulo = Typeface.createFromAsset(getAssets(),"HelveticaNeueLTStd-Md.otf");
		
		this.lstEventos = (ListView) findViewById(R.id.lstEventos);
		this.categoria = getIntent().getStringExtra("CATEGORIA");
		this.fecha = "2014-05-05";
		this.diaActual = 0;
		this.lstEventos.setOnItemClickListener(this);
		this.cargarBotones();
		this.cargarImagenes();
		this.cargarImagenesSeleccionadas();
		TextView lblMayo = (TextView) findViewById(R.id.lblMayo);
		lblMayo.setTypeface(fuenteTitulo);
		TextView lblTitulo = (TextView) findViewById(R.id.lblTipoCategoria);
		lblTitulo.setTypeface(fuenteTitulo);
		lblTitulo.setText(this.categoria);
		
		if (ConfiguracionGlobal.getSingletonObject().getProgramacion()!=null){
			this.programacion = ConfiguracionGlobal.getSingletonObject().getProgramacion();
		}
		
		this.mostrarEventos(this.filtrarEventos());
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
			this.mostrarEventos(this.filtrarEventos());
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
	
	private JSONArray filtrarEventos(){
		this.lstActual = new JSONArray();
		for(int i=0; i<this.programacion.length(); i++){
			try {
				JSONObject evento = this.programacion.getJSONObject(i);
				String [] fechaEvento = evento.getString("fecha").split(" ");
				if(fechaEvento[0].equals(this.fecha) &&
						evento.getString("categoria").toUpperCase().equals(this.categoria.toUpperCase())){
					this.lstActual.put(evento);
				}
			} catch (JSONException e) {}
		}
		return this.lstActual;
	}
	
	private void mostrarEventos(JSONArray listaEventos) {
		AdaptadorEvento adaptador = new AdaptadorEvento(this, listaEventos, this.fuenteTitulo, this.fuenteParrafo);
		this.lstEventos.setAdapter(adaptador);
	}
	
	public void atras(View v){
		this.finish();
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		try {
			ConfiguracionGlobal.getSingletonObject().setEvento(this.lstActual.getJSONObject(arg2));
			Intent intent = new Intent(this, Evento.class);
			startActivity(intent);
		} catch (JSONException e) {}
		
	}
}
