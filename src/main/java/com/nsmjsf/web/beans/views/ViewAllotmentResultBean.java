
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

import com.nsmjsf.web.datasources.AllotmentResultDataSource;
import com.nsmjsf.web.datamodels.AllotmentResult;
import com.nsmjsf.web.lazymodels.LazyAllotmentResultDataModel;


			
import com.nsmjsf.web.adapters.PostAdapter;


import com.nsmjsf.web.datasources.PostDataSource;

import com.nsmjsf.web.datamodels.Post;

import com.nsmjsf.web.wrappers.PostWrapper;



			
			
			
import com.nsmjsf.web.adapters.AnnouncementAdapter;


import com.nsmjsf.web.datasources.AnnouncementDataSource;

import com.nsmjsf.web.datamodels.Announcement;

import com.nsmjsf.web.wrappers.AnnouncementWrapper;



			
			
			
import com.nsmjsf.web.adapters.CompanyAdapter;


import com.nsmjsf.web.datasources.CompanyDataSource;

import com.nsmjsf.web.datamodels.Company;

import com.nsmjsf.web.wrappers.CompanyWrapper;



			
				   
@ManagedBean
@ViewScoped
public class ViewAllotmentResultBean implements Serializable {
private static final Log log = LogFactory.getLog(ViewAllotmentResultBean.class);
 ViewType viewType=ViewType.DATATABLE;
 

	List<AllotmentResult> allotmentResultList;
    List<AllotmentResult> selectedAllotmentResultList;
	List<AllotmentResult> filteredAllotmentResultList;
	AllotmentResult selectedAllotmentResult;
	LazyDataModel<AllotmentResult> lazyModel;
	AllotmentResultDataSource allotmentResultDataSource;
	int editAllotmentResultId=0;
	

			   List<Post> postList;
			   PostDataSource postDataSource;
			   public List<Post> getPostList() {
		return postList;
	     }
	public void setPostList(List<Post> postList) {
		this.postList = postList;
	}
			
			
			   List<Announcement> announcementList;
			   AnnouncementDataSource announcementDataSource;
			   public List<Announcement> getAnnouncementList() {
		return announcementList;
	     }
	public void setAnnouncementList(List<Announcement> announcementList) {
		this.announcementList = announcementList;
	}
			
			
			   List<Company> companyList;
			   CompanyDataSource companyDataSource;
			   public List<Company> getCompanyList() {
		return companyList;
	     }
	public void setCompanyList(List<Company> companyList) {
		this.companyList = companyList;
	}
			
				   
	
	
	public ViewAllotmentResultBean()
	{
		this.initDataSources();
		this.populateData();
		
		lazyModel=new LazyAllotmentResultDataModel(this.allotmentResultList);
		
	}
	
	
	private void initDataSources()
	{
		allotmentResultDataSource=new AllotmentResultDataSource();
		

			  postDataSource=new PostDataSource();
			
			
			  announcementDataSource=new AnnouncementDataSource();
			
			
			  companyDataSource=new CompanyDataSource();
			
				   
	
		
	}
	
	public void refreshDataSource(){
		this.allotmentResultList=allotmentResultDataSource.getAll();
		lazyModel=new LazyAllotmentResultDataModel(this.allotmentResultList);
		
	}
	
	
	private void populateData()
	{
		allotmentResultList=allotmentResultDataSource.getAll();
		

			 postList=postDataSource.getAll();
	
			
			 announcementList=announcementDataSource.getAll();
	
			
			 companyList=companyDataSource.getAll();
	
				   
	
		
			}
	public List<AllotmentResult> getAllotmentResultList() {
		return allotmentResultList;
	}
	public void setAllotmentResultList(List<AllotmentResult> allotmentResultList) {
		this.allotmentResultList = allotmentResultList;
	}
	public LazyDataModel<AllotmentResult> getLazyModel() {
		return lazyModel;
	}
	public void setLazyModel(LazyDataModel<AllotmentResult> lazyModel) {
		this.lazyModel = lazyModel;
	}
	public AllotmentResult getSelectedAllotmentResult() {
		return selectedAllotmentResult;
	}
	public void setSelectedAllotmentResult(AllotmentResult selectedAllotmentResult) {
		this.selectedAllotmentResult = selectedAllotmentResult;
	}
	
	public List<AllotmentResult> getSelectedAllotmentResultList() {
		return selectedAllotmentResultList;
	}

	public void setSelectedAllotmentResultList(
			List<AllotmentResult> selectedAllotmentResultList) {
		this.selectedAllotmentResultList = selectedAllotmentResultList;
	}

	public List<AllotmentResult> getFilteredAllotmentResultList() {
		return filteredAllotmentResultList;
	}

	public void setFilteredAllotmentResultList(
			List<AllotmentResult> filteredAllotmentResultList) {
		this.filteredAllotmentResultList = filteredAllotmentResultList;
	}

	public void newAllotmentResult() {
		System.out.println("Opening dialogue");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);

		RequestContext.getCurrentInstance().openDialog("createAllotmentResult",
				options, null);

	}
	
	public void onRowSelect(SelectEvent event) {
		System.out.println("AllotmentResult Selected"
				+ ((AllotmentResult) event.getObject()).getAllotmentResultId());
		for (AllotmentResult cat : selectedAllotmentResultList) {
			//System.out.println(cat.getAllotmentResultLabel());
		}

	}

	public void onRowUnselect(UnselectEvent event) {
		System.out.println("Car unSelected"
				+ ((AllotmentResult) event.getObject()).getAllotmentResultId());

	}

	public void deleteSelectedAllotmentResult() {
		for (AllotmentResult allotmentResult : selectedAllotmentResultList) {
			//System.out.println(allotmentResult.getAllotmentResultLabel());
			this.deleteAllotmentResult(allotmentResult);
		}
	}
	public void deleteAllotmentResult(AllotmentResult allotmentResult) {
			try{
			allotmentResultDataSource.delete(allotmentResult);
			this.refreshDataSource();
			}catch(Exception ex)
			{
				log.info(ex.getMessage());
			}
		
	}
/*----------------------------------------*/
	public int getEditAllotmentResultId() {
		return editAllotmentResultId;
	}

	public void setEditAllotmentResultId(int editAllotmentResultId) {
		this.editAllotmentResultId = editAllotmentResultId;
	}
	
	public void editAllotmentResult(int editId)
	{
		Map<String,List<String>> params = new HashMap<String,List<String>>();
		Map<String,Object> options = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		String seditId=String.valueOf(editId);
		options.put("modal", true);
		list.add(seditId);
		params.put("editId", list);
		RequestContext.getCurrentInstance().openDialog("createAllotmentResult",
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



