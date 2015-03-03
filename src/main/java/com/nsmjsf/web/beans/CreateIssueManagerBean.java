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
import com.nsmjsf.web.datasources.IssueManagerDataSource;
import com.nsmjsf.web.datamodels.IssueManager;
import com.nsmjsf.web.utils.ParameterManager;
/*imports  */	   

@ManagedBean
@ViewScoped

public class CreateIssueManagerBean implements Serializable {

private static final Log log = LogFactory
			.getLog(CreateIssueManagerBean.class);


	private IssueManager issueManager;
	private IssueManagerDataSource issueManagerDataSource;
	
	
	
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  	   
	
	
	private int editId=0;
	private boolean editMode=false;	
	
	
	
	
	
	

	public CreateIssueManagerBean() {

		issueManager = new IssueManager();
		/* init datasources */
		issueManagerDataSource = new IssueManagerDataSource();
		
			
		
		

	}
	
	@PostConstruct
	private void init()
	{
		extractParams();
		if(this.editMode)
		{
			this.issueManager=issueManagerDataSource.get(editId);
			
			
	   
			
			
			
			
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

	public IssueManager getIssueManager() {
		return issueManager;
	}

	public void setIssueManager(IssueManager issueManager) {
		this.issueManager = issueManager;
	}

	/* getters for datasources */
	public IssueManagerDataSource getIssueManagerDataSource() {
		return issueManagerDataSource;
	}

	public void setIssueManagerDataSource(IssueManagerDataSource issueManagerDataSource) {
		this.issueManagerDataSource = issueManagerDataSource;
	}
	
	
	
	
	
	
		




	
  
  
  
	public IssueManager saveIssueManager() {
		try {

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();
			
				   
			
			
			
			
			issueManagerDataSource.create(issueManager, session);
			tx.commit();
					MessageService.info("Successfully Saved  IssueManager !");
				this.issueManager=new IssueManager();
			return issueManager;

		} catch (Exception ex) {
		log.error(ex.getMessage());
			MessageService.error("Failed Saving IssueManager .Try Again Later!");
			return null;
		}
	}
	
	public IssueManager updateIssueManager() {
		try {
		log.info("Starting to update....");

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();
			
				   
			
			
			
			
			issueManagerDataSource.create(issueManager, session);
			tx.commit();
				MessageService.info("Successfully Saved  IssueManager !");
				this.issueManager=new IssueManager();
			return issueManager;

		} catch (Exception ex) {
			MessageService.error("Failed Saving IssueManager .Try Again Later!");
			log.error(ex.getMessage());
			return null;
		}
	}
	
	public void saveOrUpdate(){
	
	if(this.editMode)
		{
		log.info("Updating value");
			updateIssueManager();
		}else{
		log.info("Creating value");
			saveIssueManager();
		}
	}
	public void cancel()
	{
	    RequestContext.getCurrentInstance().closeDialog("createIssueManager");
		
	}
	public IssueManager saveIssueManager(Session session){
	
	   this.issueManager= issueManagerDataSource.create(this.issueManager,session);
	   return this.issueManager;
	}
	

}

