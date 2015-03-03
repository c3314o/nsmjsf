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
import com.nsmjsf.web.datasources.BodTypeDataSource;
import com.nsmjsf.web.datamodels.BodType;
import com.nsmjsf.web.utils.ParameterManager;
/*imports  */	   

@ManagedBean
@ViewScoped

public class CreateBodTypeBean implements Serializable {

private static final Log log = LogFactory
			.getLog(CreateBodTypeBean.class);


	private BodType bodType;
	private BodTypeDataSource bodTypeDataSource;
	
	
	
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  	   
	
	
	private int editId=0;
	private boolean editMode=false;	
	
	
	
	
	
	

	public CreateBodTypeBean() {

		bodType = new BodType();
		/* init datasources */
		bodTypeDataSource = new BodTypeDataSource();
		
			
		
		

	}
	
	@PostConstruct
	private void init()
	{
		extractParams();
		if(this.editMode)
		{
			this.bodType=bodTypeDataSource.get(editId);
			
			
	   
			
			
			
			
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

	public BodType getBodType() {
		return bodType;
	}

	public void setBodType(BodType bodType) {
		this.bodType = bodType;
	}

	/* getters for datasources */
	public BodTypeDataSource getBodTypeDataSource() {
		return bodTypeDataSource;
	}

	public void setBodTypeDataSource(BodTypeDataSource bodTypeDataSource) {
		this.bodTypeDataSource = bodTypeDataSource;
	}
	
	
	
	
	
	
		




	
  
  
  
	public BodType saveBodType() {
		try {

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();
			
				   
			
			
			
			
			bodTypeDataSource.create(bodType, session);
			tx.commit();
					MessageService.info("Successfully Saved  BodType !");
				this.bodType=new BodType();
			return bodType;

		} catch (Exception ex) {
		log.error(ex.getMessage());
			MessageService.error("Failed Saving BodType .Try Again Later!");
			return null;
		}
	}
	
	public BodType updateBodType() {
		try {
		log.info("Starting to update....");

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();
			
				   
			
			
			
			
			bodTypeDataSource.create(bodType, session);
			tx.commit();
				MessageService.info("Successfully Saved  BodType !");
				this.bodType=new BodType();
			return bodType;

		} catch (Exception ex) {
			MessageService.error("Failed Saving BodType .Try Again Later!");
			log.error(ex.getMessage());
			return null;
		}
	}
	
	public void saveOrUpdate(){
	
	if(this.editMode)
		{
		log.info("Updating value");
			updateBodType();
		}else{
		log.info("Creating value");
			saveBodType();
		}
	}
	public void cancel()
	{
	    RequestContext.getCurrentInstance().closeDialog("createBodType");
		
	}
	public BodType saveBodType(Session session){
	
	   this.bodType= bodTypeDataSource.create(this.bodType,session);
	   return this.bodType;
	}
	

}

