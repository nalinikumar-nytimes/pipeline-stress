package com.nytimes.stress.jmeter.samplers;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

import com.google.bigtable.repackaged.io.netty.handler.ssl.OpenSsl;
import com.nytimes.stress.utils.BigTableUtils;
import com.nytimes.stress.utils.PubSubUtils;

public class BTWriteSampler extends AbstractJavaSamplerClient implements
		Serializable {

	private static final long serialVersionUID = 1L;

	private static Log m_log = LogFactory.getLog(BTWriteSampler.class);


	public SampleResult runTest(JavaSamplerContext arg0) {
		
		 SampleResult sample = BigTableUtils.insert();
		 
		 //sample.getSampleLabel()
		 
		 return sample;
	}
	
	
	public static void main(String[] args) {
		 //SampleResult insert = BigTableUtils.insert();
		 
		System.out.println(OpenSsl.isAlpnSupported());
		
		System.out.println(OpenSsl.unavailabilityCause());
		
	}

}
