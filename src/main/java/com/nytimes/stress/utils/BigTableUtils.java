package com.nytimes.stress.utils;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.jmeter.samplers.SampleResult;

import com.google.cloud.bigtable.hbase.BigtableConfiguration;

public class BigTableUtils {

	private static final String PROJECT_ID = "gcp-data-poc-1208";
	private static final String ZONE = "us-central1-b"; // for example,
														// us-central1-b
	private static final String CLUSTER_ID = "access-real-time";

	private static Connection connection = null;
	
    private static StressUtils utils = new StressUtils();
    
    
    private static Log m_log = LogFactory.getLog(BigTableUtils.class);
	

	public static Connection getConnection() throws IOException {
		if(connection == null){
			connection = BigtableConfiguration.connect(PROJECT_ID, ZONE, CLUSTER_ID);
		}
		return connection;
	}
	
	

	
	
	public static SampleResult get(String rowid) {
		
		SampleResult result = new SampleResult();
		
		try {

			long start = System.currentTimeMillis();
		    
		    result.setTimeStamp(System.currentTimeMillis());
		     
			TableName tableName = TableName.valueOf("btstress");
			Table table;
			table = getConnection().getTable(tableName);
			
			Get get = new Get(Bytes.toBytes(rowid));
			
			get.addFamily(Bytes.toBytes("cf1"));
			
			
			Result r = table.get(get);
			
			
			byte [] value1 = r.getValue(Bytes.toBytes("cf1"),Bytes.toBytes("col1"));
			byte [] value2 = r.getValue(Bytes.toBytes("cf1"),Bytes.toBytes("col2"));
			byte [] value3 = r.getValue(Bytes.toBytes("cf1"),Bytes.toBytes("col3"));
			byte [] value4 = r.getValue(Bytes.toBytes("cf1"),Bytes.toBytes("col4"));
			byte [] value5 = r.getValue(Bytes.toBytes("cf1"),Bytes.toBytes("col5"));
			
			table.close();
			
		
			System.out.println(value1);
			
			long elapsed = System.currentTimeMillis();
			
            long lt = elapsed-start;
			
			result.setLatency(lt);

			result.setStampAndTime(start, lt);
			
			result.setSuccessful(true);
			
			result.setSampleLabel(rowid);
		     
	        result.setResponseMessage(rowid);
	        
	        result.setResponseData(rowid);
	        
		    
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setSuccessful(false);
		}
		
		return result;
	}

	
	
	
	
	public static SampleResult insert() {
		
		SampleResult result = new SampleResult();
		
		try {

			long start = System.currentTimeMillis();
		    
		    result.setTimeStamp(System.currentTimeMillis());
		     
			TableName tableName = TableName.valueOf("btstress");
			Table table;
			table = getConnection().getTable(tableName);
			
			String rowid = utils.generateRowID();
			
			Put p = new Put(Bytes.toBytes(rowid));
			p.addColumn(Bytes.toBytes("cf1"),Bytes.toBytes("col1") ,Bytes.toBytes("value1"));
			p.addColumn(Bytes.toBytes("cf1"),Bytes.toBytes("col2") ,Bytes.toBytes("value2"));
			p.addColumn(Bytes.toBytes("cf1"),Bytes.toBytes("col3") ,Bytes.toBytes("value3"));
			p.addColumn(Bytes.toBytes("cf1"),Bytes.toBytes("col4") ,Bytes.toBytes("value4"));
			p.addColumn(Bytes.toBytes("cf1"),Bytes.toBytes("col5") ,Bytes.toBytes("value5"));
			table.put(p);
			table.close();
			
			m_log.info("rowid .."+ rowid);

			long elapsed = System.currentTimeMillis();
			
            long lt = elapsed-start;
			
			result.setLatency(lt);

			result.setStampAndTime(start, lt);
			
			result.setSuccessful(true);
			
			result.setSampleLabel(rowid);
		     
	        result.setResponseMessage(rowid);
	        
	        result.setResponseData(rowid);
	        
		    
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setSuccessful(false);
		}
		
		return result;
	}

}
