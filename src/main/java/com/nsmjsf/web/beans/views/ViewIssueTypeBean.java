
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

import com.nsmjsf.web.datasources.IssueTypeDataSource;
import com.nsmjsf.web.datamodels.IssueType;
import com.nsmjsf.web.lazymodels.LazyIssueTypeDataModel;


			
import com.nsmjsf.web.adapters.IssueAdapter;


import com.nsmjsf.web.datasources.IssueDataSource;

import com.nsmjsf.web.datamodels.Issue;

import com.nsmjsf.web.wrappers.IssueWrapper;



			
				   
@ManagedBean
@ViewScoped
public class ViewIssueTypeBean implements Serializable {
private static final Log log = LogFactory.getLog(ViewIssueTypeBean.class);
 ViewType viewType=ViewType.DATATABLE;
 

	List<IssueType> issueTypeList;
    List<IssueType> selectedIssueTypeList;
	List<IssueType> filteredIssueTypeList;
	IssueType selectedIssueType;
	LazyDataModel<IssueType> lazyModel;
	IssueTypeDataSource issueTypeDataSource;
	int editIssueTypeId=0;
	
	   
	
	
	public ViewIssueTypeBean()
	{
		this.initDataSources();
		this.populateData();
		
		lazyModel=new LazyIssueTypeDataModel(this.issueTypeList);
		
	}
	
	
	private void initDataSources()
	{
		issueTypeDataSource=new IssueTypeDataSource();
		
	   
	
		
	}
	
	public void refreshDataSource(){
		this.issueTypeList=issueTypeDataSource.getAll();
		lazyModel=new LazyIssueTypeDataModel(this.issueTypeList);
		
	}
	
	
	private void populateData()
	{
		issueTypeList=issueTypeDataSource.getAll();
		
	   
	
		
			}
	public List<IssueType> getIssueTypeList() {
		return issueTypeList;
	}
	public void setIssueTypeList(List<IssueType> issueTypeList) {
		this.issueTypeList = issueTypeList;
	}
	public LazyDataModel<IssueType> getLazyModel() {
		return lazyModel;
	}
	public void setLazyModel(LazyDataModel<IssueType> lazyModel) {
		this.lazyModel = lazyModel;
	}
	public IssueType getSelectedIssueType() {
		return selectedIssueType;
	}
	public void setSelectedIssueType(IssueType selectedIssueType) {
		this.selectedIssueType = selectedIssueType;
	}
	
	public List<IssueType> getSelectedIssueTypeList() {
		return selectedIssueTypeList;
	}

	public void setSelectedIssueTypeList(
			List<IssueType> selectedIssueTypeList) {
		this.selectedIssueTypeList = selectedIssueTypeList;
	}

	public List<IssueType> getFilteredIssueTypeList() {
		return filteredIssueTypeList;
	}

	public void setFilteredIssueTypeList(
			List<IssueType> filteredIssueTypeList) {
		this.filteredIssueTypeList = filteredIssueTypeList;
	}

	public void newIssueType() {
		System.out.println("Opening dialogue");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);

		RequestContext.getCurrentInstance().openDialog("createIssueType",
				options, null);

	}
	
	public void onRowSelect(SelectEvent event) {
		System.out.println("IssueType Selected"
				+ ((IssueType) event.getObject()).getIssueTypeId());
		for (IssueType cat : selectedIssueTypeList) {
			//System.out.println(cat.getIssueTypeLabel());
		}

	}

	public void onRowUnselect(UnselectEvent event) {
		System.out.println("Car unSelected"
				+ ((IssueType) event.getObject()).getIssueTypeId());

	}

	public void deleteSelectedIssueType() {
		for (IssueType issueType : selectedIssueTypeList) {
			//System.out.println(issueType.getIssueTypeLabel());
			this.deleteIssueType(issueType);
		}
	}
	public void deleteIssueType(IssueType issueType) {
			try{
			issueTypeDataSource.delete(issueType);
			this.refreshDataSource();
			}catch(Exception ex)
			{
				log.info(ex.getMessage());
			}
		
	}
/*----------------------------------------*/
	public int getEditIssueTypeId() {
		return editIssueTypeId;
	}

	public void setEditIssueTypeId(int editIssueTypeId) {
		this.editIssueTypeId = editIssueTypeId;
	}
	
	public void editIssueType(int editId)
	{
		Map<String,List<String>> params = new HashMap<String,List<String>>();
		Map<String,Object> options = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		String seditId=String.valueOf(editId);
		options.put("modal", true);
		list.add(seditId);
		params.put("editId", list);
		RequestContext.getCurrentInstance().openDialog("createIssueType",
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



