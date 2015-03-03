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
import com.nsmjsf.web.datasources.AuditStatusDataSource;
import com.nsmjsf.web.datamodels.AuditStatus;
import com.nsmjsf.web.utils.ParameterManager;
/*imports  */	   

@ManagedBean
@ViewScoped

public class CreateAuditStatusBean implements Serializable {

private static final Log log = LogFactory
			.getLog(CreateAuditStatusBean.class);


	private AuditStatus auditStatus;
	private AuditStatusDataSource auditStatusDataSource;
	
	
	
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  	   
	
	
	private int editId=0;
	private boolean editMode=false;	
	
	
	
	
	
	

	public CreateAuditStatusBean() {

		auditStatus = new AuditStatus();
		/* init datasources */
		auditStatusDataSource = new AuditStatusDataSource();
		
			
		
		

	}
	
	@PostConstruct
	private void init()
	{
		extractParams();
		if(this.editMode)
		{
			this.auditStatus=auditStatusDataSource.get(editId);
			
			
	   
			
			
			
			
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

	public AuditStatus getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(AuditStatus auditStatus) {
		this.auditStatus = auditStatus;
	}

	/* getters for datasources */
	public AuditStatusDataSource getAuditStatusDataSource() {
		return auditStatusDataSource;
	}

	public void setAuditStatusDataSource(AuditStatusDataSource auditStatusDataSource) {
		this.auditStatusDataSource = auditStatusDataSource;
	}
	
	
	
	
	
	
		




	
  
  
  
	public AuditStatus saveAuditStatus() {
		try {

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();
			
				   
			
			
			
			
			auditStatusDataSource.create(auditStatus, session);
			tx.commit();
					MessageService.info("Successfully Saved  AuditStatus !");
				this.auditStatus=new AuditStatus();
			return auditStatus;

		} catch (Exception ex) {
		log.error(ex.getMessage());
			MessageService.error("Failed Saving AuditStatus .Try Again Later!");
			return null;
		}
	}
	
	public AuditStatus updateAuditStatus() {
		try {
		log.info("Starting to update....");

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();
			
				   
			
			
			
			
			auditStatusDataSource.create(auditStatus, session);
			tx.commit();
				MessageService.info("Successfully Saved  AuditStatus !");
				this.auditStatus=new AuditStatus();
			return auditStatus;

		} catch (Exception ex) {
			MessageService.error("Failed Saving AuditStatus .Try Again Later!");
			log.error(ex.getMessage());
			return null;
		}
	}
	
	public void saveOrUpdate(){
	
	if(this.editMode)
		{
		log.info("Updating value");
			updateAuditStatus();
		}else{
		log.info("Creating value");
			saveAuditStatus();
		}
	}
	public void cancel()
	{
	    RequestContext.getCurrentInstance().closeDialog("createAuditStatus");
		
	}
	public AuditStatus saveAuditStatus(Session session){
	
	   this.auditStatus= auditStatusDataSource.create(this.auditStatus,session);
	   return this.auditStatus;
	}
	

}

