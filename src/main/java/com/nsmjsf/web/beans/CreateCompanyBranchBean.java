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
import com.nsmjsf.web.datasources.CompanyBranchDataSource;
import com.nsmjsf.web.datamodels.CompanyBranch;
import com.nsmjsf.web.utils.ParameterManager;
/*imports  */	   

@ManagedBean
@ViewScoped

public class CreateCompanyBranchBean implements Serializable {

private static final Log log = LogFactory
			.getLog(CreateCompanyBranchBean.class);


	private CompanyBranch companyBranch;
	private CompanyBranchDataSource companyBranchDataSource;
	
	
	
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  	   
	
	
	private int editId=0;
	private boolean editMode=false;	
	
	
	
	
	
	

	public CreateCompanyBranchBean() {

		companyBranch = new CompanyBranch();
		/* init datasources */
		companyBranchDataSource = new CompanyBranchDataSource();
		
			
		
		

	}
	
	@PostConstruct
	private void init()
	{
		extractParams();
		if(this.editMode)
		{
			this.companyBranch=companyBranchDataSource.get(editId);
			
			
	   
			
			
			
			
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

	public CompanyBranch getCompanyBranch() {
		return companyBranch;
	}

	public void setCompanyBranch(CompanyBranch companyBranch) {
		this.companyBranch = companyBranch;
	}

	/* getters for datasources */
	public CompanyBranchDataSource getCompanyBranchDataSource() {
		return companyBranchDataSource;
	}

	public void setCompanyBranchDataSource(CompanyBranchDataSource companyBranchDataSource) {
		this.companyBranchDataSource = companyBranchDataSource;
	}
	
	
	
	
	
	
		




	
  
  
  
	public CompanyBranch saveCompanyBranch() {
		try {

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();
			
				   
			
			
			
			
			companyBranchDataSource.create(companyBranch, session);
			tx.commit();
					MessageService.info("Successfully Saved  CompanyBranch !");
				this.companyBranch=new CompanyBranch();
			return companyBranch;

		} catch (Exception ex) {
		log.error(ex.getMessage());
			MessageService.error("Failed Saving CompanyBranch .Try Again Later!");
			return null;
		}
	}
	
	public CompanyBranch updateCompanyBranch() {
		try {
		log.info("Starting to update....");

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();
			
				   
			
			
			
			
			companyBranchDataSource.create(companyBranch, session);
			tx.commit();
				MessageService.info("Successfully Saved  CompanyBranch !");
				this.companyBranch=new CompanyBranch();
			return companyBranch;

		} catch (Exception ex) {
			MessageService.error("Failed Saving CompanyBranch .Try Again Later!");
			log.error(ex.getMessage());
			return null;
		}
	}
	
	public void saveOrUpdate(){
	
	if(this.editMode)
		{
		log.info("Updating value");
			updateCompanyBranch();
		}else{
		log.info("Creating value");
			saveCompanyBranch();
		}
	}
	public void cancel()
	{
	    RequestContext.getCurrentInstance().closeDialog("createCompanyBranch");
		
	}
	public CompanyBranch saveCompanyBranch(Session session){
	
	   this.companyBranch= companyBranchDataSource.create(this.companyBranch,session);
	   return this.companyBranch;
	}
	

}

