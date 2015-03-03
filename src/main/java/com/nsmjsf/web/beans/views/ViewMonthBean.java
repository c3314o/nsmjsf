
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

import com.nsmjsf.web.datasources.MonthDataSource;
import com.nsmjsf.web.datamodels.Month;
import com.nsmjsf.web.lazymodels.LazyMonthDataModel;


			
import com.nsmjsf.web.adapters.MonthlyFinancialHighlightAdapter;


import com.nsmjsf.web.datasources.MonthlyFinancialHighlightDataSource;

import com.nsmjsf.web.datamodels.MonthlyFinancialHighlight;

import com.nsmjsf.web.wrappers.MonthlyFinancialHighlightWrapper;



			
				   
@ManagedBean
@ViewScoped
public class ViewMonthBean implements Serializable {
private static final Log log = LogFactory.getLog(ViewMonthBean.class);
 ViewType viewType=ViewType.DATATABLE;
 

	List<Month> monthList;
    List<Month> selectedMonthList;
	List<Month> filteredMonthList;
	Month selectedMonth;
	LazyDataModel<Month> lazyModel;
	MonthDataSource monthDataSource;
	int editMonthId=0;
	
	   
	
	
	public ViewMonthBean()
	{
		this.initDataSources();
		this.populateData();
		
		lazyModel=new LazyMonthDataModel(this.monthList);
		
	}
	
	
	private void initDataSources()
	{
		monthDataSource=new MonthDataSource();
		
	   
	
		
	}
	
	public void refreshDataSource(){
		this.monthList=monthDataSource.getAll();
		lazyModel=new LazyMonthDataModel(this.monthList);
		
	}
	
	
	private void populateData()
	{
		monthList=monthDataSource.getAll();
		
	   
	
		
			}
	public List<Month> getMonthList() {
		return monthList;
	}
	public void setMonthList(List<Month> monthList) {
		this.monthList = monthList;
	}
	public LazyDataModel<Month> getLazyModel() {
		return lazyModel;
	}
	public void setLazyModel(LazyDataModel<Month> lazyModel) {
		this.lazyModel = lazyModel;
	}
	public Month getSelectedMonth() {
		return selectedMonth;
	}
	public void setSelectedMonth(Month selectedMonth) {
		this.selectedMonth = selectedMonth;
	}
	
	public List<Month> getSelectedMonthList() {
		return selectedMonthList;
	}

	public void setSelectedMonthList(
			List<Month> selectedMonthList) {
		this.selectedMonthList = selectedMonthList;
	}

	public List<Month> getFilteredMonthList() {
		return filteredMonthList;
	}

	public void setFilteredMonthList(
			List<Month> filteredMonthList) {
		this.filteredMonthList = filteredMonthList;
	}

	public void newMonth() {
		System.out.println("Opening dialogue");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);

		RequestContext.getCurrentInstance().openDialog("createMonth",
				options, null);

	}
	
	public void onRowSelect(SelectEvent event) {
		System.out.println("Month Selected"
				+ ((Month) event.getObject()).getMonthId());
		for (Month cat : selectedMonthList) {
			//System.out.println(cat.getMonthLabel());
		}

	}

	public void onRowUnselect(UnselectEvent event) {
		System.out.println("Car unSelected"
				+ ((Month) event.getObject()).getMonthId());

	}

	public void deleteSelectedMonth() {
		for (Month month : selectedMonthList) {
			//System.out.println(month.getMonthLabel());
			this.deleteMonth(month);
		}
	}
	public void deleteMonth(Month month) {
			try{
			monthDataSource.delete(month);
			this.refreshDataSource();
			}catch(Exception ex)
			{
				log.info(ex.getMessage());
			}
		
	}
/*----------------------------------------*/
	public int getEditMonthId() {
		return editMonthId;
	}

	public void setEditMonthId(int editMonthId) {
		this.editMonthId = editMonthId;
	}
	
	public void editMonth(int editId)
	{
		Map<String,List<String>> params = new HashMap<String,List<String>>();
		Map<String,Object> options = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		String seditId=String.valueOf(editId);
		options.put("modal", true);
		list.add(seditId);
		params.put("editId", list);
		RequestContext.getCurrentInstance().openDialog("createMonth",
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



