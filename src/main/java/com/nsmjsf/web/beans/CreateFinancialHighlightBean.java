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
import com.nsmjsf.web.datasources.FinancialHighlightDataSource;
import com.nsmjsf.web.datamodels.FinancialHighlight;
import com.nsmjsf.web.utils.ParameterManager;
/*imports  */
			
import com.nsmjsf.web.adapters.FiscalYearAdapter;


import com.nsmjsf.web.datasources.FiscalYearDataSource;

import com.nsmjsf.web.datamodels.FiscalYear;

import com.nsmjsf.web.wrappers.FiscalYearWrapper;



			
			
			
import com.nsmjsf.web.adapters.CompanyAdapter;


import com.nsmjsf.web.datasources.CompanyDataSource;

import com.nsmjsf.web.datamodels.Company;

import com.nsmjsf.web.wrappers.CompanyWrapper;



			
			
			
import com.nsmjsf.web.adapters.QuarterAdapter;


import com.nsmjsf.web.datasources.QuarterDataSource;

import com.nsmjsf.web.datamodels.Quarter;

import com.nsmjsf.web.wrappers.QuarterWrapper;



			
				   

@ManagedBean
@ViewScoped

public class CreateFinancialHighlightBean implements Serializable {

private static final Log log = LogFactory
			.getLog(CreateFinancialHighlightBean.class);


	private FinancialHighlight financialHighlight;
	private FinancialHighlightDataSource financialHighlightDataSource;
	
	
	
			
    private FiscalYearDataSource fiscalYearDataSource;
	private List<FiscalYearWrapper> fiscalYearWrapperList;
	private List<FiscalYear> fiscalYearList;
	private FiscalYearWrapper selectedFiscalYearWrapper;
	
	
			
			
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
    private CompanyDataSource companyDataSource;
	private List<CompanyWrapper> companyWrapperList;
	private List<Company> companyList;
	private CompanyWrapper selectedCompanyWrapper;
	
	
			
			
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
    private QuarterDataSource quarterDataSource;
	private List<QuarterWrapper> quarterWrapperList;
	private List<Quarter> quarterList;
	private QuarterWrapper selectedQuarterWrapper;
	
	
			
			
			
		
			
			
			
	  	   
	
	
	private int editId=0;
	private boolean editMode=false;	
	
	
	
	
	
	

	public CreateFinancialHighlightBean() {

		financialHighlight = new FinancialHighlight();
		/* init datasources */
		financialHighlightDataSource = new FinancialHighlightDataSource();
		
		
			
fiscalYearDataSource = new FiscalYearDataSource();

		/* init option wrappers */
		fiscalYearList = fiscalYearDataSource.getAll();
		fiscalYearWrapperList = FiscalYearAdapter
				.wrapAll(fiscalYearList);
	
			
			
			
companyDataSource = new CompanyDataSource();

		/* init option wrappers */
		companyList = companyDataSource.getAll();
		companyWrapperList = CompanyAdapter
				.wrapAll(companyList);
	
			
			
			
quarterDataSource = new QuarterDataSource();

		/* init option wrappers */
		quarterList = quarterDataSource.getAll();
		quarterWrapperList = QuarterAdapter
				.wrapAll(quarterList);
	
			
				
		
		

	}
	
	@PostConstruct
	private void init()
	{
		extractParams();
		if(this.editMode)
		{
			this.financialHighlight=financialHighlightDataSource.get(editId);
			
			

			  
			  this.selectedFiscalYearWrapper=FiscalYearAdapter.wrap(financialHighlight.getFiscalYear());
	
			
			
			  
			  this.selectedCompanyWrapper=CompanyAdapter.wrap(financialHighlight.getCompany());
	
			
			
			  
			  this.selectedQuarterWrapper=QuarterAdapter.wrap(financialHighlight.getQuarter());
	
			
				   
			
			
			
			
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

	public FinancialHighlight getFinancialHighlight() {
		return financialHighlight;
	}

	public void setFinancialHighlight(FinancialHighlight financialHighlight) {
		this.financialHighlight = financialHighlight;
	}

	/* getters for datasources */
	public FinancialHighlightDataSource getFinancialHighlightDataSource() {
		return financialHighlightDataSource;
	}

	public void setFinancialHighlightDataSource(FinancialHighlightDataSource financialHighlightDataSource) {
		this.financialHighlightDataSource = financialHighlightDataSource;
	}
	
	
	
	
	
	
	
			


public List<FiscalYear> getFiscalYearList() {
		return fiscalYearList;
	}

	public void setFiscalYearList(List<FiscalYear> fiscalYearList) {
		this.fiscalYearList = fiscalYearList;
	}
  
  
  
	public FiscalYearDataSource getFiscalYearDataSource() {
		return fiscalYearDataSource;
	}

	public void setFiscalYearDataSource(
			FiscalYearDataSource fiscalYearDataSource) {
		this.fiscalYearDataSource = fiscalYearDataSource;
	}

	public List<FiscalYearWrapper> getFiscalYearWrapperList() {
		return fiscalYearWrapperList;
	}

	public void setFiscalYearWrapperList(
			List<FiscalYearWrapper> fiscalYearWrapperList) {
		this.fiscalYearWrapperList = fiscalYearWrapperList;
	}

	

	public FiscalYearWrapper getSelectedFiscalYearWrapper() {
		return selectedFiscalYearWrapper;
	}

	public void setSelectedFiscalYearWrapper(
			FiscalYearWrapper selectedFiscalYearWrapper) {
		this.selectedFiscalYearWrapper = selectedFiscalYearWrapper;
	}








			
			
			


public List<Company> getCompanyList() {
		return companyList;
	}

	public void setCompanyList(List<Company> companyList) {
		this.companyList = companyList;
	}
  
  
  
	public CompanyDataSource getCompanyDataSource() {
		return companyDataSource;
	}

	public void setCompanyDataSource(
			CompanyDataSource companyDataSource) {
		this.companyDataSource = companyDataSource;
	}

	public List<CompanyWrapper> getCompanyWrapperList() {
		return companyWrapperList;
	}

	public void setCompanyWrapperList(
			List<CompanyWrapper> companyWrapperList) {
		this.companyWrapperList = companyWrapperList;
	}

	

	public CompanyWrapper getSelectedCompanyWrapper() {
		return selectedCompanyWrapper;
	}

	public void setSelectedCompanyWrapper(
			CompanyWrapper selectedCompanyWrapper) {
		this.selectedCompanyWrapper = selectedCompanyWrapper;
	}








			
			
			


public List<Quarter> getQuarterList() {
		return quarterList;
	}

	public void setQuarterList(List<Quarter> quarterList) {
		this.quarterList = quarterList;
	}
  
  
  
	public QuarterDataSource getQuarterDataSource() {
		return quarterDataSource;
	}

	public void setQuarterDataSource(
			QuarterDataSource quarterDataSource) {
		this.quarterDataSource = quarterDataSource;
	}

	public List<QuarterWrapper> getQuarterWrapperList() {
		return quarterWrapperList;
	}

	public void setQuarterWrapperList(
			List<QuarterWrapper> quarterWrapperList) {
		this.quarterWrapperList = quarterWrapperList;
	}

	

	public QuarterWrapper getSelectedQuarterWrapper() {
		return selectedQuarterWrapper;
	}

	public void setSelectedQuarterWrapper(
			QuarterWrapper selectedQuarterWrapper) {
		this.selectedQuarterWrapper = selectedQuarterWrapper;
	}








			
				




	
  
  
  
	public FinancialHighlight saveFinancialHighlight() {
		try {

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();
			
			
			
                  FiscalYear fiscalYear =selectedFiscalYearWrapper.getFiscalYear();

			financialHighlight.setFiscalYear(fiscalYear);
			
			
			
                  Company company =selectedCompanyWrapper.getCompany();

			financialHighlight.setCompany(company);
			
			
			
                  Quarter quarter =selectedQuarterWrapper.getQuarter();

			financialHighlight.setQuarter(quarter);
			
				   
			
			
			
			
			financialHighlightDataSource.create(financialHighlight, session);
			tx.commit();
					MessageService.info("Successfully Saved  FinancialHighlight !");
				this.financialHighlight=new FinancialHighlight();
			return financialHighlight;

		} catch (Exception ex) {
		log.error(ex.getMessage());
			MessageService.error("Failed Saving FinancialHighlight .Try Again Later!");
			return null;
		}
	}
	
	public FinancialHighlight updateFinancialHighlight() {
		try {
		log.info("Starting to update....");

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();
			
			
			
                  FiscalYear fiscalYear = selectedFiscalYearWrapper.getFiscalYear();

			      financialHighlight.setFiscalYear(fiscalYear);
			
			
			
                  Company company = selectedCompanyWrapper.getCompany();

			      financialHighlight.setCompany(company);
			
			
			
                  Quarter quarter = selectedQuarterWrapper.getQuarter();

			      financialHighlight.setQuarter(quarter);
			
				   
			
			
			
			
			financialHighlightDataSource.create(financialHighlight, session);
			tx.commit();
				MessageService.info("Successfully Saved  FinancialHighlight !");
				this.financialHighlight=new FinancialHighlight();
			return financialHighlight;

		} catch (Exception ex) {
			MessageService.error("Failed Saving FinancialHighlight .Try Again Later!");
			log.error(ex.getMessage());
			return null;
		}
	}
	
	public void saveOrUpdate(){
	
	if(this.editMode)
		{
		log.info("Updating value");
			updateFinancialHighlight();
		}else{
		log.info("Creating value");
			saveFinancialHighlight();
		}
	}
	public void cancel()
	{
	    RequestContext.getCurrentInstance().closeDialog("createFinancialHighlight");
		
	}
	public FinancialHighlight saveFinancialHighlight(Session session){
	
	   this.financialHighlight= financialHighlightDataSource.create(this.financialHighlight,session);
	   return this.financialHighlight;
	}
	

}

