
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

import com.nsmjsf.web.datasources.NewLowDataSource;
import com.nsmjsf.web.datamodels.NewLow;
import com.nsmjsf.web.lazymodels.LazyNewLowDataModel;


			
import com.nsmjsf.web.adapters.CompanyAdapter;


import com.nsmjsf.web.datasources.CompanyDataSource;

import com.nsmjsf.web.datamodels.Company;

import com.nsmjsf.web.wrappers.CompanyWrapper;



			
				   
@ManagedBean
@ViewScoped
public class ViewNewLowBean implements Serializable {
private static final Log log = LogFactory.getLog(ViewNewLowBean.class);
 ViewType viewType=ViewType.DATATABLE;
 

	List<NewLow> newLowList;
    List<NewLow> selectedNewLowList;
	List<NewLow> filteredNewLowList;
	NewLow selectedNewLow;
	LazyDataModel<NewLow> lazyModel;
	NewLowDataSource newLowDataSource;
	int editNewLowId=0;
	

			   List<Company> companyList;
			   CompanyDataSource companyDataSource;
			   public List<Company> getCompanyList() {
		return companyList;
	     }
	public void setCompanyList(List<Company> companyList) {
		this.companyList = companyList;
	}
			
				   
	
	
	public ViewNewLowBean()
	{
		this.initDataSources();
		this.populateData();
		
		lazyModel=new LazyNewLowDataModel(this.newLowList);
		
	}
	
	
	private void initDataSources()
	{
		newLowDataSource=new NewLowDataSource();
		

			  companyDataSource=new CompanyDataSource();
			
				   
	
		
	}
	
	public void refreshDataSource(){
		this.newLowList=newLowDataSource.getAll();
		lazyModel=new LazyNewLowDataModel(this.newLowList);
		
	}
	
	
	private void populateData()
	{
		newLowList=newLowDataSource.getAll();
		

			 companyList=companyDataSource.getAll();
	
				   
	
		
			}
	public List<NewLow> getNewLowList() {
		return newLowList;
	}
	public void setNewLowList(List<NewLow> newLowList) {
		this.newLowList = newLowList;
	}
	public LazyDataModel<NewLow> getLazyModel() {
		return lazyModel;
	}
	public void setLazyModel(LazyDataModel<NewLow> lazyModel) {
		this.lazyModel = lazyModel;
	}
	public NewLow getSelectedNewLow() {
		return selectedNewLow;
	}
	public void setSelectedNewLow(NewLow selectedNewLow) {
		this.selectedNewLow = selectedNewLow;
	}
	
	public List<NewLow> getSelectedNewLowList() {
		return selectedNewLowList;
	}

	public void setSelectedNewLowList(
			List<NewLow> selectedNewLowList) {
		this.selectedNewLowList = selectedNewLowList;
	}

	public List<NewLow> getFilteredNewLowList() {
		return filteredNewLowList;
	}

	public void setFilteredNewLowList(
			List<NewLow> filteredNewLowList) {
		this.filteredNewLowList = filteredNewLowList;
	}

	public void newNewLow() {
		System.out.println("Opening dialogue");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);

		RequestContext.getCurrentInstance().openDialog("createNewLow",
				options, null);

	}
	
	public void onRowSelect(SelectEvent event) {
		System.out.println("NewLow Selected"
				+ ((NewLow) event.getObject()).getNewLowId());
		for (NewLow cat : selectedNewLowList) {
			//System.out.println(cat.getNewLowLabel());
		}

	}

	public void onRowUnselect(UnselectEvent event) {
		System.out.println("Car unSelected"
				+ ((NewLow) event.getObject()).getNewLowId());

	}

	public void deleteSelectedNewLow() {
		for (NewLow newLow : selectedNewLowList) {
			//System.out.println(newLow.getNewLowLabel());
			this.deleteNewLow(newLow);
		}
	}
	public void deleteNewLow(NewLow newLow) {
			try{
			newLowDataSource.delete(newLow);
			this.refreshDataSource();
			}catch(Exception ex)
			{
				log.info(ex.getMessage());
			}
		
	}
/*----------------------------------------*/
	public int getEditNewLowId() {
		return editNewLowId;
	}

	public void setEditNewLowId(int editNewLowId) {
		this.editNewLowId = editNewLowId;
	}
	
	public void editNewLow(int editId)
	{
		Map<String,List<String>> params = new HashMap<String,List<String>>();
		Map<String,Object> options = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		String seditId=String.valueOf(editId);
		options.put("modal", true);
		list.add(seditId);
		params.put("editId", list);
		RequestContext.getCurrentInstance().openDialog("createNewLow",
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



