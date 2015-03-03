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
import com.nsmjsf.web.datasources.BonusDividendDataSource;
import com.nsmjsf.web.datamodels.BonusDividend;
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



			
				   

@ManagedBean
@ViewScoped

public class CreateBonusDividendBean implements Serializable {

private static final Log log = LogFactory
			.getLog(CreateBonusDividendBean.class);


	private BonusDividend bonusDividend;
	private BonusDividendDataSource bonusDividendDataSource;
	
	
	
			
		
			
			
			
	  
			
    private FiscalYearDataSource fiscalYearDataSource;
	private List<FiscalYearWrapper> fiscalYearWrapperList;
	private List<FiscalYear> fiscalYearList;
	private FiscalYearWrapper selectedFiscalYearWrapper;
	
	
			
			
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
    private CompanyDataSource companyDataSource;
	private List<CompanyWrapper> companyWrapperList;
	private List<Company> companyList;
	private CompanyWrapper selectedCompanyWrapper;
	
	
			
			
			
		
			
			
			
	  	   
	
	
	private int editId=0;
	private boolean editMode=false;	
	
	
	
	
	
	

	public CreateBonusDividendBean() {

		bonusDividend = new BonusDividend();
		/* init datasources */
		bonusDividendDataSource = new BonusDividendDataSource();
		
		
			
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
	
			
				
		
		

	}
	
	@PostConstruct
	private void init()
	{
		extractParams();
		if(this.editMode)
		{
			this.bonusDividend=bonusDividendDataSource.get(editId);
			
			

			  
			  this.selectedFiscalYearWrapper=FiscalYearAdapter.wrap(bonusDividend.getFiscalYear());
	
			
			
			  
			  this.selectedCompanyWrapper=CompanyAdapter.wrap(bonusDividend.getCompany());
	
			
				   
			
			
			
			
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

	public BonusDividend getBonusDividend() {
		return bonusDividend;
	}

	public void setBonusDividend(BonusDividend bonusDividend) {
		this.bonusDividend = bonusDividend;
	}

	/* getters for datasources */
	public BonusDividendDataSource getBonusDividendDataSource() {
		return bonusDividendDataSource;
	}

	public void setBonusDividendDataSource(BonusDividendDataSource bonusDividendDataSource) {
		this.bonusDividendDataSource = bonusDividendDataSource;
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








			
				




	
  
  
  
	public BonusDividend saveBonusDividend() {
		try {

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();
			
			
			
                  FiscalYear fiscalYear =selectedFiscalYearWrapper.getFiscalYear();

			bonusDividend.setFiscalYear(fiscalYear);
			
			
			
                  Company company =selectedCompanyWrapper.getCompany();

			bonusDividend.setCompany(company);
			
				   
			
			
			
			
			bonusDividendDataSource.create(bonusDividend, session);
			tx.commit();
					MessageService.info("Successfully Saved  BonusDividend !");
				this.bonusDividend=new BonusDividend();
			return bonusDividend;

		} catch (Exception ex) {
		log.error(ex.getMessage());
			MessageService.error("Failed Saving BonusDividend .Try Again Later!");
			return null;
		}
	}
	
	public BonusDividend updateBonusDividend() {
		try {
		log.info("Starting to update....");

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();
			
			
			
                  FiscalYear fiscalYear = selectedFiscalYearWrapper.getFiscalYear();

			      bonusDividend.setFiscalYear(fiscalYear);
			
			
			
                  Company company = selectedCompanyWrapper.getCompany();

			      bonusDividend.setCompany(company);
			
				   
			
			
			
			
			bonusDividendDataSource.create(bonusDividend, session);
			tx.commit();
				MessageService.info("Successfully Saved  BonusDividend !");
				this.bonusDividend=new BonusDividend();
			return bonusDividend;

		} catch (Exception ex) {
			MessageService.error("Failed Saving BonusDividend .Try Again Later!");
			log.error(ex.getMessage());
			return null;
		}
	}
	
	public void saveOrUpdate(){
	
	if(this.editMode)
		{
		log.info("Updating value");
			updateBonusDividend();
		}else{
		log.info("Creating value");
			saveBonusDividend();
		}
	}
	public void cancel()
	{
	    RequestContext.getCurrentInstance().closeDialog("createBonusDividend");
		
	}
	public BonusDividend saveBonusDividend(Session session){
	
	   this.bonusDividend= bonusDividendDataSource.create(this.bonusDividend,session);
	   return this.bonusDividend;
	}
	

}

