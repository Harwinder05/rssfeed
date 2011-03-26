package com.yahoo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

public class Home extends Activity {
	Button Start;
	String url;
	Constants constants;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.ui);
		
		Start=(Button)findViewById(R.id.startbtn);
		Start.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				
				Intent intent=new Intent(Home.this,Yahoo.class);
				Bundle b=new Bundle();
				b.putString("feedurl", "http://rss.news.yahoo.com/rss/topstories");
				intent.putExtras(b);
				startActivity(intent);
			}
		});
	}
	

}
