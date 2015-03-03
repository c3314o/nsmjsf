
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

import com.nsmjsf.web.datasources.BodTypeDataSource;
import com.nsmjsf.web.datamodels.BodType;
import com.nsmjsf.web.lazymodels.LazyBodTypeDataModel;


			
import com.nsmjsf.web.adapters.BodAdapter;


import com.nsmjsf.web.datasources.BodDataSource;

import com.nsmjsf.web.datamodels.Bod;

import com.nsmjsf.web.wrappers.BodWrapper;



			
				   
@ManagedBean
@ViewScoped
public class ViewBodTypeBean implements Serializable {
private static final Log log = LogFactory.getLog(ViewBodTypeBean.class);
 ViewType viewType=ViewType.DATATABLE;
 

	List<BodType> bodTypeList;
    List<BodType> selectedBodTypeList;
	List<BodType> filteredBodTypeList;
	BodType selectedBodType;
	LazyDataModel<BodType> lazyModel;
	BodTypeDataSource bodTypeDataSource;
	int editBodTypeId=0;
	
	   
	
	
	public ViewBodTypeBean()
	{
		this.initDataSources();
		this.populateData();
		
		lazyModel=new LazyBodTypeDataModel(this.bodTypeList);
		
	}
	
	
	private void initDataSources()
	{
		bodTypeDataSource=new BodTypeDataSource();
		
	   
	
		
	}
	
	public void refreshDataSource(){
		this.bodTypeList=bodTypeDataSource.getAll();
		lazyModel=new LazyBodTypeDataModel(this.bodTypeList);
		
	}
	
	
	private void populateData()
	{
		bodTypeList=bodTypeDataSource.getAll();
		
	   
	
		
			}
	public List<BodType> getBodTypeList() {
		return bodTypeList;
	}
	public void setBodTypeList(List<BodType> bodTypeList) {
		this.bodTypeList = bodTypeList;
	}
	public LazyDataModel<BodType> getLazyModel() {
		return lazyModel;
	}
	public void setLazyModel(LazyDataModel<BodType> lazyModel) {
		this.lazyModel = lazyModel;
	}
	public BodType getSelectedBodType() {
		return selectedBodType;
	}
	public void setSelectedBodType(BodType selectedBodType) {
		this.selectedBodType = selectedBodType;
	}
	
	public List<BodType> getSelectedBodTypeList() {
		return selectedBodTypeList;
	}

	public void setSelectedBodTypeList(
			List<BodType> selectedBodTypeList) {
		this.selectedBodTypeList = selectedBodTypeList;
	}

	public List<BodType> getFilteredBodTypeList() {
		return filteredBodTypeList;
	}

	public void setFilteredBodTypeList(
			List<BodType> filteredBodTypeList) {
		this.filteredBodTypeList = filteredBodTypeList;
	}

	public void newBodType() {
		System.out.println("Opening dialogue");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);

		RequestContext.getCurrentInstance().openDialog("createBodType",
				options, null);

	}
	
	public void onRowSelect(SelectEvent event) {
		System.out.println("BodType Selected"
				+ ((BodType) event.getObject()).getBodTypeId());
		for (BodType cat : selectedBodTypeList) {
			//System.out.println(cat.getBodTypeLabel());
		}

	}

	public void onRowUnselect(UnselectEvent event) {
		System.out.println("Car unSelected"
				+ ((BodType) event.getObject()).getBodTypeId());

	}

	public void deleteSelectedBodType() {
		for (BodType bodType : selectedBodTypeList) {
			//System.out.println(bodType.getBodTypeLabel());
			this.deleteBodType(bodType);
		}
	}
	public void deleteBodType(BodType bodType) {
			try{
			bodTypeDataSource.delete(bodType);
			this.refreshDataSource();
			}catch(Exception ex)
			{
				log.info(ex.getMessage());
			}
		
	}
/*----------------------------------------*/
	public int getEditBodTypeId() {
		return editBodTypeId;
	}

	public void setEditBodTypeId(int editBodTypeId) {
		this.editBodTypeId = editBodTypeId;
	}
	
	public void editBodType(int editId)
	{
		Map<String,List<String>> params = new HashMap<String,List<String>>();
		Map<String,Object> options = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		String seditId=String.valueOf(editId);
		options.put("modal", true);
		list.add(seditId);
		params.put("editId", list);
		RequestContext.getCurrentInstance().openDialog("createBodType",
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



