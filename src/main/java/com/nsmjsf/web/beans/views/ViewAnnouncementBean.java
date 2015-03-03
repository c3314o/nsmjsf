
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

import com.nsmjsf.web.datasources.AnnouncementDataSource;
import com.nsmjsf.web.datamodels.Announcement;
import com.nsmjsf.web.lazymodels.LazyAnnouncementDataModel;


			
import com.nsmjsf.web.adapters.AuctionAdapter;


import com.nsmjsf.web.datasources.AuctionDataSource;

import com.nsmjsf.web.datamodels.Auction;

import com.nsmjsf.web.wrappers.AuctionWrapper;



			
			
			
import com.nsmjsf.web.adapters.PostAdapter;


import com.nsmjsf.web.datasources.PostDataSource;

import com.nsmjsf.web.datamodels.Post;

import com.nsmjsf.web.wrappers.PostWrapper;



			
			
			
import com.nsmjsf.web.adapters.BonusDividendApprovedAdapter;


import com.nsmjsf.web.datasources.BonusDividendApprovedDataSource;

import com.nsmjsf.web.datamodels.BonusDividendApproved;

import com.nsmjsf.web.wrappers.BonusDividendApprovedWrapper;



			
			
			
import com.nsmjsf.web.adapters.IssueAdapter;


import com.nsmjsf.web.datasources.IssueDataSource;

import com.nsmjsf.web.datamodels.Issue;

import com.nsmjsf.web.wrappers.IssueWrapper;



			
			
			
import com.nsmjsf.web.adapters.AgmAdapter;


import com.nsmjsf.web.datasources.AgmDataSource;

import com.nsmjsf.web.datamodels.Agm;

import com.nsmjsf.web.wrappers.AgmWrapper;



			
			
			
import com.nsmjsf.web.adapters.CertificateDividendDistributionAdapter;


import com.nsmjsf.web.datasources.CertificateDividendDistributionDataSource;

import com.nsmjsf.web.datamodels.CertificateDividendDistribution;

import com.nsmjsf.web.wrappers.CertificateDividendDistributionWrapper;



			
			
			
import com.nsmjsf.web.adapters.AnnouncementTypeAdapter;


import com.nsmjsf.web.datasources.AnnouncementTypeDataSource;

import com.nsmjsf.web.datamodels.AnnouncementType;

import com.nsmjsf.web.wrappers.AnnouncementTypeWrapper;



			
			
			
import com.nsmjsf.web.adapters.MonthlyFinancialHighlightAdapter;


import com.nsmjsf.web.datasources.MonthlyFinancialHighlightDataSource;

import com.nsmjsf.web.datamodels.MonthlyFinancialHighlight;

import com.nsmjsf.web.wrappers.MonthlyFinancialHighlightWrapper;



			
			
			
import com.nsmjsf.web.adapters.AllotmentResultAdapter;


import com.nsmjsf.web.datasources.AllotmentResultDataSource;

import com.nsmjsf.web.datamodels.AllotmentResult;

import com.nsmjsf.web.wrappers.AllotmentResultWrapper;



			
				   
@ManagedBean
@ViewScoped
public class ViewAnnouncementBean implements Serializable {
private static final Log log = LogFactory.getLog(ViewAnnouncementBean.class);
 ViewType viewType=ViewType.DATATABLE;
 

	List<Announcement> announcementList;
    List<Announcement> selectedAnnouncementList;
	List<Announcement> filteredAnnouncementList;
	Announcement selectedAnnouncement;
	LazyDataModel<Announcement> lazyModel;
	AnnouncementDataSource announcementDataSource;
	int editAnnouncementId=0;
	

			   List<Post> postList;
			   PostDataSource postDataSource;
			   public List<Post> getPostList() {
		return postList;
	     }
	public void setPostList(List<Post> postList) {
		this.postList = postList;
	}
			
			
			   List<AnnouncementType> announcementTypeList;
			   AnnouncementTypeDataSource announcementTypeDataSource;
			   public List<AnnouncementType> getAnnouncementTypeList() {
		return announcementTypeList;
	     }
	public void setAnnouncementTypeList(List<AnnouncementType> announcementTypeList) {
		this.announcementTypeList = announcementTypeList;
	}
			
				   
	
	
	public ViewAnnouncementBean()
	{
		this.initDataSources();
		this.populateData();
		
		lazyModel=new LazyAnnouncementDataModel(this.announcementList);
		
	}
	
	
	private void initDataSources()
	{
		announcementDataSource=new AnnouncementDataSource();
		

			  postDataSource=new PostDataSource();
			
			
			  announcementTypeDataSource=new AnnouncementTypeDataSource();
			
				   
	
		
	}
	
	public void refreshDataSource(){
		this.announcementList=announcementDataSource.getAll();
		lazyModel=new LazyAnnouncementDataModel(this.announcementList);
		
	}
	
	
	private void populateData()
	{
		announcementList=announcementDataSource.getAll();
		

			 postList=postDataSource.getAll();
	
			
			 announcementTypeList=announcementTypeDataSource.getAll();
	
				   
	
		
			}
	public List<Announcement> getAnnouncementList() {
		return announcementList;
	}
	public void setAnnouncementList(List<Announcement> announcementList) {
		this.announcementList = announcementList;
	}
	public LazyDataModel<Announcement> getLazyModel() {
		return lazyModel;
	}
	public void setLazyModel(LazyDataModel<Announcement> lazyModel) {
		this.lazyModel = lazyModel;
	}
	public Announcement getSelectedAnnouncement() {
		return selectedAnnouncement;
	}
	public void setSelectedAnnouncement(Announcement selectedAnnouncement) {
		this.selectedAnnouncement = selectedAnnouncement;
	}
	
	public List<Announcement> getSelectedAnnouncementList() {
		return selectedAnnouncementList;
	}

	public void setSelectedAnnouncementList(
			List<Announcement> selectedAnnouncementList) {
		this.selectedAnnouncementList = selectedAnnouncementList;
	}

	public List<Announcement> getFilteredAnnouncementList() {
		return filteredAnnouncementList;
	}

	public void setFilteredAnnouncementList(
			List<Announcement> filteredAnnouncementList) {
		this.filteredAnnouncementList = filteredAnnouncementList;
	}

	public void newAnnouncement() {
		System.out.println("Opening dialogue");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);

		RequestContext.getCurrentInstance().openDialog("createAnnouncement",
				options, null);

	}
	
	public void onRowSelect(SelectEvent event) {
		System.out.println("Announcement Selected"
				+ ((Announcement) event.getObject()).getAnnouncementId());
		for (Announcement cat : selectedAnnouncementList) {
			//System.out.println(cat.getAnnouncementLabel());
		}

	}

	public void onRowUnselect(UnselectEvent event) {
		System.out.println("Car unSelected"
				+ ((Announcement) event.getObject()).getAnnouncementId());

	}

	public void deleteSelectedAnnouncement() {
		for (Announcement announcement : selectedAnnouncementList) {
			//System.out.println(announcement.getAnnouncementLabel());
			this.deleteAnnouncement(announcement);
		}
	}
	public void deleteAnnouncement(Announcement announcement) {
			try{
			announcementDataSource.delete(announcement);
			this.refreshDataSource();
			}catch(Exception ex)
			{
				log.info(ex.getMessage());
			}
		
	}
/*----------------------------------------*/
	public int getEditAnnouncementId() {
		return editAnnouncementId;
	}

	public void setEditAnnouncementId(int editAnnouncementId) {
		this.editAnnouncementId = editAnnouncementId;
	}
	
	public void editAnnouncement(int editId)
	{
		Map<String,List<String>> params = new HashMap<String,List<String>>();
		Map<String,Object> options = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		String seditId=String.valueOf(editId);
		options.put("modal", true);
		list.add(seditId);
		params.put("editId", list);
		RequestContext.getCurrentInstance().openDialog("createAnnouncement",
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



