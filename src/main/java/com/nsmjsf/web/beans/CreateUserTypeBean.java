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
import com.nsmjsf.web.datasources.UserTypeDataSource;
import com.nsmjsf.web.datamodels.UserType;
import com.nsmjsf.web.utils.ParameterManager;
/*imports  */	   

@ManagedBean
@ViewScoped

public class CreateUserTypeBean implements Serializable {

private static final Log log = LogFactory
			.getLog(CreateUserTypeBean.class);


	private UserType userType;
	private UserTypeDataSource userTypeDataSource;
	
	
	
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  	   
	
	
	private int editId=0;
	private boolean editMode=false;	
	
	
	
	
	
	

	public CreateUserTypeBean() {

		userType = new UserType();
		/* init datasources */
		userTypeDataSource = new UserTypeDataSource();
		
			
		
		

	}
	
	@PostConstruct
	private void init()
	{
		extractParams();
		if(this.editMode)
		{
			this.userType=userTypeDataSource.get(editId);
			
			
	   
			
			
			
			
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

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	/* getters for datasources */
	public UserTypeDataSource getUserTypeDataSource() {
		return userTypeDataSource;
	}

	public void setUserTypeDataSource(UserTypeDataSource userTypeDataSource) {
		this.userTypeDataSource = userTypeDataSource;
	}
	
	
	
	
	
	
		




	
  
  
  
	public UserType saveUserType() {
		try {

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();
			
				   
			
			
			
			
			userTypeDataSource.create(userType, session);
			tx.commit();
					MessageService.info("Successfully Saved  UserType !");
				this.userType=new UserType();
			return userType;

		} catch (Exception ex) {
		log.error(ex.getMessage());
			MessageService.error("Failed Saving UserType .Try Again Later!");
			return null;
		}
	}
	
	public UserType updateUserType() {
		try {
		log.info("Starting to update....");

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();
			
				   
			
			
			
			
			userTypeDataSource.create(userType, session);
			tx.commit();
				MessageService.info("Successfully Saved  UserType !");
				this.userType=new UserType();
			return userType;

		} catch (Exception ex) {
			MessageService.error("Failed Saving UserType .Try Again Later!");
			log.error(ex.getMessage());
			return null;
		}
	}
	
	public void saveOrUpdate(){
	
	if(this.editMode)
		{
		log.info("Updating value");
			updateUserType();
		}else{
		log.info("Creating value");
			saveUserType();
		}
	}
	public void cancel()
	{
	    RequestContext.getCurrentInstance().closeDialog("createUserType");
		
	}
	public UserType saveUserType(Session session){
	
	   this.userType= userTypeDataSource.create(this.userType,session);
	   return this.userType;
	}
	

}

