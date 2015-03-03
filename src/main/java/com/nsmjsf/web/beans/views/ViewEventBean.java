
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

import com.nsmjsf.web.datasources.EventDataSource;
import com.nsmjsf.web.datamodels.Event;
import com.nsmjsf.web.lazymodels.LazyEventDataModel;


			
import com.nsmjsf.web.adapters.PostAdapter;


import com.nsmjsf.web.datasources.PostDataSource;

import com.nsmjsf.web.datamodels.Post;

import com.nsmjsf.web.wrappers.PostWrapper;



			
				   
@ManagedBean
@ViewScoped
public class ViewEventBean implements Serializable {
private static final Log log = LogFactory.getLog(ViewEventBean.class);
 ViewType viewType=ViewType.DATATABLE;
 

	List<Event> eventList;
    List<Event> selectedEventList;
	List<Event> filteredEventList;
	Event selectedEvent;
	LazyDataModel<Event> lazyModel;
	EventDataSource eventDataSource;
	int editEventId=0;
	

			   List<Post> postList;
			   PostDataSource postDataSource;
			   public List<Post> getPostList() {
		return postList;
	     }
	public void setPostList(List<Post> postList) {
		this.postList = postList;
	}
			
				   
	
	
	public ViewEventBean()
	{
		this.initDataSources();
		this.populateData();
		
		lazyModel=new LazyEventDataModel(this.eventList);
		
	}
	
	
	private void initDataSources()
	{
		eventDataSource=new EventDataSource();
		

			  postDataSource=new PostDataSource();
			
				   
	
		
	}
	
	public void refreshDataSource(){
		this.eventList=eventDataSource.getAll();
		lazyModel=new LazyEventDataModel(this.eventList);
		
	}
	
	
	private void populateData()
	{
		eventList=eventDataSource.getAll();
		

			 postList=postDataSource.getAll();
	
				   
	
		
			}
	public List<Event> getEventList() {
		return eventList;
	}
	public void setEventList(List<Event> eventList) {
		this.eventList = eventList;
	}
	public LazyDataModel<Event> getLazyModel() {
		return lazyModel;
	}
	public void setLazyModel(LazyDataModel<Event> lazyModel) {
		this.lazyModel = lazyModel;
	}
	public Event getSelectedEvent() {
		return selectedEvent;
	}
	public void setSelectedEvent(Event selectedEvent) {
		this.selectedEvent = selectedEvent;
	}
	
	public List<Event> getSelectedEventList() {
		return selectedEventList;
	}

	public void setSelectedEventList(
			List<Event> selectedEventList) {
		this.selectedEventList = selectedEventList;
	}

	public List<Event> getFilteredEventList() {
		return filteredEventList;
	}

	public void setFilteredEventList(
			List<Event> filteredEventList) {
		this.filteredEventList = filteredEventList;
	}

	public void newEvent() {
		System.out.println("Opening dialogue");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);

		RequestContext.getCurrentInstance().openDialog("createEvent",
				options, null);

	}
	
	public void onRowSelect(SelectEvent event) {
		System.out.println("Event Selected"
				+ ((Event) event.getObject()).getEventId());
		for (Event cat : selectedEventList) {
			//System.out.println(cat.getEventLabel());
		}

	}

	public void onRowUnselect(UnselectEvent event) {
		System.out.println("Car unSelected"
				+ ((Event) event.getObject()).getEventId());

	}

	public void deleteSelectedEvent() {
		for (Event event : selectedEventList) {
			//System.out.println(event.getEventLabel());
			this.deleteEvent(event);
		}
	}
	public void deleteEvent(Event event) {
			try{
			eventDataSource.delete(event);
			this.refreshDataSource();
			}catch(Exception ex)
			{
				log.info(ex.getMessage());
			}
		
	}
/*----------------------------------------*/
	public int getEditEventId() {
		return editEventId;
	}

	public void setEditEventId(int editEventId) {
		this.editEventId = editEventId;
	}
	
	public void editEvent(int editId)
	{
		Map<String,List<String>> params = new HashMap<String,List<String>>();
		Map<String,Object> options = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		String seditId=String.valueOf(editId);
		options.put("modal", true);
		list.add(seditId);
		params.put("editId", list);
		RequestContext.getCurrentInstance().openDialog("createEvent",
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



