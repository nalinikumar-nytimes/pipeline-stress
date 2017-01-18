package com.nytimes.stress.pubsub;

import java.util.List;

public class PrintProcessor implements Processor{

	public void process(List<String> lines) {
		for(String line: lines ){
			System.out.println(line);
		}
		
	}
        public String getProcessorId(){
           return "";

        }

}
