package com.nytimes.xml;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.nytimes.stress.utils.StressUtils;

public class Test {
	
	public static void main(String[] args) throws IOException {
		
		
		BufferedOutputStream tempFile = StressUtils.getTempFile("test");
		
		
		tempFile.write("test".getBytes());
		
		tempFile.flush();
		
		tempFile.close();
		
		
		System.out.println(System.getProperty("java.io.tmpdir"));
		
		
		/*
		
		FileOutputStream fout= new FileOutputStream(".");
		
		
		File f = new File(".");
        System.out.println(f.getAbsolutePath());*/
        
	}

}
