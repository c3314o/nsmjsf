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
import com.nsmjsf.web.datasources.TodaysPriceDumpDataSource;
import com.nsmjsf.web.datamodels.TodaysPriceDump;
import com.nsmjsf.web.utils.ParameterManager;
/*imports  */	   

@ManagedBean
@ViewScoped

public class CreateTodaysPriceDumpBean implements Serializable {

private static final Log log = LogFactory
			.getLog(CreateTodaysPriceDumpBean.class);


	private TodaysPriceDump todaysPriceDump;
	private TodaysPriceDumpDataSource todaysPriceDumpDataSource;
	
	
	
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  	   
	
	
	private int editId=0;
	private boolean editMode=false;	
	
	
	
	
	
	

	public CreateTodaysPriceDumpBean() {

		todaysPriceDump = new TodaysPriceDump();
		/* init datasources */
		todaysPriceDumpDataSource = new TodaysPriceDumpDataSource();
		
			
		
		

	}
	
	@PostConstruct
	private void init()
	{
		extractParams();
		if(this.editMode)
		{
			this.todaysPriceDump=todaysPriceDumpDataSource.get(editId);
			
			
	   
			
			
			
			
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

	public TodaysPriceDump getTodaysPriceDump() {
		return todaysPriceDump;
	}

	public void setTodaysPriceDump(TodaysPriceDump todaysPriceDump) {
		this.todaysPriceDump = todaysPriceDump;
	}

	/* getters for datasources */
	public TodaysPriceDumpDataSource getTodaysPriceDumpDataSource() {
		return todaysPriceDumpDataSource;
	}

	public void setTodaysPriceDumpDataSource(TodaysPriceDumpDataSource todaysPriceDumpDataSource) {
		this.todaysPriceDumpDataSource = todaysPriceDumpDataSource;
	}
	
	
	
	
	
	
		




	
  
  
  
	public TodaysPriceDump saveTodaysPriceDump() {
		try {

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();
			
				   
			
			
			
			
			todaysPriceDumpDataSource.create(todaysPriceDump, session);
			tx.commit();
					MessageService.info("Successfully Saved  TodaysPriceDump !");
				this.todaysPriceDump=new TodaysPriceDump();
			return todaysPriceDump;

		} catch (Exception ex) {
		log.error(ex.getMessage());
			MessageService.error("Failed Saving TodaysPriceDump .Try Again Later!");
			return null;
		}
	}
	
	public TodaysPriceDump updateTodaysPriceDump() {
		try {
		log.info("Starting to update....");

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();
			
				   
			
			
			
			
			todaysPriceDumpDataSource.create(todaysPriceDump, session);
			tx.commit();
				MessageService.info("Successfully Saved  TodaysPriceDump !");
				this.todaysPriceDump=new TodaysPriceDump();
			return todaysPriceDump;

		} catch (Exception ex) {
			MessageService.error("Failed Saving TodaysPriceDump .Try Again Later!");
			log.error(ex.getMessage());
			return null;
		}
	}
	
	public void saveOrUpdate(){
	
	if(this.editMode)
		{
		log.info("Updating value");
			updateTodaysPriceDump();
		}else{
		log.info("Creating value");
			saveTodaysPriceDump();
		}
	}
	public void cancel()
	{
	    RequestContext.getCurrentInstance().closeDialog("createTodaysPriceDump");
		
	}
	public TodaysPriceDump saveTodaysPriceDump(Session session){
	
	   this.todaysPriceDump= todaysPriceDumpDataSource.create(this.todaysPriceDump,session);
	   return this.todaysPriceDump;
	}
	

}

