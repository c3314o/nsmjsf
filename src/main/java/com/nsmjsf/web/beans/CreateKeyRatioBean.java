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
import com.nsmjsf.web.datasources.KeyRatioDataSource;
import com.nsmjsf.web.datamodels.KeyRatio;
import com.nsmjsf.web.utils.ParameterManager;
/*imports  */
			
import com.nsmjsf.web.adapters.FinancialReportAdapter;


import com.nsmjsf.web.datasources.FinancialReportDataSource;

import com.nsmjsf.web.datamodels.FinancialReport;

import com.nsmjsf.web.wrappers.FinancialReportWrapper;



			
				   

@ManagedBean
@ViewScoped

public class CreateKeyRatioBean implements Serializable {

private static final Log log = LogFactory
			.getLog(CreateKeyRatioBean.class);


	private KeyRatio keyRatio;
	private KeyRatioDataSource keyRatioDataSource;
	
	
	
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
    private FinancialReportDataSource financialReportDataSource;
	private List<FinancialReportWrapper> financialReportWrapperList;
	private List<FinancialReport> financialReportList;
	private FinancialReportWrapper selectedFinancialReportWrapper;
	
	
			
			
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  	   
	
	
	private int editId=0;
	private boolean editMode=false;	
	
	
	
	
	
	

	public CreateKeyRatioBean() {

		keyRatio = new KeyRatio();
		/* init datasources */
		keyRatioDataSource = new KeyRatioDataSource();
		
		
			
financialReportDataSource = new FinancialReportDataSource();

		/* init option wrappers */
		financialReportList = financialReportDataSource.getAll();
		financialReportWrapperList = FinancialReportAdapter
				.wrapAll(financialReportList);
	
			
				
		
		

	}
	
	@PostConstruct
	private void init()
	{
		extractParams();
		if(this.editMode)
		{
			this.keyRatio=keyRatioDataSource.get(editId);
			
			

			  
			  this.selectedFinancialReportWrapper=FinancialReportAdapter.wrap(keyRatio.getFinancialReport());
	
			
				   
			
			
			
			
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

	public KeyRatio getKeyRatio() {
		return keyRatio;
	}

	public void setKeyRatio(KeyRatio keyRatio) {
		this.keyRatio = keyRatio;
	}

	/* getters for datasources */
	public KeyRatioDataSource getKeyRatioDataSource() {
		return keyRatioDataSource;
	}

	public void setKeyRatioDataSource(KeyRatioDataSource keyRatioDataSource) {
		this.keyRatioDataSource = keyRatioDataSource;
	}
	
	
	
	
	
	
	
			


public List<FinancialReport> getFinancialReportList() {
		return financialReportList;
	}

	public void setFinancialReportList(List<FinancialReport> financialReportList) {
		this.financialReportList = financialReportList;
	}
  
  
  
	public FinancialReportDataSource getFinancialReportDataSource() {
		return financialReportDataSource;
	}

	public void setFinancialReportDataSource(
			FinancialReportDataSource financialReportDataSource) {
		this.financialReportDataSource = financialReportDataSource;
	}

	public List<FinancialReportWrapper> getFinancialReportWrapperList() {
		return financialReportWrapperList;
	}

	public void setFinancialReportWrapperList(
			List<FinancialReportWrapper> financialReportWrapperList) {
		this.financialReportWrapperList = financialReportWrapperList;
	}

	

	public FinancialReportWrapper getSelectedFinancialReportWrapper() {
		return selectedFinancialReportWrapper;
	}

	public void setSelectedFinancialReportWrapper(
			FinancialReportWrapper selectedFinancialReportWrapper) {
		this.selectedFinancialReportWrapper = selectedFinancialReportWrapper;
	}








			
				




	
  
  
  
	public KeyRatio saveKeyRatio() {
		try {

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();
			
			
			
                  FinancialReport financialReport =selectedFinancialReportWrapper.getFinancialReport();

			keyRatio.setFinancialReport(financialReport);
			
				   
			
			
			
			
			keyRatioDataSource.create(keyRatio, session);
			tx.commit();
					MessageService.info("Successfully Saved  KeyRatio !");
				this.keyRatio=new KeyRatio();
			return keyRatio;

		} catch (Exception ex) {
		log.error(ex.getMessage());
			MessageService.error("Failed Saving KeyRatio .Try Again Later!");
			return null;
		}
	}
	
	public KeyRatio updateKeyRatio() {
		try {
		log.info("Starting to update....");

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();
			
			
			
                  FinancialReport financialReport = selectedFinancialReportWrapper.getFinancialReport();

			      keyRatio.setFinancialReport(financialReport);
			
				   
			
			
			
			
			keyRatioDataSource.create(keyRatio, session);
			tx.commit();
				MessageService.info("Successfully Saved  KeyRatio !");
				this.keyRatio=new KeyRatio();
			return keyRatio;

		} catch (Exception ex) {
			MessageService.error("Failed Saving KeyRatio .Try Again Later!");
			log.error(ex.getMessage());
			return null;
		}
	}
	
	public void saveOrUpdate(){
	
	if(this.editMode)
		{
		log.info("Updating value");
			updateKeyRatio();
		}else{
		log.info("Creating value");
			saveKeyRatio();
		}
	}
	public void cancel()
	{
	    RequestContext.getCurrentInstance().closeDialog("createKeyRatio");
		
	}
	public KeyRatio saveKeyRatio(Session session){
	
	   this.keyRatio= keyRatioDataSource.create(this.keyRatio,session);
	   return this.keyRatio;
	}
	

}

