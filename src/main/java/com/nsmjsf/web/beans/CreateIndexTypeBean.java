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
import com.nsmjsf.web.datasources.IndexTypeDataSource;
import com.nsmjsf.web.datamodels.IndexType;
import com.nsmjsf.web.utils.ParameterManager;
/*imports  */	   

@ManagedBean
@ViewScoped

public class CreateIndexTypeBean implements Serializable {

private static final Log log = LogFactory
			.getLog(CreateIndexTypeBean.class);


	private IndexType indexType;
	private IndexTypeDataSource indexTypeDataSource;
	
	
	
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  	   
	
	
	private int editId=0;
	private boolean editMode=false;	
	
	
	
	
	
	

	public CreateIndexTypeBean() {

		indexType = new IndexType();
		/* init datasources */
		indexTypeDataSource = new IndexTypeDataSource();
		
			
		
		

	}
	
	@PostConstruct
	private void init()
	{
		extractParams();
		if(this.editMode)
		{
			this.indexType=indexTypeDataSource.get(editId);
			
			
	   
			
			
			
			
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

	public IndexType getIndexType() {
		return indexType;
	}

	public void setIndexType(IndexType indexType) {
		this.indexType = indexType;
	}

	/* getters for datasources */
	public IndexTypeDataSource getIndexTypeDataSource() {
		return indexTypeDataSource;
	}

	public void setIndexTypeDataSource(IndexTypeDataSource indexTypeDataSource) {
		this.indexTypeDataSource = indexTypeDataSource;
	}
	
	
	
	
	
	
		




	
  
  
  
	public IndexType saveIndexType() {
		try {

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();
			
				   
			
			
			
			
			indexTypeDataSource.create(indexType, session);
			tx.commit();
					MessageService.info("Successfully Saved  IndexType !");
				this.indexType=new IndexType();
			return indexType;

		} catch (Exception ex) {
		log.error(ex.getMessage());
			MessageService.error("Failed Saving IndexType .Try Again Later!");
			return null;
		}
	}
	
	public IndexType updateIndexType() {
		try {
		log.info("Starting to update....");

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();
			
				   
			
			
			
			
			indexTypeDataSource.create(indexType, session);
			tx.commit();
				MessageService.info("Successfully Saved  IndexType !");
				this.indexType=new IndexType();
			return indexType;

		} catch (Exception ex) {
			MessageService.error("Failed Saving IndexType .Try Again Later!");
			log.error(ex.getMessage());
			return null;
		}
	}
	
	public void saveOrUpdate(){
	
	if(this.editMode)
		{
		log.info("Updating value");
			updateIndexType();
		}else{
		log.info("Creating value");
			saveIndexType();
		}
	}
	public void cancel()
	{
	    RequestContext.getCurrentInstance().closeDialog("createIndexType");
		
	}
	public IndexType saveIndexType(Session session){
	
	   this.indexType= indexTypeDataSource.create(this.indexType,session);
	   return this.indexType;
	}
	

}

