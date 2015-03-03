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
import com.nsmjsf.web.datasources.AnnouncementTypeDataSource;
import com.nsmjsf.web.datamodels.AnnouncementType;
import com.nsmjsf.web.utils.ParameterManager;
/*imports  */	   

@ManagedBean
@ViewScoped

public class CreateAnnouncementTypeBean implements Serializable {

private static final Log log = LogFactory
			.getLog(CreateAnnouncementTypeBean.class);


	private AnnouncementType announcementType;
	private AnnouncementTypeDataSource announcementTypeDataSource;
	
	
	
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  	   
	
	
	private int editId=0;
	private boolean editMode=false;	
	
	
	
	
	
	

	public CreateAnnouncementTypeBean() {

		announcementType = new AnnouncementType();
		/* init datasources */
		announcementTypeDataSource = new AnnouncementTypeDataSource();
		
			
		
		

	}
	
	@PostConstruct
	private void init()
	{
		extractParams();
		if(this.editMode)
		{
			this.announcementType=announcementTypeDataSource.get(editId);
			
			
	   
			
			
			
			
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

	public AnnouncementType getAnnouncementType() {
		return announcementType;
	}

	public void setAnnouncementType(AnnouncementType announcementType) {
		this.announcementType = announcementType;
	}

	/* getters for datasources */
	public AnnouncementTypeDataSource getAnnouncementTypeDataSource() {
		return announcementTypeDataSource;
	}

	public void setAnnouncementTypeDataSource(AnnouncementTypeDataSource announcementTypeDataSource) {
		this.announcementTypeDataSource = announcementTypeDataSource;
	}
	
	
	
	
	
	
		




	
  
  
  
	public AnnouncementType saveAnnouncementType() {
		try {

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();
			
				   
			
			
			
			
			announcementTypeDataSource.create(announcementType, session);
			tx.commit();
					MessageService.info("Successfully Saved  AnnouncementType !");
				this.announcementType=new AnnouncementType();
			return announcementType;

		} catch (Exception ex) {
		log.error(ex.getMessage());
			MessageService.error("Failed Saving AnnouncementType .Try Again Later!");
			return null;
		}
	}
	
	public AnnouncementType updateAnnouncementType() {
		try {
		log.info("Starting to update....");

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();
			
				   
			
			
			
			
			announcementTypeDataSource.create(announcementType, session);
			tx.commit();
				MessageService.info("Successfully Saved  AnnouncementType !");
				this.announcementType=new AnnouncementType();
			return announcementType;

		} catch (Exception ex) {
			MessageService.error("Failed Saving AnnouncementType .Try Again Later!");
			log.error(ex.getMessage());
			return null;
		}
	}
	
	public void saveOrUpdate(){
	
	if(this.editMode)
		{
		log.info("Updating value");
			updateAnnouncementType();
		}else{
		log.info("Creating value");
			saveAnnouncementType();
		}
	}
	public void cancel()
	{
	    RequestContext.getCurrentInstance().closeDialog("createAnnouncementType");
		
	}
	public AnnouncementType saveAnnouncementType(Session session){
	
	   this.announcementType= announcementTypeDataSource.create(this.announcementType,session);
	   return this.announcementType;
	}
	

}

