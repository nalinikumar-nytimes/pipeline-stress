package com.nytimes.stress.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;

public class StressUtils {
	
	
	  String ip ;
	  
	  static HashMap<String,BufferedOutputStream> fileMap = new HashMap<String,BufferedOutputStream>();
	  
	  
	  StressUtils(){
	    try {
		   ip = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
	
	
	  public static void main(String[] args) {
		        try {
		            InetAddress ipAddr = InetAddress.getLocalHost();
		           
		        } catch (UnknownHostException e){
		        	
		        }
	  }
	  
	
	  
	public static BufferedOutputStream getTempFile(String name){
		
		if(fileMap.get(name)==null){
			 String tmpFile = System.getProperty("java.io.tmpdir")+File.separator+name;
			 try {
				return new BufferedOutputStream(new FileOutputStream(tmpFile));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
			 
		}
		return fileMap.get(name);
		
	}
	  
	
	
	
	public  String generateRowID(){
		return ip+":"+DateUtils.getCurrentTimestamp();
	}
	
	

}
