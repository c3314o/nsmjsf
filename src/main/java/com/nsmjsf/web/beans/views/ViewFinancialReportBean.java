
package com.nsmjsf.web.beans.views;

import java.io.Serializable;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.model.LazyDataModel;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.context.RequestContext;

import com.nsmjsf.web.datasources.FinancialReportDataSource;
import com.nsmjsf.web.datamodels.FinancialReport;
import com.nsmjsf.web.lazymodels.LazyFinancialReportDataModel;


			
import com.nsmjsf.web.adapters.FiscalYearAdapter;


import com.nsmjsf.web.datasources.FiscalYearDataSource;

import com.nsmjsf.web.datamodels.FiscalYear;

import com.nsmjsf.web.wrappers.FiscalYearWrapper;



			
			
			
import com.nsmjsf.web.adapters.AuditStatusAdapter;


import com.nsmjsf.web.datasources.AuditStatusDataSource;

import com.nsmjsf.web.datamodels.AuditStatus;

import com.nsmjsf.web.wrappers.AuditStatusWrapper;



			
			
			
import com.nsmjsf.web.adapters.BalancesheetAdapter;


import com.nsmjsf.web.datasources.BalancesheetDataSource;

import com.nsmjsf.web.datamodels.Balancesheet;

import com.nsmjsf.web.wrappers.BalancesheetWrapper;



			
			
			
import com.nsmjsf.web.adapters.PostAdapter;


import com.nsmjsf.web.datasources.PostDataSource;

import com.nsmjsf.web.datamodels.Post;

import com.nsmjsf.web.wrappers.PostWrapper;



			
			
			
import com.nsmjsf.web.adapters.CompanyAdapter;


import com.nsmjsf.web.datasources.CompanyDataSource;

import com.nsmjsf.web.datamodels.Company;

import com.nsmjsf.web.wrappers.CompanyWrapper;



			
			
			
import com.nsmjsf.web.adapters.KeyRatioAdapter;


import com.nsmjsf.web.datasources.KeyRatioDataSource;

import com.nsmjsf.web.datamodels.KeyRatio;

import com.nsmjsf.web.wrappers.KeyRatioWrapper;



			
			
			
import com.nsmjsf.web.adapters.ProfitLossAdapter;


import com.nsmjsf.web.datasources.ProfitLossDataSource;

import com.nsmjsf.web.datamodels.ProfitLoss;

import com.nsmjsf.web.wrappers.ProfitLossWrapper;



			
			
			
import com.nsmjsf.web.adapters.QuarterAdapter;


import com.nsmjsf.web.datasources.QuarterDataSource;

import com.nsmjsf.web.datamodels.Quarter;

import com.nsmjsf.web.wrappers.QuarterWrapper;



			
				   
@ManagedBean
@ViewScoped
public class ViewFinancialReportBean implements Serializable {
private static final Log log = LogFactory.getLog(ViewFinancialReportBean.class);
 ViewType viewType=ViewType.DATATABLE;
 

	List<FinancialReport> financialReportList;
    List<FinancialReport> selectedFinancialReportList;
	List<FinancialReport> filteredFinancialReportList;
	FinancialReport selectedFinancialReport;
	LazyDataModel<FinancialReport> lazyModel;
	FinancialReportDataSource financialReportDataSource;
	int editFinancialReportId=0;
	

			   List<FiscalYear> fiscalYearList;
			   FiscalYearDataSource fiscalYearDataSource;
			   public List<FiscalYear> getFiscalYearList() {
		return fiscalYearList;
	     }
	public void setFiscalYearList(List<FiscalYear> fiscalYearList) {
		this.fiscalYearList = fiscalYearList;
	}
			
			
			   List<AuditStatus> auditStatusList;
			   AuditStatusDataSource auditStatusDataSource;
			   public List<AuditStatus> getAuditStatusList() {
		return auditStatusList;
	     }
	public void setAuditStatusList(List<AuditStatus> auditStatusList) {
		this.auditStatusList = auditStatusList;
	}
			
			
			   List<Post> postList;
			   PostDataSource postDataSource;
			   public List<Post> getPostList() {
		return postList;
	     }
	public void setPostList(List<Post> postList) {
		this.postList = postList;
	}
			
			
			   List<Company> companyList;
			   CompanyDataSource companyDataSource;
			   public List<Company> getCompanyList() {
		return companyList;
	     }
	public void setCompanyList(List<Company> companyList) {
		this.companyList = companyList;
	}
			
			
			   List<Quarter> quarterList;
			   QuarterDataSource quarterDataSource;
			   public List<Quarter> getQuarterList() {
		return quarterList;
	     }
	public void setQuarterList(List<Quarter> quarterList) {
		this.quarterList = quarterList;
	}
			
				   
	
	
	public ViewFinancialReportBean()
	{
		this.initDataSources();
		this.populateData();
		
		lazyModel=new LazyFinancialReportDataModel(this.financialReportList);
		
	}
	
	
	private void initDataSources()
	{
		financialReportDataSource=new FinancialReportDataSource();
		

			  fiscalYearDataSource=new FiscalYearDataSource();
			
			
			  auditStatusDataSource=new AuditStatusDataSource();
			
			
			  postDataSource=new PostDataSource();
			
			
			  companyDataSource=new CompanyDataSource();
			
			
			  quarterDataSource=new QuarterDataSource();
			
				   
	
		
	}
	
	public void refreshDataSource(){
		this.financialReportList=financialReportDataSource.getAll();
		lazyModel=new LazyFinancialReportDataModel(this.financialReportList);
		
	}
	
	
	private void populateData()
	{
		financialReportList=financialReportDataSource.getAll();
		

			 fiscalYearList=fiscalYearDataSource.getAll();
	
			
			 auditStatusList=auditStatusDataSource.getAll();
	
			
			 postList=postDataSource.getAll();
	
			
			 companyList=companyDataSource.getAll();
	
			
			 quarterList=quarterDataSource.getAll();
	
				   
	
		
			}
	public List<FinancialReport> getFinancialReportList() {
		return financialReportList;
	}
	public void setFinancialReportList(List<FinancialReport> financialReportList) {
		this.financialReportList = financialReportList;
	}
	public LazyDataModel<FinancialReport> getLazyModel() {
		return lazyModel;
	}
	public void setLazyModel(LazyDataModel<FinancialReport> lazyModel) {
		this.lazyModel = lazyModel;
	}
	public FinancialReport getSelectedFinancialReport() {
		return selectedFinancialReport;
	}
	public void setSelectedFinancialReport(FinancialReport selectedFinancialReport) {
		this.selectedFinancialReport = selectedFinancialReport;
	}
	
	public List<FinancialReport> getSelectedFinancialReportList() {
		return selectedFinancialReportList;
	}

	public void setSelectedFinancialReportList(
			List<FinancialReport> selectedFinancialReportList) {
		this.selectedFinancialReportList = selectedFinancialReportList;
	}

	public List<FinancialReport> getFilteredFinancialReportList() {
		return filteredFinancialReportList;
	}

	public void setFilteredFinancialReportList(
			List<FinancialReport> filteredFinancialReportList) {
		this.filteredFinancialReportList = filteredFinancialReportList;
	}

	public void newFinancialReport() {
		System.out.println("Opening dialogue");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);

		RequestContext.getCurrentInstance().openDialog("createFinancialReport",
				options, null);

	}
	
	public void onRowSelect(SelectEvent event) {
		System.out.println("FinancialReport Selected"
				+ ((FinancialReport) event.getObject()).getFinancialReportId());
		for (FinancialReport cat : selectedFinancialReportList) {
			//System.out.println(cat.getFinancialReportLabel());
		}

	}

	public void onRowUnselect(UnselectEvent event) {
		System.out.println("Car unSelected"
				+ ((FinancialReport) event.getObject()).getFinancialReportId());

	}

	public void deleteSelectedFinancialReport() {
		for (FinancialReport financialReport : selectedFinancialReportList) {
			//System.out.println(financialReport.getFinancialReportLabel());
			this.deleteFinancialReport(financialReport);
		}
	}
	public void deleteFinancialReport(FinancialReport financialReport) {
			try{
			financialReportDataSource.delete(financialReport);
			this.refreshDataSource();
			}catch(Exception ex)
			{
				log.info(ex.getMessage());
			}
		
	}
/*----------------------------------------*/
	public int getEditFinancialReportId() {
		return editFinancialReportId;
	}

	public void setEditFinancialReportId(int editFinancialReportId) {
		this.editFinancialReportId = editFinancialReportId;
	}
	
	public void editFinancialReport(int editId)
	{
		Map<String,List<String>> params = new HashMap<String,List<String>>();
		Map<String,Object> options = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		String seditId=String.valueOf(editId);
		options.put("modal", true);
		list.add(seditId);
		params.put("editId", list);
		RequestContext.getCurrentInstance().openDialog("createFinancialReport",
				options,params);
	}

	public ViewType getViewType() {
		return viewType;
	}

	public void setViewType(ViewType viewType) {
		this.viewType = viewType;
	}

	public boolean isDataGrid()
	{
		return this.viewType==ViewType.DATAGRID;
	}
	public boolean isDataTable()
	{
		return this.viewType==ViewType.DATATABLE;
	}
	public boolean isDataScroller()
	{
		return this.viewType==ViewType.DATASCROLLER;
	}
	public boolean isDataTableLive()
	{
		return this.viewType==ViewType.DATATABLELIVE;
	}
	
	public void toDataTable()
	{
		this.viewType=ViewType.DATATABLE;
	}
	public void toDataGrid()
	{
		this.viewType=ViewType.DATAGRID;
	}
	public void toDataScroll()
	{
		this.viewType=ViewType.DATASCROLLER;
	}
	

}



