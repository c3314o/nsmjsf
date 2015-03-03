
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

import com.nsmjsf.web.datasources.IssueDataSource;
import com.nsmjsf.web.datamodels.Issue;
import com.nsmjsf.web.lazymodels.LazyIssueDataModel;


			
import com.nsmjsf.web.adapters.AnnouncementAdapter;


import com.nsmjsf.web.datasources.AnnouncementDataSource;

import com.nsmjsf.web.datamodels.Announcement;

import com.nsmjsf.web.wrappers.AnnouncementWrapper;



			
			
			
import com.nsmjsf.web.adapters.IssueTypeAdapter;


import com.nsmjsf.web.datasources.IssueTypeDataSource;

import com.nsmjsf.web.datamodels.IssueType;

import com.nsmjsf.web.wrappers.IssueTypeWrapper;



			
			
			
import com.nsmjsf.web.adapters.CompanyAdapter;


import com.nsmjsf.web.datasources.CompanyDataSource;

import com.nsmjsf.web.datamodels.Company;

import com.nsmjsf.web.wrappers.CompanyWrapper;



			
			
			
import com.nsmjsf.web.adapters.IssueManagerAdapter;


import com.nsmjsf.web.datasources.IssueManagerDataSource;

import com.nsmjsf.web.datamodels.IssueManager;

import com.nsmjsf.web.wrappers.IssueManagerWrapper;



			
				   
@ManagedBean
@ViewScoped
public class ViewIssueBean implements Serializable {
private static final Log log = LogFactory.getLog(ViewIssueBean.class);
 ViewType viewType=ViewType.DATATABLE;
 

	List<Issue> issueList;
    List<Issue> selectedIssueList;
	List<Issue> filteredIssueList;
	Issue selectedIssue;
	LazyDataModel<Issue> lazyModel;
	IssueDataSource issueDataSource;
	int editIssueId=0;
	

			   List<Announcement> announcementList;
			   AnnouncementDataSource announcementDataSource;
			   public List<Announcement> getAnnouncementList() {
		return announcementList;
	     }
	public void setAnnouncementList(List<Announcement> announcementList) {
		this.announcementList = announcementList;
	}
			
			
			   List<IssueType> issueTypeList;
			   IssueTypeDataSource issueTypeDataSource;
			   public List<IssueType> getIssueTypeList() {
		return issueTypeList;
	     }
	public void setIssueTypeList(List<IssueType> issueTypeList) {
		this.issueTypeList = issueTypeList;
	}
			
			
			   List<Company> companyList;
			   CompanyDataSource companyDataSource;
			   public List<Company> getCompanyList() {
		return companyList;
	     }
	public void setCompanyList(List<Company> companyList) {
		this.companyList = companyList;
	}
			
			
			   List<IssueManager> issueManagerList;
			   IssueManagerDataSource issueManagerDataSource;
			   public List<IssueManager> getIssueManagerList() {
		return issueManagerList;
	     }
	public void setIssueManagerList(List<IssueManager> issueManagerList) {
		this.issueManagerList = issueManagerList;
	}
			
				   
	
	
	public ViewIssueBean()
	{
		this.initDataSources();
		this.populateData();
		
		lazyModel=new LazyIssueDataModel(this.issueList);
		
	}
	
	
	private void initDataSources()
	{
		issueDataSource=new IssueDataSource();
		

			  announcementDataSource=new AnnouncementDataSource();
			
			
			  issueTypeDataSource=new IssueTypeDataSource();
			
			
			  companyDataSource=new CompanyDataSource();
			
			
			  issueManagerDataSource=new IssueManagerDataSource();
			
				   
	
		
	}
	
	public void refreshDataSource(){
		this.issueList=issueDataSource.getAll();
		lazyModel=new LazyIssueDataModel(this.issueList);
		
	}
	
	
	private void populateData()
	{
		issueList=issueDataSource.getAll();
		

			 announcementList=announcementDataSource.getAll();
	
			
			 issueTypeList=issueTypeDataSource.getAll();
	
			
			 companyList=companyDataSource.getAll();
	
			
			 issueManagerList=issueManagerDataSource.getAll();
	
				   
	
		
			}
	public List<Issue> getIssueList() {
		return issueList;
	}
	public void setIssueList(List<Issue> issueList) {
		this.issueList = issueList;
	}
	public LazyDataModel<Issue> getLazyModel() {
		return lazyModel;
	}
	public void setLazyModel(LazyDataModel<Issue> lazyModel) {
		this.lazyModel = lazyModel;
	}
	public Issue getSelectedIssue() {
		return selectedIssue;
	}
	public void setSelectedIssue(Issue selectedIssue) {
		this.selectedIssue = selectedIssue;
	}
	
	public List<Issue> getSelectedIssueList() {
		return selectedIssueList;
	}

	public void setSelectedIssueList(
			List<Issue> selectedIssueList) {
		this.selectedIssueList = selectedIssueList;
	}

	public List<Issue> getFilteredIssueList() {
		return filteredIssueList;
	}

	public void setFilteredIssueList(
			List<Issue> filteredIssueList) {
		this.filteredIssueList = filteredIssueList;
	}

	public void newIssue() {
		System.out.println("Opening dialogue");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);

		RequestContext.getCurrentInstance().openDialog("createIssue",
				options, null);

	}
	
	public void onRowSelect(SelectEvent event) {
		System.out.println("Issue Selected"
				+ ((Issue) event.getObject()).getIssueId());
		for (Issue cat : selectedIssueList) {
			//System.out.println(cat.getIssueLabel());
		}

	}

	public void onRowUnselect(UnselectEvent event) {
		System.out.println("Car unSelected"
				+ ((Issue) event.getObject()).getIssueId());

	}

	public void deleteSelectedIssue() {
		for (Issue issue : selectedIssueList) {
			//System.out.println(issue.getIssueLabel());
			this.deleteIssue(issue);
		}
	}
	public void deleteIssue(Issue issue) {
			try{
			issueDataSource.delete(issue);
			this.refreshDataSource();
			}catch(Exception ex)
			{
				log.info(ex.getMessage());
			}
		
	}
/*----------------------------------------*/
	public int getEditIssueId() {
		return editIssueId;
	}

	public void setEditIssueId(int editIssueId) {
		this.editIssueId = editIssueId;
	}
	
	public void editIssue(int editId)
	{
		Map<String,List<String>> params = new HashMap<String,List<String>>();
		Map<String,Object> options = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		String seditId=String.valueOf(editId);
		options.put("modal", true);
		list.add(seditId);
		params.put("editId", list);
		RequestContext.getCurrentInstance().openDialog("createIssue",
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



