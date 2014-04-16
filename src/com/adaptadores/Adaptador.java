package com.adaptadores;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.culturapp.R;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class Adaptador extends BaseAdapter {

    private final Activity actividad;
    private final JSONArray lista;

    public Adaptador(Activity actividad, JSONArray lista) {
    	  super();
          this.actividad = actividad;
          this.lista = lista;
    }
/**
 * @return: vista que contiene la lista renderizada con imagenes a 
 * los extremos de cada item.
 */
    public View getView (int position, View convertView,ViewGroup parent) {
          LayoutInflater inflater = actividad.getLayoutInflater();
          View view = inflater.inflate(R.layout.item_noticia, null,true);
          
          TextView lblTitulo = (TextView)view.findViewById(R.id.lblTitulo);
          TextView lblFecha = (TextView)view.findViewById(R.id.lblFecha);
          TextView lblDescripcion = (TextView)view.findViewById(R.id.lblDescripcion);
          
          JSONObject obj;
		try {
			obj = this.lista.getJSONObject(position);

			lblTitulo.setText(obj.getString("titulo"));
			lblFecha.setText(obj.getString("fecha"));
			lblDescripcion.setText(obj.getString("descripcion"));
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
          
        return view;
    }

    public int getCount() {
          return lista.length();
    }

    public Object getItem (int arg0) {
          try {
			return lista.getJSONObject(arg0);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
          return null;
    }

    public long getItemId(int position) {
          return position;
    }
    
    
}
