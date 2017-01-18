package com.nytimes.stress.pubsub;

import java.util.ArrayList;
import java.util.List;



import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.google.api.services.pubsub.Pubsub;
import com.google.api.services.pubsub.model.PublishRequest;
import com.google.api.services.pubsub.model.PubsubMessage;

public class PubsubProcessor implements Processor{
	
	private final String processorId;
	private final Pubsub pubsub;
	private final String topicArn;
        private static Log m_log = LogFactory.getLog(PubsubProcessor.class);
	
	public PubsubProcessor(String processorId, Pubsub pubsub,String topicArn){
		this.pubsub=pubsub;
		this.topicArn = topicArn;
		this.processorId = processorId;
	}
        
        public String getProcessorId(){
               return processorId;
        }

	public void process(List<String> lines) {
		try {
			
			if(lines.isEmpty()) return;
                        
                        m_log.info(this.processorId+".."+lines.size());
			
			List<PubsubMessage> messages = new ArrayList<PubsubMessage>();
			for(String s : lines){
				PubsubMessage pubsubMessage = new PubsubMessage();
				pubsubMessage.encodeData(s.getBytes("UTF-8"));
				messages.add(pubsubMessage);
			}
			
			PublishRequest publishRequest = new PublishRequest().setMessages(messages);
			pubsub.projects().topics().publish(topicArn, publishRequest).execute();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
