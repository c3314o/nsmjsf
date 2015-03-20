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
import com.nsmjsf.web.datamodels.TodaysPriceDump;
/*
 * Singleton container for live todaysPrice data
 * 
 * 
 * 
 * 
 */
public class TodaysPriceLive {
	private static final Log log = LogFactory.getLog(TodaysPriceLive.class);
	
	HashMap<String,TodaysPriceDump> todaysPriceData;
	private static TodaysPriceLive INSTANCE=new TodaysPriceLive();
	private boolean isMarketOpen=true;
	
	public static TodaysPriceLive getInstance(){
		if(INSTANCE==null){
			INSTANCE=new TodaysPriceLive();
		}
		return INSTANCE;
	}
	
	private TodaysPriceLive(){
		log.info("Initializing todaysPrice live data container");
		todaysPriceData=new HashMap<String,TodaysPriceDump>();

	}
	
	public synchronized void updateData(TodaysPriceDump data){
		if(data!=null){
			todaysPriceData.put(data.getTodaysPriceCompanyName(), data);
			
		}
	}

	public HashMap<String, TodaysPriceDump> getTodaysPriceData() {
		return todaysPriceData;
	}

	public void setTodaysPriceData(HashMap<String, TodaysPriceDump> todaysPriceData) {
		this.todaysPriceData = todaysPriceData;
	}

	public boolean isMarketOpen() {
		return isMarketOpen;
	}

	public void setMarketOpen(boolean isMarketOpen) {
		this.isMarketOpen = isMarketOpen;
	}
	
	public List<TodaysPriceDump> getTodaysPriceList(){
		List<TodaysPriceDump> dumps=new ArrayList<TodaysPriceDump>();
		Iterator<Entry<String,TodaysPriceDump>> it=this.todaysPriceData.entrySet().iterator();
		while(it.hasNext()){
			Entry<String,TodaysPriceDump> entry=it.next();
			dumps.add(entry.getValue());
		}
		return dumps;
		
	}
	
	
	

}
