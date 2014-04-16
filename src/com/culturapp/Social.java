package com.culturapp;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.app.Activity;
import android.content.Intent;

public class Social extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.social);
	}
	
	public void facebook(View view){
		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/groups/59040913850/"));
		startActivity(intent);
	}
	
	public void twitter(View view){
		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/imagenfest"));
		startActivity(intent);
	}
	
	public void instagram(View view){
		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://instagram.com/imagenfest"));
		startActivity(intent);
	}
	
	public void atras(View v){
		this.finish();
	}
}
