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
import com.nsmjsf.web.datasources.ProfitLossDataSource;
import com.nsmjsf.web.datamodels.ProfitLoss;
import com.nsmjsf.web.utils.ParameterManager;
/*imports  */
			
import com.nsmjsf.web.adapters.FinancialReportAdapter;


import com.nsmjsf.web.datasources.FinancialReportDataSource;

import com.nsmjsf.web.datamodels.FinancialReport;

import com.nsmjsf.web.wrappers.FinancialReportWrapper;



			
				   

@ManagedBean
@ViewScoped

public class CreateProfitLossBean implements Serializable {

private static final Log log = LogFactory
			.getLog(CreateProfitLossBean.class);


	private ProfitLoss profitLoss;
	private ProfitLossDataSource profitLossDataSource;
	
	
	
			
		
			
			
			
	  
			
		
			
			
			
	  
			
    private FinancialReportDataSource financialReportDataSource;
	private List<FinancialReportWrapper> financialReportWrapperList;
	private List<FinancialReport> financialReportList;
	private FinancialReportWrapper selectedFinancialReportWrapper;
	
	
			
			
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  	   
	
	
	private int editId=0;
	private boolean editMode=false;	
	
	
	
	
	
	

	public CreateProfitLossBean() {

		profitLoss = new ProfitLoss();
		/* init datasources */
		profitLossDataSource = new ProfitLossDataSource();
		
		
			
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
			this.profitLoss=profitLossDataSource.get(editId);
			
			

			  
			  this.selectedFinancialReportWrapper=FinancialReportAdapter.wrap(profitLoss.getFinancialReport());
	
			
				   
			
			
			
			
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

	public ProfitLoss getProfitLoss() {
		return profitLoss;
	}

	public void setProfitLoss(ProfitLoss profitLoss) {
		this.profitLoss = profitLoss;
	}

	/* getters for datasources */
	public ProfitLossDataSource getProfitLossDataSource() {
		return profitLossDataSource;
	}

	public void setProfitLossDataSource(ProfitLossDataSource profitLossDataSource) {
		this.profitLossDataSource = profitLossDataSource;
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








			
				




	
  
  
  
	public ProfitLoss saveProfitLoss() {
		try {

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();
			
			
			
                  FinancialReport financialReport =selectedFinancialReportWrapper.getFinancialReport();

			profitLoss.setFinancialReport(financialReport);
			
				   
			
			
			
			
			profitLossDataSource.create(profitLoss, session);
			tx.commit();
					MessageService.info("Successfully Saved  ProfitLoss !");
				this.profitLoss=new ProfitLoss();
			return profitLoss;

		} catch (Exception ex) {
		log.error(ex.getMessage());
			MessageService.error("Failed Saving ProfitLoss .Try Again Later!");
			return null;
		}
	}
	
	public ProfitLoss updateProfitLoss() {
		try {
		log.info("Starting to update....");

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();
			
			
			
                  FinancialReport financialReport = selectedFinancialReportWrapper.getFinancialReport();

			      profitLoss.setFinancialReport(financialReport);
			
				   
			
			
			
			
			profitLossDataSource.create(profitLoss, session);
			tx.commit();
				MessageService.info("Successfully Saved  ProfitLoss !");
				this.profitLoss=new ProfitLoss();
			return profitLoss;

		} catch (Exception ex) {
			MessageService.error("Failed Saving ProfitLoss .Try Again Later!");
			log.error(ex.getMessage());
			return null;
		}
	}
	
	public void saveOrUpdate(){
	
	if(this.editMode)
		{
		log.info("Updating value");
			updateProfitLoss();
		}else{
		log.info("Creating value");
			saveProfitLoss();
		}
	}
	public void cancel()
	{
	    RequestContext.getCurrentInstance().closeDialog("createProfitLoss");
		
	}
	public ProfitLoss saveProfitLoss(Session session){
	
	   this.profitLoss= profitLossDataSource.create(this.profitLoss,session);
	   return this.profitLoss;
	}
	

}

