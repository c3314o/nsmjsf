
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

import com.nsmjsf.web.datasources.FloorsheetHeaderDataSource;
import com.nsmjsf.web.datamodels.FloorsheetHeader;
import com.nsmjsf.web.lazymodels.LazyFloorsheetHeaderDataModel;

	   
@ManagedBean
@ViewScoped
public class ViewFloorsheetHeaderBean implements Serializable {
private static final Log log = LogFactory.getLog(ViewFloorsheetHeaderBean.class);
 ViewType viewType=ViewType.DATATABLE;
 

	List<FloorsheetHeader> floorsheetHeaderList;
    List<FloorsheetHeader> selectedFloorsheetHeaderList;
	List<FloorsheetHeader> filteredFloorsheetHeaderList;
	FloorsheetHeader selectedFloorsheetHeader;
	LazyDataModel<FloorsheetHeader> lazyModel;
	FloorsheetHeaderDataSource floorsheetHeaderDataSource;
	int editFloorsheetHeaderId=0;
	
	   
	
	
	public ViewFloorsheetHeaderBean()
	{
		this.initDataSources();
		this.populateData();
		
		lazyModel=new LazyFloorsheetHeaderDataModel(this.floorsheetHeaderList);
		
	}
	
	
	private void initDataSources()
	{
		floorsheetHeaderDataSource=new FloorsheetHeaderDataSource();
		
	   
	
		
	}
	
	public void refreshDataSource(){
		this.floorsheetHeaderList=floorsheetHeaderDataSource.getAll();
		lazyModel=new LazyFloorsheetHeaderDataModel(this.floorsheetHeaderList);
		
	}
	
	
	private void populateData()
	{
		floorsheetHeaderList=floorsheetHeaderDataSource.getAll();
		
	   
	
		
			}
	public List<FloorsheetHeader> getFloorsheetHeaderList() {
		return floorsheetHeaderList;
	}
	public void setFloorsheetHeaderList(List<FloorsheetHeader> floorsheetHeaderList) {
		this.floorsheetHeaderList = floorsheetHeaderList;
	}
	public LazyDataModel<FloorsheetHeader> getLazyModel() {
		return lazyModel;
	}
	public void setLazyModel(LazyDataModel<FloorsheetHeader> lazyModel) {
		this.lazyModel = lazyModel;
	}
	public FloorsheetHeader getSelectedFloorsheetHeader() {
		return selectedFloorsheetHeader;
	}
	public void setSelectedFloorsheetHeader(FloorsheetHeader selectedFloorsheetHeader) {
		this.selectedFloorsheetHeader = selectedFloorsheetHeader;
	}
	
	public List<FloorsheetHeader> getSelectedFloorsheetHeaderList() {
		return selectedFloorsheetHeaderList;
	}

	public void setSelectedFloorsheetHeaderList(
			List<FloorsheetHeader> selectedFloorsheetHeaderList) {
		this.selectedFloorsheetHeaderList = selectedFloorsheetHeaderList;
	}

	public List<FloorsheetHeader> getFilteredFloorsheetHeaderList() {
		return filteredFloorsheetHeaderList;
	}

	public void setFilteredFloorsheetHeaderList(
			List<FloorsheetHeader> filteredFloorsheetHeaderList) {
		this.filteredFloorsheetHeaderList = filteredFloorsheetHeaderList;
	}

	public void newFloorsheetHeader() {
		System.out.println("Opening dialogue");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);

		RequestContext.getCurrentInstance().openDialog("createFloorsheetHeader",
				options, null);

	}
	
	public void onRowSelect(SelectEvent event) {
		System.out.println("FloorsheetHeader Selected"
				+ ((FloorsheetHeader) event.getObject()).getFloorsheetHeaderId());
		for (FloorsheetHeader cat : selectedFloorsheetHeaderList) {
			//System.out.println(cat.getFloorsheetHeaderLabel());
		}

	}

	public void onRowUnselect(UnselectEvent event) {
		System.out.println("Car unSelected"
				+ ((FloorsheetHeader) event.getObject()).getFloorsheetHeaderId());

	}

	public void deleteSelectedFloorsheetHeader() {
		for (FloorsheetHeader floorsheetHeader : selectedFloorsheetHeaderList) {
			//System.out.println(floorsheetHeader.getFloorsheetHeaderLabel());
			this.deleteFloorsheetHeader(floorsheetHeader);
		}
	}
	public void deleteFloorsheetHeader(FloorsheetHeader floorsheetHeader) {
			try{
			floorsheetHeaderDataSource.delete(floorsheetHeader);
			this.refreshDataSource();
			}catch(Exception ex)
			{
				log.info(ex.getMessage());
			}
		
	}
/*----------------------------------------*/
	public int getEditFloorsheetHeaderId() {
		return editFloorsheetHeaderId;
	}

	public void setEditFloorsheetHeaderId(int editFloorsheetHeaderId) {
		this.editFloorsheetHeaderId = editFloorsheetHeaderId;
	}
	
	public void editFloorsheetHeader(int editId)
	{
		Map<String,List<String>> params = new HashMap<String,List<String>>();
		Map<String,Object> options = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		String seditId=String.valueOf(editId);
		options.put("modal", true);
		list.add(seditId);
		params.put("editId", list);
		RequestContext.getCurrentInstance().openDialog("createFloorsheetHeader",
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



