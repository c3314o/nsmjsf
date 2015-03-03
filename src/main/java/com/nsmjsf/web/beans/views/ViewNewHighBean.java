
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

import com.nsmjsf.web.datasources.NewHighDataSource;
import com.nsmjsf.web.datamodels.NewHigh;
import com.nsmjsf.web.lazymodels.LazyNewHighDataModel;


			
import com.nsmjsf.web.adapters.CompanyAdapter;


import com.nsmjsf.web.datasources.CompanyDataSource;

import com.nsmjsf.web.datamodels.Company;

import com.nsmjsf.web.wrappers.CompanyWrapper;



			
				   
@ManagedBean
@ViewScoped
public class ViewNewHighBean implements Serializable {
private static final Log log = LogFactory.getLog(ViewNewHighBean.class);
 ViewType viewType=ViewType.DATATABLE;
 

	List<NewHigh> newHighList;
    List<NewHigh> selectedNewHighList;
	List<NewHigh> filteredNewHighList;
	NewHigh selectedNewHigh;
	LazyDataModel<NewHigh> lazyModel;
	NewHighDataSource newHighDataSource;
	int editNewHighId=0;
	

			   List<Company> companyList;
			   CompanyDataSource companyDataSource;
			   public List<Company> getCompanyList() {
		return companyList;
	     }
	public void setCompanyList(List<Company> companyList) {
		this.companyList = companyList;
	}
			
				   
	
	
	public ViewNewHighBean()
	{
		this.initDataSources();
		this.populateData();
		
		lazyModel=new LazyNewHighDataModel(this.newHighList);
		
	}
	
	
	private void initDataSources()
	{
		newHighDataSource=new NewHighDataSource();
		

			  companyDataSource=new CompanyDataSource();
			
				   
	
		
	}
	
	public void refreshDataSource(){
		this.newHighList=newHighDataSource.getAll();
		lazyModel=new LazyNewHighDataModel(this.newHighList);
		
	}
	
	
	private void populateData()
	{
		newHighList=newHighDataSource.getAll();
		

			 companyList=companyDataSource.getAll();
	
				   
	
		
			}
	public List<NewHigh> getNewHighList() {
		return newHighList;
	}
	public void setNewHighList(List<NewHigh> newHighList) {
		this.newHighList = newHighList;
	}
	public LazyDataModel<NewHigh> getLazyModel() {
		return lazyModel;
	}
	public void setLazyModel(LazyDataModel<NewHigh> lazyModel) {
		this.lazyModel = lazyModel;
	}
	public NewHigh getSelectedNewHigh() {
		return selectedNewHigh;
	}
	public void setSelectedNewHigh(NewHigh selectedNewHigh) {
		this.selectedNewHigh = selectedNewHigh;
	}
	
	public List<NewHigh> getSelectedNewHighList() {
		return selectedNewHighList;
	}

	public void setSelectedNewHighList(
			List<NewHigh> selectedNewHighList) {
		this.selectedNewHighList = selectedNewHighList;
	}

	public List<NewHigh> getFilteredNewHighList() {
		return filteredNewHighList;
	}

	public void setFilteredNewHighList(
			List<NewHigh> filteredNewHighList) {
		this.filteredNewHighList = filteredNewHighList;
	}

	public void newNewHigh() {
		System.out.println("Opening dialogue");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);

		RequestContext.getCurrentInstance().openDialog("createNewHigh",
				options, null);

	}
	
	public void onRowSelect(SelectEvent event) {
		System.out.println("NewHigh Selected"
				+ ((NewHigh) event.getObject()).getNewHighId());
		for (NewHigh cat : selectedNewHighList) {
			//System.out.println(cat.getNewHighLabel());
		}

	}

	public void onRowUnselect(UnselectEvent event) {
		System.out.println("Car unSelected"
				+ ((NewHigh) event.getObject()).getNewHighId());

	}

	public void deleteSelectedNewHigh() {
		for (NewHigh newHigh : selectedNewHighList) {
			//System.out.println(newHigh.getNewHighLabel());
			this.deleteNewHigh(newHigh);
		}
	}
	public void deleteNewHigh(NewHigh newHigh) {
			try{
			newHighDataSource.delete(newHigh);
			this.refreshDataSource();
			}catch(Exception ex)
			{
				log.info(ex.getMessage());
			}
		
	}
/*----------------------------------------*/
	public int getEditNewHighId() {
		return editNewHighId;
	}

	public void setEditNewHighId(int editNewHighId) {
		this.editNewHighId = editNewHighId;
	}
	
	public void editNewHigh(int editId)
	{
		Map<String,List<String>> params = new HashMap<String,List<String>>();
		Map<String,Object> options = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		String seditId=String.valueOf(editId);
		options.put("modal", true);
		list.add(seditId);
		params.put("editId", list);
		RequestContext.getCurrentInstance().openDialog("createNewHigh",
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



