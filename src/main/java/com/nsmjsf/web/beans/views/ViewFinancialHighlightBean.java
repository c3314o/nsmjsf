
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

import com.nsmjsf.web.datasources.FinancialHighlightDataSource;
import com.nsmjsf.web.datamodels.FinancialHighlight;
import com.nsmjsf.web.lazymodels.LazyFinancialHighlightDataModel;


			
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
public class ViewFinancialHighlightBean implements Serializable {
private static final Log log = LogFactory.getLog(ViewFinancialHighlightBean.class);
 ViewType viewType=ViewType.DATATABLE;
 

	List<FinancialHighlight> financialHighlightList;
    List<FinancialHighlight> selectedFinancialHighlightList;
	List<FinancialHighlight> filteredFinancialHighlightList;
	FinancialHighlight selectedFinancialHighlight;
	LazyDataModel<FinancialHighlight> lazyModel;
	FinancialHighlightDataSource financialHighlightDataSource;
	int editFinancialHighlightId=0;
	

			   List<FiscalYear> fiscalYearList;
			   FiscalYearDataSource fiscalYearDataSource;
			   public List<FiscalYear> getFiscalYearList() {
		return fiscalYearList;
	     }
	public void setFiscalYearList(List<FiscalYear> fiscalYearList) {
		this.fiscalYearList = fiscalYearList;
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
			
				   
	
	
	public ViewFinancialHighlightBean()
	{
		this.initDataSources();
		this.populateData();
		
		lazyModel=new LazyFinancialHighlightDataModel(this.financialHighlightList);
		
	}
	
	
	private void initDataSources()
	{
		financialHighlightDataSource=new FinancialHighlightDataSource();
		

			  fiscalYearDataSource=new FiscalYearDataSource();
			
			
			  companyDataSource=new CompanyDataSource();
			
			
			  quarterDataSource=new QuarterDataSource();
			
				   
	
		
	}
	
	public void refreshDataSource(){
		this.financialHighlightList=financialHighlightDataSource.getAll();
		lazyModel=new LazyFinancialHighlightDataModel(this.financialHighlightList);
		
	}
	
	
	private void populateData()
	{
		financialHighlightList=financialHighlightDataSource.getAll();
		

			 fiscalYearList=fiscalYearDataSource.getAll();
	
			
			 companyList=companyDataSource.getAll();
	
			
			 quarterList=quarterDataSource.getAll();
	
				   
	
		
			}
	public List<FinancialHighlight> getFinancialHighlightList() {
		return financialHighlightList;
	}
	public void setFinancialHighlightList(List<FinancialHighlight> financialHighlightList) {
		this.financialHighlightList = financialHighlightList;
	}
	public LazyDataModel<FinancialHighlight> getLazyModel() {
		return lazyModel;
	}
	public void setLazyModel(LazyDataModel<FinancialHighlight> lazyModel) {
		this.lazyModel = lazyModel;
	}
	public FinancialHighlight getSelectedFinancialHighlight() {
		return selectedFinancialHighlight;
	}
	public void setSelectedFinancialHighlight(FinancialHighlight selectedFinancialHighlight) {
		this.selectedFinancialHighlight = selectedFinancialHighlight;
	}
	
	public List<FinancialHighlight> getSelectedFinancialHighlightList() {
		return selectedFinancialHighlightList;
	}

	public void setSelectedFinancialHighlightList(
			List<FinancialHighlight> selectedFinancialHighlightList) {
		this.selectedFinancialHighlightList = selectedFinancialHighlightList;
	}

	public List<FinancialHighlight> getFilteredFinancialHighlightList() {
		return filteredFinancialHighlightList;
	}

	public void setFilteredFinancialHighlightList(
			List<FinancialHighlight> filteredFinancialHighlightList) {
		this.filteredFinancialHighlightList = filteredFinancialHighlightList;
	}

	public void newFinancialHighlight() {
		System.out.println("Opening dialogue");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);

		RequestContext.getCurrentInstance().openDialog("createFinancialHighlight",
				options, null);

	}
	
	public void onRowSelect(SelectEvent event) {
		System.out.println("FinancialHighlight Selected"
				+ ((FinancialHighlight) event.getObject()).getFinancialHighlightId());
		for (FinancialHighlight cat : selectedFinancialHighlightList) {
			//System.out.println(cat.getFinancialHighlightLabel());
		}

	}

	public void onRowUnselect(UnselectEvent event) {
		System.out.println("Car unSelected"
				+ ((FinancialHighlight) event.getObject()).getFinancialHighlightId());

	}

	public void deleteSelectedFinancialHighlight() {
		for (FinancialHighlight financialHighlight : selectedFinancialHighlightList) {
			//System.out.println(financialHighlight.getFinancialHighlightLabel());
			this.deleteFinancialHighlight(financialHighlight);
		}
	}
	public void deleteFinancialHighlight(FinancialHighlight financialHighlight) {
			try{
			financialHighlightDataSource.delete(financialHighlight);
			this.refreshDataSource();
			}catch(Exception ex)
			{
				log.info(ex.getMessage());
			}
		
	}
/*----------------------------------------*/
	public int getEditFinancialHighlightId() {
		return editFinancialHighlightId;
	}

	public void setEditFinancialHighlightId(int editFinancialHighlightId) {
		this.editFinancialHighlightId = editFinancialHighlightId;
	}
	
	public void editFinancialHighlight(int editId)
	{
		Map<String,List<String>> params = new HashMap<String,List<String>>();
		Map<String,Object> options = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		String seditId=String.valueOf(editId);
		options.put("modal", true);
		list.add(seditId);
		params.put("editId", list);
		RequestContext.getCurrentInstance().openDialog("createFinancialHighlight",
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



