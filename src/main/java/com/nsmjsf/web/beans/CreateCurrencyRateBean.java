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
import com.nsmjsf.web.datasources.CurrencyRateDataSource;
import com.nsmjsf.web.datamodels.CurrencyRate;
import com.nsmjsf.web.utils.ParameterManager;
/*imports  */
			
import com.nsmjsf.web.adapters.CurrencyTypeAdapter;


import com.nsmjsf.web.datasources.CurrencyTypeDataSource;

import com.nsmjsf.web.datamodels.CurrencyType;

import com.nsmjsf.web.wrappers.CurrencyTypeWrapper;



			
				   

@ManagedBean
@ViewScoped

public class CreateCurrencyRateBean implements Serializable {

private static final Log log = LogFactory
			.getLog(CreateCurrencyRateBean.class);


	private CurrencyRate currencyRate;
	private CurrencyRateDataSource currencyRateDataSource;
	
	
	
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
    private CurrencyTypeDataSource currencyTypeDataSource;
	private List<CurrencyTypeWrapper> currencyTypeWrapperList;
	private List<CurrencyType> currencyTypeList;
	private CurrencyTypeWrapper selectedCurrencyTypeWrapper;
	
	
			
			
			
		
			
			
			
	  
			
		
			
			
			
	  	   
	
	
	private int editId=0;
	private boolean editMode=false;	
	
	
	
	
	
	

	public CreateCurrencyRateBean() {

		currencyRate = new CurrencyRate();
		/* init datasources */
		currencyRateDataSource = new CurrencyRateDataSource();
		
		
			
currencyTypeDataSource = new CurrencyTypeDataSource();

		/* init option wrappers */
		currencyTypeList = currencyTypeDataSource.getAll();
		currencyTypeWrapperList = CurrencyTypeAdapter
				.wrapAll(currencyTypeList);
	
			
				
		
		

	}
	
	@PostConstruct
	private void init()
	{
		extractParams();
		if(this.editMode)
		{
			this.currencyRate=currencyRateDataSource.get(editId);
			
			

			  
			  this.selectedCurrencyTypeWrapper=CurrencyTypeAdapter.wrap(currencyRate.getCurrencyType());
	
			
				   
			
			
			
			
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

	public CurrencyRate getCurrencyRate() {
		return currencyRate;
	}

	public void setCurrencyRate(CurrencyRate currencyRate) {
		this.currencyRate = currencyRate;
	}

	/* getters for datasources */
	public CurrencyRateDataSource getCurrencyRateDataSource() {
		return currencyRateDataSource;
	}

	public void setCurrencyRateDataSource(CurrencyRateDataSource currencyRateDataSource) {
		this.currencyRateDataSource = currencyRateDataSource;
	}
	
	
	
	
	
	
	
			


public List<CurrencyType> getCurrencyTypeList() {
		return currencyTypeList;
	}

	public void setCurrencyTypeList(List<CurrencyType> currencyTypeList) {
		this.currencyTypeList = currencyTypeList;
	}
  
  
  
	public CurrencyTypeDataSource getCurrencyTypeDataSource() {
		return currencyTypeDataSource;
	}

	public void setCurrencyTypeDataSource(
			CurrencyTypeDataSource currencyTypeDataSource) {
		this.currencyTypeDataSource = currencyTypeDataSource;
	}

	public List<CurrencyTypeWrapper> getCurrencyTypeWrapperList() {
		return currencyTypeWrapperList;
	}

	public void setCurrencyTypeWrapperList(
			List<CurrencyTypeWrapper> currencyTypeWrapperList) {
		this.currencyTypeWrapperList = currencyTypeWrapperList;
	}

	

	public CurrencyTypeWrapper getSelectedCurrencyTypeWrapper() {
		return selectedCurrencyTypeWrapper;
	}

	public void setSelectedCurrencyTypeWrapper(
			CurrencyTypeWrapper selectedCurrencyTypeWrapper) {
		this.selectedCurrencyTypeWrapper = selectedCurrencyTypeWrapper;
	}








			
				




	
  
  
  
	public CurrencyRate saveCurrencyRate() {
		try {

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();
			
			
			
                  CurrencyType currencyType =selectedCurrencyTypeWrapper.getCurrencyType();

			currencyRate.setCurrencyType(currencyType);
			
				   
			
			
			
			
			currencyRateDataSource.create(currencyRate, session);
			tx.commit();
					MessageService.info("Successfully Saved  CurrencyRate !");
				this.currencyRate=new CurrencyRate();
			return currencyRate;

		} catch (Exception ex) {
		log.error(ex.getMessage());
			MessageService.error("Failed Saving CurrencyRate .Try Again Later!");
			return null;
		}
	}
	
	public CurrencyRate updateCurrencyRate() {
		try {
		log.info("Starting to update....");

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();
			
			
			
                  CurrencyType currencyType = selectedCurrencyTypeWrapper.getCurrencyType();

			      currencyRate.setCurrencyType(currencyType);
			
				   
			
			
			
			
			currencyRateDataSource.create(currencyRate, session);
			tx.commit();
				MessageService.info("Successfully Saved  CurrencyRate !");
				this.currencyRate=new CurrencyRate();
			return currencyRate;

		} catch (Exception ex) {
			MessageService.error("Failed Saving CurrencyRate .Try Again Later!");
			log.error(ex.getMessage());
			return null;
		}
	}
	
	public void saveOrUpdate(){
	
	if(this.editMode)
		{
		log.info("Updating value");
			updateCurrencyRate();
		}else{
		log.info("Creating value");
			saveCurrencyRate();
		}
	}
	public void cancel()
	{
	    RequestContext.getCurrentInstance().closeDialog("createCurrencyRate");
		
	}
	public CurrencyRate saveCurrencyRate(Session session){
	
	   this.currencyRate= currencyRateDataSource.create(this.currencyRate,session);
	   return this.currencyRate;
	}
	

}

