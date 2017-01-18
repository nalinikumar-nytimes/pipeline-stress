package com.nytimes.xml;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SampleResultHandler extends DefaultHandler {
	
	
	BufferedOutputStream out = null;
	private String fileName;
	
	StringBuffer content = new StringBuffer("");
	
	
	SampleResultHandler(String file){
		this.fileName = file;
	}
	
	@Override
	public void startDocument() throws SAXException {
		try {
			out = new BufferedOutputStream(new FileOutputStream(fileName));
			try {
				out.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>".getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void endDocument() throws SAXException {
		try {
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		
		content.append("\n");
		
		if(qName.equals("testResults")){
			content.append("<testResults version=\"1.2\">");
		}
		
		
		if(qName.equals("sample")){
			
			content.append("<sample ");
			int i=0;
			String lt="0";
			while(i < attributes.getLength()){
				
				if(attributes.getQName(i).equals("t")){
					//content.append(" t=\""+lt+"\" ");
				}else{
					if(attributes.getQName(i).equals("lt")){
						lt = attributes.getValue(i);
					}	
					content.append(" " + attributes.getQName(i)+"=\""+attributes.getValue(i)+"\"");
				}
				i++;
			}
			
			content.append(" t=\""+lt+"\" ");
		}
		
	
	}
	
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {

		
		if(qName.equals("testResults")){
			content.append("</testResults>");
		}
		
		if(qName.equals("sample")){
			content.append(" />");
		}
		
		
		try {
			
			out.write(content.toString().getBytes());
			
			content = new StringBuffer();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}
	

}
