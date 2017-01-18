package com.nytimes.stress.jmeter.samplers;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

import com.google.bigtable.repackaged.io.netty.handler.ssl.OpenSsl;
import com.nytimes.stress.utils.BigTableUtils;
import com.nytimes.stress.utils.PubSubUtils;

public class BTReadSampler extends AbstractJavaSamplerClient implements
		Serializable {

	private static final long serialVersionUID = 1L;

	private static Log m_log = LogFactory.getLog(BTReadSampler.class);
	
	 @Override
	    public Arguments getDefaultParameters() {
	        Arguments defaultParameters = new Arguments();
	        defaultParameters.addArgument("rowid","${rowid}");
	        return defaultParameters;

	    }
	  

	public SampleResult runTest(JavaSamplerContext context) {
		
		  
	    String id = context.getParameter("rowid");
	    
		return BigTableUtils.get(id);
	
	}
	
	
	public static void main(String[] args) {
		SampleResult sampleResult = BigTableUtils.get("10.240.0.29:2016-03-20T20:07:33.521Z");
		
		
	}

}
