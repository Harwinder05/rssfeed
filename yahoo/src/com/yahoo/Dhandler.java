package com.yahoo;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Log;

public class Dhandler extends DefaultHandler{
	Item item;
	
	ArrayList<Item> itemList;
	StringBuilder stringbuilder;
	String imgUrl;
	boolean imagestatus=false;
	int e=0;
	
	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.startDocument();
		itemList=new ArrayList<Item>();
		stringbuilder=new StringBuilder();
		
	}
	
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		super.startElement(uri, localName, qName, attributes);
		if(qName.equalsIgnoreCase("item")){
			item=new Item();
		}
		else if(qName.equalsIgnoreCase("channel")){
			
		}
		else if(qName.equalsIgnoreCase("media:content")){
			if(attributes.getValue("url") != null){
				imagestatus=true;
				imgUrl=attributes.getValue("url");
			
			}
		}
		
	}
	
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
		stringbuilder.append(ch, start, length);
	}
	
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// TODO Auto-generated method stub
		super.endElement(uri, localName, qName);
		
		if(qName.equalsIgnoreCase("item")){
			if(imagestatus){
				Log.d("Image Url :",imgUrl);
				imagestatus=false;
			}
			else{
				imgUrl="http://www.cliniminds.com/presentation/App_Themes/default/images/no_image_thumb.gif";
				item.imgUrl=imgUrl;
				Log.d("Image Url :",imgUrl);
			}
			additem(item);
			item=null;
		}
		if(qName.equalsIgnoreCase("image")){
			imagestatus=false;
		}
		if(qName.equalsIgnoreCase("title") && item!=null){
			item.title=stringbuilder.toString().trim();
		}
		if(qName.equalsIgnoreCase("link") &&  item!=null ){
			
			item.link=stringbuilder.toString().trim();
			
			}
		if(qName.equalsIgnoreCase("pubDate") && item!=null){
			item.pubDate=stringbuilder.toString().trim();
		}
		if(qName.equalsIgnoreCase("description") && item!=null){
			item.description=stringbuilder.toString().trim();
		}
		if(qName.equalsIgnoreCase("media:content") && item!=null){
			item.imgUrl=imgUrl;
			e=e+1;
			
		}
		stringbuilder.setLength(0);
	}
	
	public void additem(Item item){
		itemList.add(item);
	}
}
