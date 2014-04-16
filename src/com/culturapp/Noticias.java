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
import android.view.View;
import android.widget.ListView;

public class Noticias extends Activity {
	
	private WebService ws;
	private ProgressDialog dialog;
	private ListView lstNoticias;
	JSONArray lista = new JSONArray();//Stub

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.noticias);
		
		this.ws = new WebService();
		this.dialog = new ProgressDialog(Noticias.this);
		this.lstNoticias = (ListView) findViewById(R.id.lstNoticias);
		
		//Stub
		JSONObject noticia = new JSONObject();
		try {
			noticia.put("titulo", "Noticia1");
			noticia.put("fecha", "23-05-07");
			noticia.put("descripcion", "Desde el lanzamiento, en 1957, del primer satélite artificial " +
					"al espacio –una especie de bola de metal con antenas llamado Sputnik 1, de autoría soviética-, " +
					"han llegado a la órbita terrestre más de 20.000 de estos objetos, un promedio de 70 por año en " +
					"la última década.Era plena Guerra Fría. Estados Unidos y la Unión Soviética, potencias que tenían " +
					"dividido el planeta por sus posiciones ideológicas y políticas, llevaron su confrontación al " +
					"dominio del espacio, logrando los soviéticos su primera victoria en este campo el 4 de octubre de " +
					"1957. Su satélite se convirtió así en el primer objeto en el espacio construido por humanos.");
			lista.put(noticia);
			//-----------------
			noticia = new JSONObject();
			noticia.put("titulo", "Noticia2");
			noticia.put("fecha", "23-04-09");
			noticia.put("descripcion", "Desde el lanzamiento, en 1957, del primer satélite artificial " +
					"al espacio –una especie de bola de metal con antenas llamado Sputnik 1, de autoría soviética-, " +
					"han llegado a la órbita terrestre más de 20.000 de estos objetos, un promedio de 70 por año en " +
					"la última década.Era plena Guerra Fría. Estados Unidos y la Unión Soviética, potencias que tenían " +
					"dividido el planeta por sus posiciones ideológicas y políticas, llevaron su confrontación al " +
					"dominio del espacio, logrando los soviéticos su primera victoria en este campo el 4 de octubre de " +
					"1957. Su satélite se convirtió así en el primer objeto en el espacio construido por humanos.");
			lista.put(noticia);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Fin stub
		
		new Sincronizar().execute();
	}
	
	private void mostrarNoticias(JSONArray noticias){
		Adaptador adaptador = new Adaptador(this, lista);
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
			String[] parametros = {"url,http://citytaxiapp.com/emprendimiento/emprendimiento/Controller/facade_blog.php?method=mostrar_aportes"};
			respuesta = ws.conectar(parametros);
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			
			mostrarNoticias(respuesta);
		}
	}

}
