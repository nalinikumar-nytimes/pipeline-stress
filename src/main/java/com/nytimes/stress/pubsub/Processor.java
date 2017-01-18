package com.nytimes.stress.pubsub;

import java.util.List;

public interface Processor {
	
	public void process(List<String> lines);
        public String getProcessorId();
}
