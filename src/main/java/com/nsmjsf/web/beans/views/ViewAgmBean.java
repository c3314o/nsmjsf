
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

import com.nsmjsf.web.datasources.AgmDataSource;
import com.nsmjsf.web.datamodels.Agm;
import com.nsmjsf.web.lazymodels.LazyAgmDataModel;


			
import com.nsmjsf.web.adapters.FiscalYearAdapter;


import com.nsmjsf.web.datasources.FiscalYearDataSource;

import com.nsmjsf.web.datamodels.FiscalYear;

import com.nsmjsf.web.wrappers.FiscalYearWrapper;



			
			
			
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
public class ViewAgmBean implements Serializable {
private static final Log log = LogFactory.getLog(ViewAgmBean.class);
 ViewType viewType=ViewType.DATATABLE;
 

	List<Agm> agmList;
    List<Agm> selectedAgmList;
	List<Agm> filteredAgmList;
	Agm selectedAgm;
	LazyDataModel<Agm> lazyModel;
	AgmDataSource agmDataSource;
	int editAgmId=0;
	

			   List<FiscalYear> fiscalYearList;
			   FiscalYearDataSource fiscalYearDataSource;
			   public List<FiscalYear> getFiscalYearList() {
		return fiscalYearList;
	     }
	public void setFiscalYearList(List<FiscalYear> fiscalYearList) {
		this.fiscalYearList = fiscalYearList;
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
			
				   
	
	
	public ViewAgmBean()
	{
		this.initDataSources();
		this.populateData();
		
		lazyModel=new LazyAgmDataModel(this.agmList);
		
	}
	
	
	private void initDataSources()
	{
		agmDataSource=new AgmDataSource();
		

			  fiscalYearDataSource=new FiscalYearDataSource();
			
			
			  announcementDataSource=new AnnouncementDataSource();
			
			
			  companyDataSource=new CompanyDataSource();
			
				   
	
		
	}
	
	public void refreshDataSource(){
		this.agmList=agmDataSource.getAll();
		lazyModel=new LazyAgmDataModel(this.agmList);
		
	}
	
	
	private void populateData()
	{
		agmList=agmDataSource.getAll();
		

			 fiscalYearList=fiscalYearDataSource.getAll();
	
			
			 announcementList=announcementDataSource.getAll();
	
			
			 companyList=companyDataSource.getAll();
	
				   
	
		
			}
	public List<Agm> getAgmList() {
		return agmList;
	}
	public void setAgmList(List<Agm> agmList) {
		this.agmList = agmList;
	}
	public LazyDataModel<Agm> getLazyModel() {
		return lazyModel;
	}
	public void setLazyModel(LazyDataModel<Agm> lazyModel) {
		this.lazyModel = lazyModel;
	}
	public Agm getSelectedAgm() {
		return selectedAgm;
	}
	public void setSelectedAgm(Agm selectedAgm) {
		this.selectedAgm = selectedAgm;
	}
	
	public List<Agm> getSelectedAgmList() {
		return selectedAgmList;
	}

	public void setSelectedAgmList(
			List<Agm> selectedAgmList) {
		this.selectedAgmList = selectedAgmList;
	}

	public List<Agm> getFilteredAgmList() {
		return filteredAgmList;
	}

	public void setFilteredAgmList(
			List<Agm> filteredAgmList) {
		this.filteredAgmList = filteredAgmList;
	}

	public void newAgm() {
		System.out.println("Opening dialogue");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);

		RequestContext.getCurrentInstance().openDialog("createAgm",
				options, null);

	}
	
	public void onRowSelect(SelectEvent event) {
		System.out.println("Agm Selected"
				+ ((Agm) event.getObject()).getAgmId());
		for (Agm cat : selectedAgmList) {
			//System.out.println(cat.getAgmLabel());
		}

	}

	public void onRowUnselect(UnselectEvent event) {
		System.out.println("Car unSelected"
				+ ((Agm) event.getObject()).getAgmId());

	}

	public void deleteSelectedAgm() {
		for (Agm agm : selectedAgmList) {
			//System.out.println(agm.getAgmLabel());
			this.deleteAgm(agm);
		}
	}
	public void deleteAgm(Agm agm) {
			try{
			agmDataSource.delete(agm);
			this.refreshDataSource();
			}catch(Exception ex)
			{
				log.info(ex.getMessage());
			}
		
	}
/*----------------------------------------*/
	public int getEditAgmId() {
		return editAgmId;
	}

	public void setEditAgmId(int editAgmId) {
		this.editAgmId = editAgmId;
	}
	
	public void editAgm(int editId)
	{
		Map<String,List<String>> params = new HashMap<String,List<String>>();
		Map<String,Object> options = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		String seditId=String.valueOf(editId);
		options.put("modal", true);
		list.add(seditId);
		params.put("editId", list);
		RequestContext.getCurrentInstance().openDialog("createAgm",
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



