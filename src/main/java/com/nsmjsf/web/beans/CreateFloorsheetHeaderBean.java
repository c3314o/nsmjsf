package com.nsmjsf.web.beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.annotation.PostConstruct;
import com.nsmjsf.web.datalayer.DbSessionManager;
import com.nsmjsf.web.messaging.MessageService;
import org.primefaces.context.RequestContext;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.nsmjsf.web.datasources.FloorsheetHeaderDataSource;
import com.nsmjsf.web.datamodels.FloorsheetHeader;
import com.nsmjsf.web.utils.ParameterManager;
/*imports  */	   

@ManagedBean
@ViewScoped

public class CreateFloorsheetHeaderBean implements Serializable {

private static final Log log = LogFactory
			.getLog(CreateFloorsheetHeaderBean.class);


	private FloorsheetHeader floorsheetHeader;
	private FloorsheetHeaderDataSource floorsheetHeaderDataSource;
	
	
	
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  	   
	
	
	private int editId=0;
	private boolean editMode=false;	
	
	
	
	
	
	

	public CreateFloorsheetHeaderBean() {

		floorsheetHeader = new FloorsheetHeader();
		/* init datasources */
		floorsheetHeaderDataSource = new FloorsheetHeaderDataSource();
		
			
		
		

	}
	
	@PostConstruct
	private void init()
	{
		extractParams();
		if(this.editMode)
		{
			this.floorsheetHeader=floorsheetHeaderDataSource.get(editId);
			
			
	   
			
			
			
			
		}
	}
	private void extractParams()
	{
		int editId = ParameterManager.getInt("editId");
		if(editId!=0)
		{
			this.editId=editId;
			this.editMode=true;
			System.out.println("EditId"+editId);
		}
	}

	public FloorsheetHeader getFloorsheetHeader() {
		return floorsheetHeader;
	}

	public void setFloorsheetHeader(FloorsheetHeader floorsheetHeader) {
		this.floorsheetHeader = floorsheetHeader;
	}

	/* getters for datasources */
	public FloorsheetHeaderDataSource getFloorsheetHeaderDataSource() {
		return floorsheetHeaderDataSource;
	}

	public void setFloorsheetHeaderDataSource(FloorsheetHeaderDataSource floorsheetHeaderDataSource) {
		this.floorsheetHeaderDataSource = floorsheetHeaderDataSource;
	}
	
	
	
	
	
	
		




	
  
  
  
	public FloorsheetHeader saveFloorsheetHeader() {
		try {

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();
			
				   
			
			
			
			
			floorsheetHeaderDataSource.create(floorsheetHeader, session);
			tx.commit();
					MessageService.info("Successfully Saved  FloorsheetHeader !");
				this.floorsheetHeader=new FloorsheetHeader();
			return floorsheetHeader;

		} catch (Exception ex) {
		log.error(ex.getMessage());
			MessageService.error("Failed Saving FloorsheetHeader .Try Again Later!");
			return null;
		}
	}
	
	public FloorsheetHeader updateFloorsheetHeader() {
		try {
		log.info("Starting to update....");

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();
			
				   
			
			
			
			
			floorsheetHeaderDataSource.create(floorsheetHeader, session);
			tx.commit();
				MessageService.info("Successfully Saved  FloorsheetHeader !");
				this.floorsheetHeader=new FloorsheetHeader();
			return floorsheetHeader;

		} catch (Exception ex) {
			MessageService.error("Failed Saving FloorsheetHeader .Try Again Later!");
			log.error(ex.getMessage());
			return null;
		}
	}
	
	public void saveOrUpdate(){
	
	if(this.editMode)
		{
		log.info("Updating value");
			updateFloorsheetHeader();
		}else{
		log.info("Creating value");
			saveFloorsheetHeader();
		}
	}
	public void cancel()
	{
	    RequestContext.getCurrentInstance().closeDialog("createFloorsheetHeader");
		
	}
	public FloorsheetHeader saveFloorsheetHeader(Session session){
	
	   this.floorsheetHeader= floorsheetHeaderDataSource.create(this.floorsheetHeader,session);
	   return this.floorsheetHeader;
	}
	

}

