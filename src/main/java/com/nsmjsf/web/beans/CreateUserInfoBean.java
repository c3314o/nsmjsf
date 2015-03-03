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
import com.nsmjsf.web.datasources.UserInfoDataSource;
import com.nsmjsf.web.datamodels.UserInfo;
import com.nsmjsf.web.utils.ParameterManager;
/*imports  */	   

@ManagedBean
@ViewScoped

public class CreateUserInfoBean implements Serializable {

private static final Log log = LogFactory
			.getLog(CreateUserInfoBean.class);


	private UserInfo userInfo;
	private UserInfoDataSource userInfoDataSource;
	
	
	
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  	   
	
	
	private int editId=0;
	private boolean editMode=false;	
	
	
	
	
	
	

	public CreateUserInfoBean() {

		userInfo = new UserInfo();
		/* init datasources */
		userInfoDataSource = new UserInfoDataSource();
		
			
		
		

	}
	
	@PostConstruct
	private void init()
	{
		extractParams();
		if(this.editMode)
		{
			this.userInfo=userInfoDataSource.get(editId);
			
			
	   
			
			
			
			
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

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	/* getters for datasources */
	public UserInfoDataSource getUserInfoDataSource() {
		return userInfoDataSource;
	}

	public void setUserInfoDataSource(UserInfoDataSource userInfoDataSource) {
		this.userInfoDataSource = userInfoDataSource;
	}
	
	
	
	
	
	
		




	
  
  
  
	public UserInfo saveUserInfo() {
		try {

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();
			
				   
			
			
			
			
			userInfoDataSource.create(userInfo, session);
			tx.commit();
					MessageService.info("Successfully Saved  UserInfo !");
				this.userInfo=new UserInfo();
			return userInfo;

		} catch (Exception ex) {
		log.error(ex.getMessage());
			MessageService.error("Failed Saving UserInfo .Try Again Later!");
			return null;
		}
	}
	
	public UserInfo updateUserInfo() {
		try {
		log.info("Starting to update....");

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();
			
				   
			
			
			
			
			userInfoDataSource.create(userInfo, session);
			tx.commit();
				MessageService.info("Successfully Saved  UserInfo !");
				this.userInfo=new UserInfo();
			return userInfo;

		} catch (Exception ex) {
			MessageService.error("Failed Saving UserInfo .Try Again Later!");
			log.error(ex.getMessage());
			return null;
		}
	}
	
	public void saveOrUpdate(){
	
	if(this.editMode)
		{
		log.info("Updating value");
			updateUserInfo();
		}else{
		log.info("Creating value");
			saveUserInfo();
		}
	}
	public void cancel()
	{
	    RequestContext.getCurrentInstance().closeDialog("createUserInfo");
		
	}
	public UserInfo saveUserInfo(Session session){
	
	   this.userInfo= userInfoDataSource.create(this.userInfo,session);
	   return this.userInfo;
	}
	

}

