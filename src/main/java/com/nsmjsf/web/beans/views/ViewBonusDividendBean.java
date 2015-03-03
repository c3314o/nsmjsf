
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

import com.nsmjsf.web.datasources.BonusDividendDataSource;
import com.nsmjsf.web.datamodels.BonusDividend;
import com.nsmjsf.web.lazymodels.LazyBonusDividendDataModel;


			
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
public class ViewBonusDividendBean implements Serializable {
private static final Log log = LogFactory.getLog(ViewBonusDividendBean.class);
 ViewType viewType=ViewType.DATATABLE;
 

	List<BonusDividend> bonusDividendList;
    List<BonusDividend> selectedBonusDividendList;
	List<BonusDividend> filteredBonusDividendList;
	BonusDividend selectedBonusDividend;
	LazyDataModel<BonusDividend> lazyModel;
	BonusDividendDataSource bonusDividendDataSource;
	int editBonusDividendId=0;
	

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
			
				   
	
	
	public ViewBonusDividendBean()
	{
		this.initDataSources();
		this.populateData();
		
		lazyModel=new LazyBonusDividendDataModel(this.bonusDividendList);
		
	}
	
	
	private void initDataSources()
	{
		bonusDividendDataSource=new BonusDividendDataSource();
		

			  fiscalYearDataSource=new FiscalYearDataSource();
			
			
			  companyDataSource=new CompanyDataSource();
			
				   
	
		
	}
	
	public void refreshDataSource(){
		this.bonusDividendList=bonusDividendDataSource.getAll();
		lazyModel=new LazyBonusDividendDataModel(this.bonusDividendList);
		
	}
	
	
	private void populateData()
	{
		bonusDividendList=bonusDividendDataSource.getAll();
		

			 fiscalYearList=fiscalYearDataSource.getAll();
	
			
			 companyList=companyDataSource.getAll();
	
				   
	
		
			}
	public List<BonusDividend> getBonusDividendList() {
		return bonusDividendList;
	}
	public void setBonusDividendList(List<BonusDividend> bonusDividendList) {
		this.bonusDividendList = bonusDividendList;
	}
	public LazyDataModel<BonusDividend> getLazyModel() {
		return lazyModel;
	}
	public void setLazyModel(LazyDataModel<BonusDividend> lazyModel) {
		this.lazyModel = lazyModel;
	}
	public BonusDividend getSelectedBonusDividend() {
		return selectedBonusDividend;
	}
	public void setSelectedBonusDividend(BonusDividend selectedBonusDividend) {
		this.selectedBonusDividend = selectedBonusDividend;
	}
	
	public List<BonusDividend> getSelectedBonusDividendList() {
		return selectedBonusDividendList;
	}

	public void setSelectedBonusDividendList(
			List<BonusDividend> selectedBonusDividendList) {
		this.selectedBonusDividendList = selectedBonusDividendList;
	}

	public List<BonusDividend> getFilteredBonusDividendList() {
		return filteredBonusDividendList;
	}

	public void setFilteredBonusDividendList(
			List<BonusDividend> filteredBonusDividendList) {
		this.filteredBonusDividendList = filteredBonusDividendList;
	}

	public void newBonusDividend() {
		System.out.println("Opening dialogue");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);

		RequestContext.getCurrentInstance().openDialog("createBonusDividend",
				options, null);

	}
	
	public void onRowSelect(SelectEvent event) {
		System.out.println("BonusDividend Selected"
				+ ((BonusDividend) event.getObject()).getBonusDividendId());
		for (BonusDividend cat : selectedBonusDividendList) {
			//System.out.println(cat.getBonusDividendLabel());
		}

	}

	public void onRowUnselect(UnselectEvent event) {
		System.out.println("Car unSelected"
				+ ((BonusDividend) event.getObject()).getBonusDividendId());

	}

	public void deleteSelectedBonusDividend() {
		for (BonusDividend bonusDividend : selectedBonusDividendList) {
			//System.out.println(bonusDividend.getBonusDividendLabel());
			this.deleteBonusDividend(bonusDividend);
		}
	}
	public void deleteBonusDividend(BonusDividend bonusDividend) {
			try{
			bonusDividendDataSource.delete(bonusDividend);
			this.refreshDataSource();
			}catch(Exception ex)
			{
				log.info(ex.getMessage());
			}
		
	}
/*----------------------------------------*/
	public int getEditBonusDividendId() {
		return editBonusDividendId;
	}

	public void setEditBonusDividendId(int editBonusDividendId) {
		this.editBonusDividendId = editBonusDividendId;
	}
	
	public void editBonusDividend(int editId)
	{
		Map<String,List<String>> params = new HashMap<String,List<String>>();
		Map<String,Object> options = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		String seditId=String.valueOf(editId);
		options.put("modal", true);
		list.add(seditId);
		params.put("editId", list);
		RequestContext.getCurrentInstance().openDialog("createBonusDividend",
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



