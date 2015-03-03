
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

import com.nsmjsf.web.datasources.IssueManagerDataSource;
import com.nsmjsf.web.datamodels.IssueManager;
import com.nsmjsf.web.lazymodels.LazyIssueManagerDataModel;


			
import com.nsmjsf.web.adapters.AuctionAdapter;


import com.nsmjsf.web.datasources.AuctionDataSource;

import com.nsmjsf.web.datamodels.Auction;

import com.nsmjsf.web.wrappers.AuctionWrapper;



			
			
			
import com.nsmjsf.web.adapters.IssueAdapter;


import com.nsmjsf.web.datasources.IssueDataSource;

import com.nsmjsf.web.datamodels.Issue;

import com.nsmjsf.web.wrappers.IssueWrapper;



			
			
			
import com.nsmjsf.web.adapters.CertificateDividendDistributionAdapter;


import com.nsmjsf.web.datasources.CertificateDividendDistributionDataSource;

import com.nsmjsf.web.datamodels.CertificateDividendDistribution;

import com.nsmjsf.web.wrappers.CertificateDividendDistributionWrapper;



			
				   
@ManagedBean
@ViewScoped
public class ViewIssueManagerBean implements Serializable {
private static final Log log = LogFactory.getLog(ViewIssueManagerBean.class);
 ViewType viewType=ViewType.DATATABLE;
 

	List<IssueManager> issueManagerList;
    List<IssueManager> selectedIssueManagerList;
	List<IssueManager> filteredIssueManagerList;
	IssueManager selectedIssueManager;
	LazyDataModel<IssueManager> lazyModel;
	IssueManagerDataSource issueManagerDataSource;
	int editIssueManagerId=0;
	
	   
	
	
	public ViewIssueManagerBean()
	{
		this.initDataSources();
		this.populateData();
		
		lazyModel=new LazyIssueManagerDataModel(this.issueManagerList);
		
	}
	
	
	private void initDataSources()
	{
		issueManagerDataSource=new IssueManagerDataSource();
		
	   
	
		
	}
	
	public void refreshDataSource(){
		this.issueManagerList=issueManagerDataSource.getAll();
		lazyModel=new LazyIssueManagerDataModel(this.issueManagerList);
		
	}
	
	
	private void populateData()
	{
		issueManagerList=issueManagerDataSource.getAll();
		
	   
	
		
			}
	public List<IssueManager> getIssueManagerList() {
		return issueManagerList;
	}
	public void setIssueManagerList(List<IssueManager> issueManagerList) {
		this.issueManagerList = issueManagerList;
	}
	public LazyDataModel<IssueManager> getLazyModel() {
		return lazyModel;
	}
	public void setLazyModel(LazyDataModel<IssueManager> lazyModel) {
		this.lazyModel = lazyModel;
	}
	public IssueManager getSelectedIssueManager() {
		return selectedIssueManager;
	}
	public void setSelectedIssueManager(IssueManager selectedIssueManager) {
		this.selectedIssueManager = selectedIssueManager;
	}
	
	public List<IssueManager> getSelectedIssueManagerList() {
		return selectedIssueManagerList;
	}

	public void setSelectedIssueManagerList(
			List<IssueManager> selectedIssueManagerList) {
		this.selectedIssueManagerList = selectedIssueManagerList;
	}

	public List<IssueManager> getFilteredIssueManagerList() {
		return filteredIssueManagerList;
	}

	public void setFilteredIssueManagerList(
			List<IssueManager> filteredIssueManagerList) {
		this.filteredIssueManagerList = filteredIssueManagerList;
	}

	public void newIssueManager() {
		System.out.println("Opening dialogue");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);

		RequestContext.getCurrentInstance().openDialog("createIssueManager",
				options, null);

	}
	
	public void onRowSelect(SelectEvent event) {
		System.out.println("IssueManager Selected"
				+ ((IssueManager) event.getObject()).getIssueManagerId());
		for (IssueManager cat : selectedIssueManagerList) {
			//System.out.println(cat.getIssueManagerLabel());
		}

	}

	public void onRowUnselect(UnselectEvent event) {
		System.out.println("Car unSelected"
				+ ((IssueManager) event.getObject()).getIssueManagerId());

	}

	public void deleteSelectedIssueManager() {
		for (IssueManager issueManager : selectedIssueManagerList) {
			//System.out.println(issueManager.getIssueManagerLabel());
			this.deleteIssueManager(issueManager);
		}
	}
	public void deleteIssueManager(IssueManager issueManager) {
			try{
			issueManagerDataSource.delete(issueManager);
			this.refreshDataSource();
			}catch(Exception ex)
			{
				log.info(ex.getMessage());
			}
		
	}
/*----------------------------------------*/
	public int getEditIssueManagerId() {
		return editIssueManagerId;
	}

	public void setEditIssueManagerId(int editIssueManagerId) {
		this.editIssueManagerId = editIssueManagerId;
	}
	
	public void editIssueManager(int editId)
	{
		Map<String,List<String>> params = new HashMap<String,List<String>>();
		Map<String,Object> options = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		String seditId=String.valueOf(editId);
		options.put("modal", true);
		list.add(seditId);
		params.put("editId", list);
		RequestContext.getCurrentInstance().openDialog("createIssueManager",
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



