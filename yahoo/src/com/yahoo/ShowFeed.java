package com.yahoo;



import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ShowFeed extends BaseAdapter{
	String[] title,date,link, imgurl;
	StringBuilder sb;
	LayoutInflater mLayoutInflater;
	ViewHolder holder;
	ShowFeed(Context context,String[] title,String[] date,String[] image_url ){
		mLayoutInflater=LayoutInflater.from(context);
		this.title=title;
		this.date=date;
		this.imgurl=image_url;
	}
	
	public int getCount() {
		// TODO Auto-generated method stub
		return this.title.length;
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		if(convertView==null){
			holder = new ViewHolder();
			convertView=mLayoutInflater.inflate(R.layout.list, null);
			holder.title=(TextView)convertView.findViewById(R.id.list_rssfeed);
			holder.date=(TextView)convertView.findViewById(R.id.date);
			holder.image=(ImageView)convertView.findViewById(R.id.ImgView); 
			convertView.setTag(holder);
		}else {
			 holder = (ViewHolder) convertView.getTag();
		 }
		sb=new StringBuilder();
		holder.title.setText(title[position]);
	    holder.date.setText(date[position]);
	    holder.imageurl=this.imgurl[position];
	    for(int i=0;i<holder.imageurl.length();i++){
	    	if(holder.imageurl.charAt(i)!='?'){
	    		sb.append(holder.imageurl.charAt(i));
	    	}
	    	else{
	    		break;
	    	}
	    }
	    getImage(sb.toString());
		return convertView;
	}
	
	static class ViewHolder{
		TextView title;
		TextView date;
		String imageurl;
		ImageView image;
	}
	Bitmap bmImg;
	   void getImage(String fileUrl){
	         URL myFileUrl =null;          
	         try {
	              myFileUrl= new URL(fileUrl);
	         } catch (MalformedURLException e) {
	              // TODO Auto-generated catch block
	              e.printStackTrace();
	         }
	         try {
	              HttpURLConnection conn= (HttpURLConnection)myFileUrl.openConnection();
	              conn.setDoInput(true);
	              conn.connect();
	              int length = conn.getContentLength();
	              InputStream is = conn.getInputStream();
	              
	              bmImg = BitmapFactory.decodeStream(is);
	              holder.image.setImageBitmap(bmImg);
	              
	         } catch (IOException e) {
	              // TODO Auto-generated catch block
	              e.printStackTrace();
	         }
	    }
	

}
