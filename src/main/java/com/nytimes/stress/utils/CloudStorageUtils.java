package com.nytimes.stress.utils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.nio.channels.Channels;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.google.gcloud.storage.Blob;
import com.google.gcloud.storage.Bucket;
import com.google.gcloud.storage.Storage.BlobListOption;
import com.google.gcloud.storage.StorageOptions;
import com.nytimes.stress.pubsub.Processor;
import com.nytimes.stress.pubsub.PubsubProcessor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Nalini Kumar
 * 
 * Utils to read/write the data to Google cloud storage.
 *
 */
public class CloudStorageUtils {
	
	static com.google.gcloud.storage.Storage storage = StorageOptions.defaultInstance().service();
	
        private static Log m_log = LogFactory.getLog(PubsubProcessor.class);
	
	public static void main(String[] args) throws IOException, GeneralSecurityException {

		 List<Blob> objects = listObjectsOfBucket("gcp-data-poc-1208-s3-xfer","json/event-tracker/datums/page/2016/01/11/part");
		 
		 Blob blob = objects.get(0);
		
		 readBlob(blob,5);
	}


	private static List<Blob> listObjectsOfBucket( String bucketName, String prefix) {
		 Bucket bucket = storage.get(bucketName);
		 Iterator<Blob> valuesItr = bucket.list(BlobListOption.prefix(prefix)).iterateAll();
		 ArrayList<Blob> list = new ArrayList<Blob>();
		 while (valuesItr.hasNext()) {
			Blob next = valuesItr.next();
			list.add(next);
			System.out.println(next);
		 }
		 return list;
		
	}
	
	private static void readBlob(Blob blob,int limit) throws IOException {
		
		  System.out.println("reading...blob .."+blob);
		
	      if (blob == null) {
	        System.out.println("No such object");
	        return;
	      }
	     
	        Scanner scanner = new Scanner(new BufferedInputStream(Channels.newInputStream(blob.reader())));
	        
	        int i =0;
	        
	        while(scanner.hasNext()){
	        	String nextLine = scanner.nextLine();
	        	System.out.println(nextLine);
	        	if(i++ == limit){
	        		break;
	        	}
	        }
	        
	        scanner.close();
   }  
	
	
	
	
	/**
	 * @param bucket file bucket
	 * @param prefix file name prefix
	 * @param processor processor to be used for the batch of records
	 * @param batchSize number of records to be batched
	 * @param testMode true 
	 * @throws IOException
	 */
	public static void processPath(String bucket,String prefix,Processor processor,int batchSize,boolean testMode) throws IOException {
		
		 List<Blob> objects = listObjectsOfBucket(bucket,prefix);
		
		  System.out.println("blobs found .."+objects.size());
		  
		  ArrayList<String> lines = new ArrayList<String>();
		
	      if (objects == null) {
	        System.out.println("No such Path");
	        return;
	      }
	     
	      
	      for(Blob blob: objects){
	    	  
                    m_log.info("processor "+processor.getProcessorId()+"..processing blob.."+blob.name()); 
	    	  
		        Scanner scanner = new Scanner(Channels.newInputStream(blob.reader()));
		        
		        
		        while(scanner.hasNext()){
		        	String nextLine = scanner.nextLine();
		        	lines.add(nextLine);
		        	
		        	if(lines.size() ==  batchSize){
		        		processor.process(lines);
		        		lines = new ArrayList<String>();
		        		//In test mode I would like to break right after the first batch is processed.
		        		if(testMode){
		        			break;
		        		}
		        	}
		        }
		        
		        scanner.close();
	    	  
	      }
	      
 }
	
	
	/**
	 * @param bucket file bucket
	 * @param prefix file name prefix
	 * @param processor processor to be used for the batch of records
	 * @param batchSize number of records to be batched
	 * @param testMode true 
	 * @throws IOException
	 */
	public static void processEvents(String msg,Processor processor,int batchSize) throws IOException {
		
	      List<String> lines = Collections.nCopies(batchSize, msg);     
		        		processor.process(lines);
	}
	
	

	
	/**
	 * @param bucket file bucket
	 * @param prefix file name prefix
	 * @param processor processor to be used for the batch of records
	 * @param batchSize number of records to be batched
	 * @param testMode true 
	 * @throws IOException
	 */
	public static void processPathMock(String bucket,String prefix,Processor processor,int batchSize,boolean testMode) throws IOException {
		
		 List<Blob> objects = listObjectsOfBucket(bucket,prefix);
		
		  System.out.println("blobs found .."+objects.size());
		  
		  ArrayList<String> lines = new ArrayList<String>();
		
	      if (objects == null) {
	        System.out.println("No such Path");
	        return;
	      }
	     
	      
	            Blob blob = objects.get(0);
	    	  
                 m_log.info("processor "+processor.getProcessorId()+"..processing blob.."+blob.name()); 
	    	  
		        Scanner scanner = new Scanner(Channels.newInputStream(blob.reader()));
		        String nextLine = scanner.nextLine();
		        scanner.close();
			      
		        
		        while(true){
		        	lines.add(nextLine);
		        	if(lines.size() ==  batchSize){
		        		processor.process(lines);
		        		lines = new ArrayList<String>();
		        		//In test mode I would like to break right after the first batch is processed.
		        		if(testMode){
		        			break;
		        		}
		        	}
		        }
		        
		    
 }  	
	/*
	private static void listObjectsOfBucket(Bucket bucket,String prefix) {
		 Iterator<Blob> valuesItr = bucket.list(BlobListOption.recursive(false).fields(BlobField.ID).prefix(prefix)).iterateAll();
		 while (valuesItr.hasNext()) {
		   System.out.println(valuesItr.next());
		 }
	}

	private static void listBuckets(com.google.gcloud.storage.Storage storage) {
		Iterator<Bucket> bucketIterator = storage.list().iterateAll();
		 while (bucketIterator.hasNext()) {
		   System.out.println(bucketIterator.next());
		 }
	}*/

}
