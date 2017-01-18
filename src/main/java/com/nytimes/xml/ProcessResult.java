package com.nytimes.xml;

import java.io.FileInputStream;
import java.io.InputStream;

import javax.xml.parsers.SAXParserFactory;

public class ProcessResult {
	
	
	public static void main(String[] args) {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {

		    InputStream    xmlInput  = new FileInputStream("C:\\Users\\nalini\\workspace\\hdp\\allresults.jtl");
		    javax.xml.parsers.SAXParser      saxParser = factory.newSAXParser();

		    
		    saxParser.parse(xmlInput, new SampleResultHandler("C:\\Users\\nalini\\workspace\\hdp\\out.jtl"));

		} catch (Throwable err) {
		    err.printStackTrace ();
		}
	}

}
