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
import com.nsmjsf.web.datasources.EnergyPriceDataSource;
import com.nsmjsf.web.datamodels.EnergyPrice;
import com.nsmjsf.web.utils.ParameterManager;
/*imports  */	   

@ManagedBean
@ViewScoped

public class CreateEnergyPriceBean implements Serializable {

private static final Log log = LogFactory
			.getLog(CreateEnergyPriceBean.class);


	private EnergyPrice energyPrice;
	private EnergyPriceDataSource energyPriceDataSource;
	
	
	
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  	   
	
	
	private int editId=0;
	private boolean editMode=false;	
	
	
	
	
	
	

	public CreateEnergyPriceBean() {

		energyPrice = new EnergyPrice();
		/* init datasources */
		energyPriceDataSource = new EnergyPriceDataSource();
		
			
		
		

	}
	
	@PostConstruct
	private void init()
	{
		extractParams();
		if(this.editMode)
		{
			this.energyPrice=energyPriceDataSource.get(editId);
			
			
	   
			
			
			
			
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

	public EnergyPrice getEnergyPrice() {
		return energyPrice;
	}

	public void setEnergyPrice(EnergyPrice energyPrice) {
		this.energyPrice = energyPrice;
	}

	/* getters for datasources */
	public EnergyPriceDataSource getEnergyPriceDataSource() {
		return energyPriceDataSource;
	}

	public void setEnergyPriceDataSource(EnergyPriceDataSource energyPriceDataSource) {
		this.energyPriceDataSource = energyPriceDataSource;
	}
	
	
	
	
	
	
		




	
  
  
  
	public EnergyPrice saveEnergyPrice() {
		try {

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();
			
				   
			
			
			
			
			energyPriceDataSource.create(energyPrice, session);
			tx.commit();
					MessageService.info("Successfully Saved  EnergyPrice !");
				this.energyPrice=new EnergyPrice();
			return energyPrice;

		} catch (Exception ex) {
		log.error(ex.getMessage());
			MessageService.error("Failed Saving EnergyPrice .Try Again Later!");
			return null;
		}
	}
	
	public EnergyPrice updateEnergyPrice() {
		try {
		log.info("Starting to update....");

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();
			
				   
			
			
			
			
			energyPriceDataSource.create(energyPrice, session);
			tx.commit();
				MessageService.info("Successfully Saved  EnergyPrice !");
				this.energyPrice=new EnergyPrice();
			return energyPrice;

		} catch (Exception ex) {
			MessageService.error("Failed Saving EnergyPrice .Try Again Later!");
			log.error(ex.getMessage());
			return null;
		}
	}
	
	public void saveOrUpdate(){
	
	if(this.editMode)
		{
		log.info("Updating value");
			updateEnergyPrice();
		}else{
		log.info("Creating value");
			saveEnergyPrice();
		}
	}
	public void cancel()
	{
	    RequestContext.getCurrentInstance().closeDialog("createEnergyPrice");
		
	}
	public EnergyPrice saveEnergyPrice(Session session){
	
	   this.energyPrice= energyPriceDataSource.create(this.energyPrice,session);
	   return this.energyPrice;
	}
	

}

