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
import com.nsmjsf.web.datasources.BalancesheetDataSource;
import com.nsmjsf.web.datamodels.Balancesheet;
import com.nsmjsf.web.utils.ParameterManager;
/*imports  */
			
import com.nsmjsf.web.adapters.FinancialReportAdapter;


import com.nsmjsf.web.datasources.FinancialReportDataSource;

import com.nsmjsf.web.datamodels.FinancialReport;

import com.nsmjsf.web.wrappers.FinancialReportWrapper;



			
				   

@ManagedBean
@ViewScoped

public class CreateBalancesheetBean implements Serializable {

private static final Log log = LogFactory
			.getLog(CreateBalancesheetBean.class);


	private Balancesheet balancesheet;
	private BalancesheetDataSource balancesheetDataSource;
	
	
	
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
    private FinancialReportDataSource financialReportDataSource;
	private List<FinancialReportWrapper> financialReportWrapperList;
	private List<FinancialReport> financialReportList;
	private FinancialReportWrapper selectedFinancialReportWrapper;
	
	
			
			
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  	   
	
	
	private int editId=0;
	private boolean editMode=false;	
	
	
	
	
	
	

	public CreateBalancesheetBean() {

		balancesheet = new Balancesheet();
		/* init datasources */
		balancesheetDataSource = new BalancesheetDataSource();
		
		
			
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
			this.balancesheet=balancesheetDataSource.get(editId);
			
			

			  
			  this.selectedFinancialReportWrapper=FinancialReportAdapter.wrap(balancesheet.getFinancialReport());
	
			
				   
			
			
			
			
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

	public Balancesheet getBalancesheet() {
		return balancesheet;
	}

	public void setBalancesheet(Balancesheet balancesheet) {
		this.balancesheet = balancesheet;
	}

	/* getters for datasources */
	public BalancesheetDataSource getBalancesheetDataSource() {
		return balancesheetDataSource;
	}

	public void setBalancesheetDataSource(BalancesheetDataSource balancesheetDataSource) {
		this.balancesheetDataSource = balancesheetDataSource;
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








			
				




	
  
  
  
	public Balancesheet saveBalancesheet() {
		try {

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();
			
			
			
                  FinancialReport financialReport =selectedFinancialReportWrapper.getFinancialReport();

			balancesheet.setFinancialReport(financialReport);
			
				   
			
			
			
			
			balancesheetDataSource.create(balancesheet, session);
			tx.commit();
					MessageService.info("Successfully Saved  Balancesheet !");
				this.balancesheet=new Balancesheet();
			return balancesheet;

		} catch (Exception ex) {
		log.error(ex.getMessage());
			MessageService.error("Failed Saving Balancesheet .Try Again Later!");
			return null;
		}
	}
	
	public Balancesheet updateBalancesheet() {
		try {
		log.info("Starting to update....");

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();
			
			
			
                  FinancialReport financialReport = selectedFinancialReportWrapper.getFinancialReport();

			      balancesheet.setFinancialReport(financialReport);
			
				   
			
			
			
			
			balancesheetDataSource.create(balancesheet, session);
			tx.commit();
				MessageService.info("Successfully Saved  Balancesheet !");
				this.balancesheet=new Balancesheet();
			return balancesheet;

		} catch (Exception ex) {
			MessageService.error("Failed Saving Balancesheet .Try Again Later!");
			log.error(ex.getMessage());
			return null;
		}
	}
	
	public void saveOrUpdate(){
	
	if(this.editMode)
		{
		log.info("Updating value");
			updateBalancesheet();
		}else{
		log.info("Creating value");
			saveBalancesheet();
		}
	}
	public void cancel()
	{
	    RequestContext.getCurrentInstance().closeDialog("createBalancesheet");
		
	}
	public Balancesheet saveBalancesheet(Session session){
	
	   this.balancesheet= balancesheetDataSource.create(this.balancesheet,session);
	   return this.balancesheet;
	}
	

}

