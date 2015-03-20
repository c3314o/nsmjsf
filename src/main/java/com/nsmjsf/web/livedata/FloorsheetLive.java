package com.nsmjsf.web.livedata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.Application;
import com.nsmjsf.web.datamodels.FloorsheetDump;
/*
 * Singleton container for live floorsheet data
 * 
 * 
 * 
 * 
 */
public class FloorsheetLive {
	private static final Log log = LogFactory.getLog(FloorsheetLive.class);
	
	HashMap<Long,FloorsheetDump> floorsheetData;
	private static FloorsheetLive INSTANCE=new FloorsheetLive();
	private boolean isMarketOpen=true;
	
	public static FloorsheetLive getInstance(){
		if(INSTANCE==null){
			INSTANCE=new FloorsheetLive();
		}
		return INSTANCE;
	}
	
	private FloorsheetLive(){
		log.info("Initializing floorsheet live data container");
		floorsheetData=new HashMap<Long,FloorsheetDump>();

	}
	
	public synchronized void updateData(FloorsheetDump data){
		if(data!=null){
			floorsheetData.put(data.getFloorsheetTransactionNo(), data);
			
		}
	}

	public HashMap<Long, FloorsheetDump> getFloorsheetData() {
		return floorsheetData;
	}

	public void setFloorsheetData(HashMap<Long, FloorsheetDump> floorsheetData) {
		this.floorsheetData = floorsheetData;
	}

	public boolean isMarketOpen() {
		return isMarketOpen;
	}

	public void setMarketOpen(boolean isMarketOpen) {
		this.isMarketOpen = isMarketOpen;
	}
	
	public List<FloorsheetDump> getFloorsheetList(){
		List<FloorsheetDump> dumps=new ArrayList<FloorsheetDump>();
		Iterator<Entry<Long,FloorsheetDump>> it=this.floorsheetData.entrySet().iterator();
		while(it.hasNext()){
			Entry<Long,FloorsheetDump> entry=it.next();
			dumps.add(entry.getValue());
		}
		return dumps;
		
	}
	
	
	

}
