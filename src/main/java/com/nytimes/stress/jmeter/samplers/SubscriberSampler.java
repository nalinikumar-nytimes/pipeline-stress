package com.nytimes.stress.jmeter.samplers;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

import com.google.api.services.pubsub.model.PullRequest;
import com.google.api.services.pubsub.model.PullResponse;
import com.google.api.services.pubsub.model.ReceivedMessage;
import com.nytimes.stress.utils.PubSubUtils;

public class SubscriberSampler extends AbstractJavaSamplerClient implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PullRequest pullRequest;

	private String subscriptionName;

	private int batchSize;

	public SubscriberSampler() {

		this.batchSize = 1;

	}

	public static void main(String[] args) throws FileNotFoundException {

		BufferedOutputStream out = new BufferedOutputStream(
				new FileOutputStream(""));

		// "projects/gcp-data-poc-1208/subscriptions/pullsub1"

	}

	public SampleResult runTest(JavaSamplerContext arg0) {
		return PubSubUtils.samplePullMessage();
	}

}
