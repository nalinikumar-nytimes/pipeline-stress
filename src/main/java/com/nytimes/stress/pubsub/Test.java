package com.nytimes.stress.pubsub;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.storage.Storage;
import com.google.api.services.storage.StorageScopes;
import com.google.api.services.storage.model.Bucket;
import com.google.api.services.storage.model.StorageObject;

public class Test {

	private static final String APPLICATION_NAME = "test";

	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

	private static Storage storageService;

	public static void main(String[] args) throws IOException, GeneralSecurityException {

		getService();
		System.out.println(storageService);

		
		printObjects(listBucket("gcp-data-poc-1208-s3-xfer"));
		
		
		
		

	}

	private static void printObjects(List<StorageObject> listBucket) {
		for(StorageObject obj : listBucket){
			System.out.println(obj.toString());
		}
		
	}

	public static Bucket getBucket(String bucketName) throws IOException, GeneralSecurityException {
		Storage client = getService();

		Storage.Buckets.Get bucketRequest = client.buckets().get(bucketName);
		bucketRequest.setProjection("full");
		return bucketRequest.execute();
	}

	

	public static List<StorageObject> listBucket(String bucketName) throws IOException, GeneralSecurityException {
		Storage client = getService();
		Storage.Objects.List listRequest = client.objects().list(bucketName);

		List<StorageObject> results = new ArrayList<StorageObject>();
		com.google.api.services.storage.model.Objects objects;

		do {
			objects = listRequest.execute();
			results.addAll(objects.getItems());

			listRequest.setPageToken(objects.getNextPageToken());
		} while (null != objects.getNextPageToken());

		return results;
	}

	private static Storage getService() throws IOException, GeneralSecurityException {
		if (null == storageService) {
			GoogleCredential credential = GoogleCredential.getApplicationDefault();
			if (credential.createScopedRequired()) {
				credential = credential.createScoped(StorageScopes.all());
			}
			HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
			storageService = new Storage.Builder(httpTransport, JSON_FACTORY, credential)
					.setApplicationName(APPLICATION_NAME).build();
		}
		return storageService;
	}

}
