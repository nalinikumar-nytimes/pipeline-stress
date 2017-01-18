package com.nytimes.stress.jmeter.samplers;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

import com.google.api.services.pubsub.Pubsub;
import com.nytimes.stress.utils.PubSubUtils;


public class PublishSampler  extends AbstractJavaSamplerClient implements Serializable {
	
	    private static final long serialVersionUID = 1L;
	    
	    private static Log m_log = LogFactory.getLog(PublishSampler.class);
	    
	    private static String msg = "Thread Group configurations will be the same which you have already mentioned. no. of threads will be the client load which you want to put(suppose 10 or 100), duration : 1 hr, loop : forever, rampup : 10 yes when you rampup i.e. in first minute generally you get more requests as threads are starting so it is normal behavior. To test your scenario run for exactly 1hr with given throughput you should test for 1hr 10min. exclude first 5 min and last 5 min of test as that is warm up time for your test as well as server and last 5 min are warm down period.Thread Group configurations will be the same which you have already mentioned. no. of threads will be the client load which you want to put(suppose 10 or 100)";
	     
	    @Override
	    public Arguments getDefaultParameters() {
	        Arguments defaultParameters = new Arguments();
	        defaultParameters.addArgument("id","${id}");
	        defaultParameters.addArgument("bucket", "${bucket}");
	        defaultParameters.addArgument("path", "${path}");
	        return defaultParameters;

	    }
	  
		public SampleResult runTest(JavaSamplerContext context) {
			    
			    String id = context.getParameter("id");
			    String bucket = context.getParameter("bucket");
			    String path = context.getParameter("path");
			    
			    SampleResult result = new SampleResult();
		        
		        
		        Pubsub pubsub;
				long start = System.currentTimeMillis();
				    
				    result.setTimeStamp(System.currentTimeMillis());
				     
				    
				//pubsub = PubSubUtils.getPubsubClient();
				//CloudStorageUtils.processPath(bucket, path, new PubsubProcessor(id, pubsub, "projects/gcp-data-poc-1208/topics/pipeline-stress") , 10, false);
				//CloudStorageUtils.processPathMock(bucket, path, new PubsubProcessor(id, pubsub, "projects/gcp-data-poc-1208/topics/pipeline-stress") , 1000, false);
				
				//CloudStorageUtils.processEvents(msg,
						// new PubsubProcessor(id, pubsub, "projects/gcp-data-poc-1208/topics/pipeline-stress") , 1);
				
				try {
					
					PubSubUtils.publishSingle(msg, "projects/gcp-data-poc-1208/topics/pipeline-stress");
					//PubSubUtils.publishNcopies(msg, "projects/gcp-data-poc-1208/topics/pipeline-stress", 1);
					result.setSuccessful(true);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					result.setSuccessful(false);
				}
				
				long elapsed = System.currentTimeMillis();
				
				result.setLatency(elapsed-start);
				
				 result.setStampAndTime(start, elapsed);
			    
		        //m_log.info(user+".."+param1+"..."+param2);
		        
		        return result;
		}
	    
		

		

	    
}
