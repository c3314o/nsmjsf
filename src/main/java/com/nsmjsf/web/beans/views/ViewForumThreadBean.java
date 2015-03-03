
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

import com.nsmjsf.web.datasources.ForumThreadDataSource;
import com.nsmjsf.web.datamodels.ForumThread;
import com.nsmjsf.web.lazymodels.LazyForumThreadDataModel;


			
import com.nsmjsf.web.adapters.PostAdapter;


import com.nsmjsf.web.datasources.PostDataSource;

import com.nsmjsf.web.datamodels.Post;

import com.nsmjsf.web.wrappers.PostWrapper;



			
				   
@ManagedBean
@ViewScoped
public class ViewForumThreadBean implements Serializable {
private static final Log log = LogFactory.getLog(ViewForumThreadBean.class);
 ViewType viewType=ViewType.DATATABLE;
 

	List<ForumThread> forumThreadList;
    List<ForumThread> selectedForumThreadList;
	List<ForumThread> filteredForumThreadList;
	ForumThread selectedForumThread;
	LazyDataModel<ForumThread> lazyModel;
	ForumThreadDataSource forumThreadDataSource;
	int editForumThreadId=0;
	

			   List<Post> postList;
			   PostDataSource postDataSource;
			   public List<Post> getPostList() {
		return postList;
	     }
	public void setPostList(List<Post> postList) {
		this.postList = postList;
	}
			
				   
	
	
	public ViewForumThreadBean()
	{
		this.initDataSources();
		this.populateData();
		
		lazyModel=new LazyForumThreadDataModel(this.forumThreadList);
		
	}
	
	
	private void initDataSources()
	{
		forumThreadDataSource=new ForumThreadDataSource();
		

			  postDataSource=new PostDataSource();
			
				   
	
		
	}
	
	public void refreshDataSource(){
		this.forumThreadList=forumThreadDataSource.getAll();
		lazyModel=new LazyForumThreadDataModel(this.forumThreadList);
		
	}
	
	
	private void populateData()
	{
		forumThreadList=forumThreadDataSource.getAll();
		

			 postList=postDataSource.getAll();
	
				   
	
		
			}
	public List<ForumThread> getForumThreadList() {
		return forumThreadList;
	}
	public void setForumThreadList(List<ForumThread> forumThreadList) {
		this.forumThreadList = forumThreadList;
	}
	public LazyDataModel<ForumThread> getLazyModel() {
		return lazyModel;
	}
	public void setLazyModel(LazyDataModel<ForumThread> lazyModel) {
		this.lazyModel = lazyModel;
	}
	public ForumThread getSelectedForumThread() {
		return selectedForumThread;
	}
	public void setSelectedForumThread(ForumThread selectedForumThread) {
		this.selectedForumThread = selectedForumThread;
	}
	
	public List<ForumThread> getSelectedForumThreadList() {
		return selectedForumThreadList;
	}

	public void setSelectedForumThreadList(
			List<ForumThread> selectedForumThreadList) {
		this.selectedForumThreadList = selectedForumThreadList;
	}

	public List<ForumThread> getFilteredForumThreadList() {
		return filteredForumThreadList;
	}

	public void setFilteredForumThreadList(
			List<ForumThread> filteredForumThreadList) {
		this.filteredForumThreadList = filteredForumThreadList;
	}

	public void newForumThread() {
		System.out.println("Opening dialogue");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);

		RequestContext.getCurrentInstance().openDialog("createForumThread",
				options, null);

	}
	
	public void onRowSelect(SelectEvent event) {
		System.out.println("ForumThread Selected"
				+ ((ForumThread) event.getObject()).getForumThreadId());
		for (ForumThread cat : selectedForumThreadList) {
			//System.out.println(cat.getForumThreadLabel());
		}

	}

	public void onRowUnselect(UnselectEvent event) {
		System.out.println("Car unSelected"
				+ ((ForumThread) event.getObject()).getForumThreadId());

	}

	public void deleteSelectedForumThread() {
		for (ForumThread forumThread : selectedForumThreadList) {
			//System.out.println(forumThread.getForumThreadLabel());
			this.deleteForumThread(forumThread);
		}
	}
	public void deleteForumThread(ForumThread forumThread) {
			try{
			forumThreadDataSource.delete(forumThread);
			this.refreshDataSource();
			}catch(Exception ex)
			{
				log.info(ex.getMessage());
			}
		
	}
/*----------------------------------------*/
	public int getEditForumThreadId() {
		return editForumThreadId;
	}

	public void setEditForumThreadId(int editForumThreadId) {
		this.editForumThreadId = editForumThreadId;
	}
	
	public void editForumThread(int editId)
	{
		Map<String,List<String>> params = new HashMap<String,List<String>>();
		Map<String,Object> options = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		String seditId=String.valueOf(editId);
		options.put("modal", true);
		list.add(seditId);
		params.put("editId", list);
		RequestContext.getCurrentInstance().openDialog("createForumThread",
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



