package com.nytimes.stress.jmeter.samplers;

import java.io.Serializable;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;


public class PipelineStressSampler2  extends AbstractJavaSamplerClient implements Serializable {
	
	    private static final long serialVersionUID = 1L;
	    
	    private static Log m_log = LogFactory.getLog(PipelineStressSampler2.class);
	    
	    private static String msg = "Thread Group configurations will be the same which you have already mentioned. no. of threads will be the client load which you want to put(suppose 10 or 100), duration : 1 hr, loop : forever, rampup : 10 yes when you rampup i.e. in first minute generally you get more requests as threads are starting so it is normal behavior. To test your scenario run for exactly 1hr with given throughput you should test for 1hr 10min. exclude first 5 min and last 5 min of test as that is warm up time for your test as well as server and last 5 min are warm down period.Thread Group configurations will be the same which you have already mentioned. no. of threads will be the client load which you want to put(suppose 10 or 100), duration : 1 hr, loop : forever, rampup : 10 yes when you rampup i.e. in first minute generally you get more requests as threads are starting so it is normal behavior. To test your scenario run for exactly 1hr with given throughput you should test for 1hr 10min. exclude first 5 min and last 5 min of test as that is warm up time for your test as well as server and last 5 min are warm down period.Thread Group configurations will be the same which you have already mentioned. no. of threads will be the client load which you want to put(suppose 10 or 100), duration : 1 hr, loop : forever, rampup : 10 yes when you rampup i.e. in first minute generally you get more requests as threads are starting so it is normal behavior. To test your scenario run for exactly 1hr with given throughput you should test for 1hr 10min. exclude first 5 min and last 5 min of test as that is warm up time for your test as well as server and last 5 min are warm down period";
	     
	    @Override
	    public Arguments getDefaultParameters() {
	        Arguments defaultParameters = new Arguments();
	        defaultParameters.addArgument("id","${id}");
	        defaultParameters.addArgument("bucket", "${bucket}");
	        defaultParameters.addArgument("path", "${path}");
	        return defaultParameters;

	    }
	  
		public SampleResult runTest(JavaSamplerContext context) {
			    long start = System.currentTimeMillis();
			    
			    //result.setTimeStamp(System.currentTimeMillis());
			    
			    /*
			    try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
			    
			    int i=0;
			    
			    Random random = new Random();
			    int nextInt = random.nextInt(500);
			    
			    
			    //SampleResult resutl1 = new Sample
			    
			    SampleResult result = new SampleResult();
			    
			    
			    m_log.info(System.currentTimeMillis()-start);
			    
			    
			    //result.setLatency(System.currentTimeMillis()-start);
		        
			    //result.setEndTime(System.currentTimeMillis());
			    
			    result.setStampAndTime(start, System.currentTimeMillis()+20);
			    
			    
		        result.setSuccessful(true);
		      
		        //result.setTimeStamp(Date.);
		        
		        //m_log.info(user+".."+param1+"..."+param2);
		        
		        result.setResponseMessage("test");
		        
		        result.setResponseData("test");
		        
		 
		        return result;
		}
	    
		

		

	    
}
