
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

import com.nsmjsf.web.datasources.AnnouncementTypeDataSource;
import com.nsmjsf.web.datamodels.AnnouncementType;
import com.nsmjsf.web.lazymodels.LazyAnnouncementTypeDataModel;


			
import com.nsmjsf.web.adapters.AnnouncementAdapter;


import com.nsmjsf.web.datasources.AnnouncementDataSource;

import com.nsmjsf.web.datamodels.Announcement;

import com.nsmjsf.web.wrappers.AnnouncementWrapper;



			
				   
@ManagedBean
@ViewScoped
public class ViewAnnouncementTypeBean implements Serializable {
private static final Log log = LogFactory.getLog(ViewAnnouncementTypeBean.class);
 ViewType viewType=ViewType.DATATABLE;
 

	List<AnnouncementType> announcementTypeList;
    List<AnnouncementType> selectedAnnouncementTypeList;
	List<AnnouncementType> filteredAnnouncementTypeList;
	AnnouncementType selectedAnnouncementType;
	LazyDataModel<AnnouncementType> lazyModel;
	AnnouncementTypeDataSource announcementTypeDataSource;
	int editAnnouncementTypeId=0;
	
	   
	
	
	public ViewAnnouncementTypeBean()
	{
		this.initDataSources();
		this.populateData();
		
		lazyModel=new LazyAnnouncementTypeDataModel(this.announcementTypeList);
		
	}
	
	
	private void initDataSources()
	{
		announcementTypeDataSource=new AnnouncementTypeDataSource();
		
	   
	
		
	}
	
	public void refreshDataSource(){
		this.announcementTypeList=announcementTypeDataSource.getAll();
		lazyModel=new LazyAnnouncementTypeDataModel(this.announcementTypeList);
		
	}
	
	
	private void populateData()
	{
		announcementTypeList=announcementTypeDataSource.getAll();
		
	   
	
		
			}
	public List<AnnouncementType> getAnnouncementTypeList() {
		return announcementTypeList;
	}
	public void setAnnouncementTypeList(List<AnnouncementType> announcementTypeList) {
		this.announcementTypeList = announcementTypeList;
	}
	public LazyDataModel<AnnouncementType> getLazyModel() {
		return lazyModel;
	}
	public void setLazyModel(LazyDataModel<AnnouncementType> lazyModel) {
		this.lazyModel = lazyModel;
	}
	public AnnouncementType getSelectedAnnouncementType() {
		return selectedAnnouncementType;
	}
	public void setSelectedAnnouncementType(AnnouncementType selectedAnnouncementType) {
		this.selectedAnnouncementType = selectedAnnouncementType;
	}
	
	public List<AnnouncementType> getSelectedAnnouncementTypeList() {
		return selectedAnnouncementTypeList;
	}

	public void setSelectedAnnouncementTypeList(
			List<AnnouncementType> selectedAnnouncementTypeList) {
		this.selectedAnnouncementTypeList = selectedAnnouncementTypeList;
	}

	public List<AnnouncementType> getFilteredAnnouncementTypeList() {
		return filteredAnnouncementTypeList;
	}

	public void setFilteredAnnouncementTypeList(
			List<AnnouncementType> filteredAnnouncementTypeList) {
		this.filteredAnnouncementTypeList = filteredAnnouncementTypeList;
	}

	public void newAnnouncementType() {
		System.out.println("Opening dialogue");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);

		RequestContext.getCurrentInstance().openDialog("createAnnouncementType",
				options, null);

	}
	
	public void onRowSelect(SelectEvent event) {
		System.out.println("AnnouncementType Selected"
				+ ((AnnouncementType) event.getObject()).getAnnouncementTypeId());
		for (AnnouncementType cat : selectedAnnouncementTypeList) {
			//System.out.println(cat.getAnnouncementTypeLabel());
		}

	}

	public void onRowUnselect(UnselectEvent event) {
		System.out.println("Car unSelected"
				+ ((AnnouncementType) event.getObject()).getAnnouncementTypeId());

	}

	public void deleteSelectedAnnouncementType() {
		for (AnnouncementType announcementType : selectedAnnouncementTypeList) {
			//System.out.println(announcementType.getAnnouncementTypeLabel());
			this.deleteAnnouncementType(announcementType);
		}
	}
	public void deleteAnnouncementType(AnnouncementType announcementType) {
			try{
			announcementTypeDataSource.delete(announcementType);
			this.refreshDataSource();
			}catch(Exception ex)
			{
				log.info(ex.getMessage());
			}
		
	}
/*----------------------------------------*/
	public int getEditAnnouncementTypeId() {
		return editAnnouncementTypeId;
	}

	public void setEditAnnouncementTypeId(int editAnnouncementTypeId) {
		this.editAnnouncementTypeId = editAnnouncementTypeId;
	}
	
	public void editAnnouncementType(int editId)
	{
		Map<String,List<String>> params = new HashMap<String,List<String>>();
		Map<String,Object> options = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		String seditId=String.valueOf(editId);
		options.put("modal", true);
		list.add(seditId);
		params.put("editId", list);
		RequestContext.getCurrentInstance().openDialog("createAnnouncementType",
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



