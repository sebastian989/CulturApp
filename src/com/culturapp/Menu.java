package com.culturapp;

import org.json.JSONArray;
import org.json.JSONObject;
import com.google.android.gcm.GCMRegistrar;
import com.recursos.ConfiguracionGlobal;
import com.recursos.WebService;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;

public class Menu extends Activity {

	public WebService ws;
	public ProgressDialog pd2;
	public String imei;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);
		ws = new WebService();
		pd2 = new ProgressDialog(this);
		pd2.setCancelable(false);
		new Login().execute("");
		
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
	
	public class Login extends AsyncTask<String, Void, Void> {

		Boolean exito;
		
		@Override
		protected Void doInBackground(String... params) {

			final String regId = GCMRegistrar.getRegistrationId(getApplicationContext());
			if (regId.equals("")) {
				GCMRegistrar.register(getApplicationContext(), "141302713907");
				Log.v("GCM", "Registrado");
				ConfiguracionGlobal.getSingletonObject().setPushId(GCMRegistrar.getRegistrationId(getApplicationContext()));
				SystemClock.sleep(2000);
				ConfiguracionGlobal.getSingletonObject().setPushId(GCMRegistrar.getRegistrationId(getApplicationContext()));
			} else {
				ConfiguracionGlobal.getSingletonObject().setPushId(regId);
				Log.v("GCM", "Ya registrado");
			}
			TelephonyManager mngr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
			imei = mngr.getDeviceId();
			String[] parametros = {"getEventosAndSavePushId",imei,ConfiguracionGlobal.getSingletonObject().getPushId()};
			JSONArray respuesta = ws.conectar(parametros);
			try {
				JSONObject res = respuesta.getJSONObject(0);
				exito=res.getBoolean("respuesta");
			} catch (Exception e) {
				exito=true;
				ConfiguracionGlobal.getSingletonObject().setProgramacion(respuesta);
			}

			return null;
		}

		@Override
		protected void onPreExecute() {
			pd2.setMessage("Descargando Programacion");
			pd2.setTitle("Espere");
			pd2.show();
			super.onPreExecute();
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			pd2.dismiss();
			if(exito){
				Toast.makeText(getApplicationContext(),ConfiguracionGlobal.getSingletonObject().getProgramacion().toString(),Toast.LENGTH_LONG).show();

			}
			else if(!exito){
				Toast.makeText(getApplicationContext(),"No hay nada programado",Toast.LENGTH_LONG).show();
			}
		}
	}
	
}
