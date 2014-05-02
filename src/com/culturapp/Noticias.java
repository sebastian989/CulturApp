package com.culturapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.adaptadores.Adaptador;
import com.recursos.WebService;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class Noticias extends Activity implements OnItemClickListener {
	
	private WebService ws;
	private ProgressDialog dialog;
	private ListView lstNoticias;
	private JSONArray listaNoticias = new JSONArray();
	private Typeface fuenteTitulo;
	private Typeface fuenteParrafo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.noticias);
		
		this.fuenteParrafo = Typeface.createFromAsset(getAssets(),"HelveticaNeueLTStd-Lt.otf");
		this.fuenteTitulo = Typeface.createFromAsset(getAssets(),"HelveticaNeueLTStd-Md.otf");
		
		this.ws = new WebService();
		this.dialog = new ProgressDialog(Noticias.this);
		this.lstNoticias = (ListView) findViewById(R.id.lstNoticias);
		this.lstNoticias.setOnItemClickListener(this);
		TextView bar = (TextView) findViewById(R.id.lblBar);
		bar.setTypeface(fuenteTitulo);
		new Sincronizar().execute();
	}
	
	private void mostrarNoticias(JSONArray noticias){
		this.listaNoticias = noticias;
		Adaptador adaptador = new Adaptador(this, noticias, this.fuenteTitulo, this.fuenteParrafo);
		this.lstNoticias.setAdapter(adaptador);
		this.dialog.dismiss();
	}
	
	public void atras(View v){
		this.finish();
	}
	
	private class Sincronizar extends AsyncTask<Void, Void, Void> {

		private JSONArray respuesta;
		
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
			String[] parametros = {"getNoticias"};
			respuesta = ws.conectar(parametros);
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			
			mostrarNoticias(respuesta);
		}
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		Intent intent = new Intent(this, Noticia.class);
		try {
			JSONObject noticia = this.listaNoticias.getJSONObject(arg2);
			intent.putExtra("TITULO", noticia.getString("titulo"));
			intent.putExtra("FECHA", noticia.getString("fecha"));
			intent.putExtra("DESCRIPCION", noticia.getString("descripcion"));
		} catch (JSONException e) {}
		startActivity(intent);
	}

}
