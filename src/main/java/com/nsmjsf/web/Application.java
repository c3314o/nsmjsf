package com.nsmjsf.web;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.adapters.AgmAdapter;
import com.nsmjsf.web.livedata.FloorsheetLive;
@ManagedBean(eager=true)
@ApplicationScoped
public class Application {
	private static final Log log = LogFactory.getLog(Application.class);

	
	public void Application(){
		
		log.info("Application initialized......");
		log.info("Initializing Floorsheet live data");
		FloorsheetLive.getInstance();
		
	}

}
