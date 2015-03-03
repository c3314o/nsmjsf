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
import com.nsmjsf.web.datasources.FloorsheetDumpDataSource;
import com.nsmjsf.web.datamodels.FloorsheetDump;
import com.nsmjsf.web.utils.ParameterManager;
/*imports  */	   

@ManagedBean
@ViewScoped

public class CreateFloorsheetDumpBean implements Serializable {

private static final Log log = LogFactory
			.getLog(CreateFloorsheetDumpBean.class);


	private FloorsheetDump floorsheetDump;
	private FloorsheetDumpDataSource floorsheetDumpDataSource;
	
	
	
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  	   
	
	
	private int editId=0;
	private boolean editMode=false;	
	
	
	
	
	
	

	public CreateFloorsheetDumpBean() {

		floorsheetDump = new FloorsheetDump();
		/* init datasources */
		floorsheetDumpDataSource = new FloorsheetDumpDataSource();
		
			
		
		

	}
	
	@PostConstruct
	private void init()
	{
		extractParams();
		if(this.editMode)
		{
			this.floorsheetDump=floorsheetDumpDataSource.get(editId);
			
			
	   
			
			
			
			
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

	public FloorsheetDump getFloorsheetDump() {
		return floorsheetDump;
	}

	public void setFloorsheetDump(FloorsheetDump floorsheetDump) {
		this.floorsheetDump = floorsheetDump;
	}

	/* getters for datasources */
	public FloorsheetDumpDataSource getFloorsheetDumpDataSource() {
		return floorsheetDumpDataSource;
	}

	public void setFloorsheetDumpDataSource(FloorsheetDumpDataSource floorsheetDumpDataSource) {
		this.floorsheetDumpDataSource = floorsheetDumpDataSource;
	}
	
	
	
	
	
	
		




	
  
  
  
	public FloorsheetDump saveFloorsheetDump() {
		try {

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();
			
				   
			
			
			
			
			floorsheetDumpDataSource.create(floorsheetDump, session);
			tx.commit();
					MessageService.info("Successfully Saved  FloorsheetDump !");
				this.floorsheetDump=new FloorsheetDump();
			return floorsheetDump;

		} catch (Exception ex) {
		log.error(ex.getMessage());
			MessageService.error("Failed Saving FloorsheetDump .Try Again Later!");
			return null;
		}
	}
	
	public FloorsheetDump updateFloorsheetDump() {
		try {
		log.info("Starting to update....");

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();
			
				   
			
			
			
			
			floorsheetDumpDataSource.create(floorsheetDump, session);
			tx.commit();
				MessageService.info("Successfully Saved  FloorsheetDump !");
				this.floorsheetDump=new FloorsheetDump();
			return floorsheetDump;

		} catch (Exception ex) {
			MessageService.error("Failed Saving FloorsheetDump .Try Again Later!");
			log.error(ex.getMessage());
			return null;
		}
	}
	
	public void saveOrUpdate(){
	
	if(this.editMode)
		{
		log.info("Updating value");
			updateFloorsheetDump();
		}else{
		log.info("Creating value");
			saveFloorsheetDump();
		}
	}
	public void cancel()
	{
	    RequestContext.getCurrentInstance().closeDialog("createFloorsheetDump");
		
	}
	public FloorsheetDump saveFloorsheetDump(Session session){
	
	   this.floorsheetDump= floorsheetDumpDataSource.create(this.floorsheetDump,session);
	   return this.floorsheetDump;
	}
	

}

