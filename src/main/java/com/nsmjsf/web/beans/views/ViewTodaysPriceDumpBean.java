
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

import com.nsmjsf.web.datasources.TodaysPriceDumpDataSource;
import com.nsmjsf.web.datamodels.TodaysPriceDump;
import com.nsmjsf.web.lazymodels.LazyTodaysPriceDumpDataModel;

	   
@ManagedBean
@ViewScoped
public class ViewTodaysPriceDumpBean implements Serializable {
private static final Log log = LogFactory.getLog(ViewTodaysPriceDumpBean.class);
 ViewType viewType=ViewType.DATATABLE;
 

	List<TodaysPriceDump> todaysPriceDumpList;
    List<TodaysPriceDump> selectedTodaysPriceDumpList;
	List<TodaysPriceDump> filteredTodaysPriceDumpList;
	TodaysPriceDump selectedTodaysPriceDump;
	LazyDataModel<TodaysPriceDump> lazyModel;
	TodaysPriceDumpDataSource todaysPriceDumpDataSource;
	int editTodaysPriceDumpId=0;
	
	   
	
	
	public ViewTodaysPriceDumpBean()
	{
		this.initDataSources();
		this.populateData();
		
		lazyModel=new LazyTodaysPriceDumpDataModel(this.todaysPriceDumpList);
		
	}
	
	
	private void initDataSources()
	{
		todaysPriceDumpDataSource=new TodaysPriceDumpDataSource();
		
	   
	
		
	}
	
	public void refreshDataSource(){
		this.todaysPriceDumpList=todaysPriceDumpDataSource.getAll();
		lazyModel=new LazyTodaysPriceDumpDataModel(this.todaysPriceDumpList);
		
	}
	
	
	private void populateData()
	{
		todaysPriceDumpList=todaysPriceDumpDataSource.getAll();
		
	   
	
		
			}
	public List<TodaysPriceDump> getTodaysPriceDumpList() {
		return todaysPriceDumpList;
	}
	public void setTodaysPriceDumpList(List<TodaysPriceDump> todaysPriceDumpList) {
		this.todaysPriceDumpList = todaysPriceDumpList;
	}
	public LazyDataModel<TodaysPriceDump> getLazyModel() {
		return lazyModel;
	}
	public void setLazyModel(LazyDataModel<TodaysPriceDump> lazyModel) {
		this.lazyModel = lazyModel;
	}
	public TodaysPriceDump getSelectedTodaysPriceDump() {
		return selectedTodaysPriceDump;
	}
	public void setSelectedTodaysPriceDump(TodaysPriceDump selectedTodaysPriceDump) {
		this.selectedTodaysPriceDump = selectedTodaysPriceDump;
	}
	
	public List<TodaysPriceDump> getSelectedTodaysPriceDumpList() {
		return selectedTodaysPriceDumpList;
	}

	public void setSelectedTodaysPriceDumpList(
			List<TodaysPriceDump> selectedTodaysPriceDumpList) {
		this.selectedTodaysPriceDumpList = selectedTodaysPriceDumpList;
	}

	public List<TodaysPriceDump> getFilteredTodaysPriceDumpList() {
		return filteredTodaysPriceDumpList;
	}

	public void setFilteredTodaysPriceDumpList(
			List<TodaysPriceDump> filteredTodaysPriceDumpList) {
		this.filteredTodaysPriceDumpList = filteredTodaysPriceDumpList;
	}

	public void newTodaysPriceDump() {
		System.out.println("Opening dialogue");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);

		RequestContext.getCurrentInstance().openDialog("createTodaysPriceDump",
				options, null);

	}
	
	public void onRowSelect(SelectEvent event) {
		System.out.println("TodaysPriceDump Selected"
				+ ((TodaysPriceDump) event.getObject()).getTodaysPriceDumpId());
		for (TodaysPriceDump cat : selectedTodaysPriceDumpList) {
			//System.out.println(cat.getTodaysPriceDumpLabel());
		}

	}

	public void onRowUnselect(UnselectEvent event) {
		System.out.println("Car unSelected"
				+ ((TodaysPriceDump) event.getObject()).getTodaysPriceDumpId());

	}

	public void deleteSelectedTodaysPriceDump() {
		for (TodaysPriceDump todaysPriceDump : selectedTodaysPriceDumpList) {
			//System.out.println(todaysPriceDump.getTodaysPriceDumpLabel());
			this.deleteTodaysPriceDump(todaysPriceDump);
		}
	}
	public void deleteTodaysPriceDump(TodaysPriceDump todaysPriceDump) {
			try{
			todaysPriceDumpDataSource.delete(todaysPriceDump);
			this.refreshDataSource();
			}catch(Exception ex)
			{
				log.info(ex.getMessage());
			}
		
	}
/*----------------------------------------*/
	public int getEditTodaysPriceDumpId() {
		return editTodaysPriceDumpId;
	}

	public void setEditTodaysPriceDumpId(int editTodaysPriceDumpId) {
		this.editTodaysPriceDumpId = editTodaysPriceDumpId;
	}
	
	public void editTodaysPriceDump(int editId)
	{
		Map<String,List<String>> params = new HashMap<String,List<String>>();
		Map<String,Object> options = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		String seditId=String.valueOf(editId);
		options.put("modal", true);
		list.add(seditId);
		params.put("editId", list);
		RequestContext.getCurrentInstance().openDialog("createTodaysPriceDump",
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



