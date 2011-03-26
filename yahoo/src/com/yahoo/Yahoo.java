package com.yahoo;

import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;



import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class Yahoo extends Activity {
    /** Called when the activity is first created. */
	Constants constants;
	Dhandler handler=new Dhandler();
	ListView rssList;
	String feedurl;
	Button topstories,india,sports,technology;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        Bundle b=getIntent().getExtras();
        feedurl=b.getString("feedurl");
        
        
        try{
        URL url=new URL(feedurl);
        SAXParserFactory saxparserF=SAXParserFactory.newInstance();
        SAXParser saxparser=saxparserF.newSAXParser();
        XMLReader xr=saxparser.getXMLReader();
        xr.setContentHandler(handler);
        xr.parse(new InputSource(url.openStream()));
        }catch(Exception e){
        	
        }
        String[] title=new String[handler.itemList.size()];
        String[] date=new String[handler.itemList.size()];
        String[] link=new String[handler.itemList.size()];
        String[] image_url=new String[handler.itemList.size()];
        if(handler.itemList.size()>0){
        	for(int i=0;i<handler.itemList.size();i++){
        		//Log.d("URL:",handler.itemList.get(i).imgUrl);
        		title[i]=handler.itemList.get(i).title;
        		date[i]=handler.itemList.get(i).pubDate;
        		link[i]=handler.itemList.get(i).link;
        		image_url[i]=handler.itemList.get(i).imgUrl;
        	}
        	
        	
        }
        rssList=(ListView)findViewById(R.id.RssList);
       // rssList.setAdapter(new ArrayAdapter<String>(this, R.layout.list, R.id.list_rssfeed, title));
        rssList.setAdapter(new ShowFeed(this,title,date,image_url));
        rssList.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> adapter, View view, int position,
					long id) {
				//url=handler.itemList.get(position).link;
				//Toast.makeText(Yahoo.this,url, Toast.LENGTH_LONG).show();
				Intent webview=new Intent(Intent.ACTION_VIEW,Uri.parse(handler.itemList.get(position).link));
				startActivity(webview);
			}
		});
        topstories=(Button)findViewById(R.id.topStories);
        topstories.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent intent=new Intent(Yahoo.this,Yahoo.class);
				Bundle b=new Bundle();
				b.putString("feedurl", "http://rss.news.yahoo.com/rss/topstories");
				intent.putExtras(b);
				startActivity(intent);
			}
		});
        india=(Button)findViewById(R.id.india);
        india.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent intent=new Intent(Yahoo.this,Yahoo.class);
				Bundle b=new Bundle();
				b.putString("feedurl","http://rss.news.yahoo.com/rss/india");
				intent.putExtras(b);
				startActivity(intent);
			}
		});
        sports=(Button)findViewById(R.id.sports);
        sports.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent intent=new Intent(Yahoo.this,Yahoo.class);
				Bundle b=new Bundle();
				b.putString("feedurl", "http://rss.news.yahoo.com/rss/sports");
				intent.putExtras(b);
				startActivity(intent);
				
			}
		});
        
	technology=(Button)findViewById(R.id.technology);
    technology.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent intent=new Intent(Yahoo.this,Yahoo.class);
				Bundle b=new Bundle();
				b.putString("feedurl", "http://rss.news.yahoo.com/rss/tech");
				intent.putExtras(b);
				startActivity(intent);
				
			}
		});
     }
	

}