
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

import com.nsmjsf.web.datasources.PostCategoryDataSource;
import com.nsmjsf.web.datamodels.PostCategory;
import com.nsmjsf.web.lazymodels.LazyPostCategoryDataModel;


			
import com.nsmjsf.web.adapters.MapPostCategoryAdapter;


import com.nsmjsf.web.datasources.MapPostCategoryDataSource;

import com.nsmjsf.web.datamodels.MapPostCategory;

import com.nsmjsf.web.wrappers.MapPostCategoryWrapper;



			
				   
@ManagedBean
@ViewScoped
public class ViewPostCategoryBean implements Serializable {
private static final Log log = LogFactory.getLog(ViewPostCategoryBean.class);
 ViewType viewType=ViewType.DATATABLE;
 

	List<PostCategory> postCategoryList;
    List<PostCategory> selectedPostCategoryList;
	List<PostCategory> filteredPostCategoryList;
	PostCategory selectedPostCategory;
	LazyDataModel<PostCategory> lazyModel;
	PostCategoryDataSource postCategoryDataSource;
	int editPostCategoryId=0;
	
	   
	
	
	public ViewPostCategoryBean()
	{
		this.initDataSources();
		this.populateData();
		
		lazyModel=new LazyPostCategoryDataModel(this.postCategoryList);
		
	}
	
	
	private void initDataSources()
	{
		postCategoryDataSource=new PostCategoryDataSource();
		
	   
	
		
	}
	
	public void refreshDataSource(){
		this.postCategoryList=postCategoryDataSource.getAll();
		lazyModel=new LazyPostCategoryDataModel(this.postCategoryList);
		
	}
	
	
	private void populateData()
	{
		postCategoryList=postCategoryDataSource.getAll();
		
	   
	
		
			}
	public List<PostCategory> getPostCategoryList() {
		return postCategoryList;
	}
	public void setPostCategoryList(List<PostCategory> postCategoryList) {
		this.postCategoryList = postCategoryList;
	}
	public LazyDataModel<PostCategory> getLazyModel() {
		return lazyModel;
	}
	public void setLazyModel(LazyDataModel<PostCategory> lazyModel) {
		this.lazyModel = lazyModel;
	}
	public PostCategory getSelectedPostCategory() {
		return selectedPostCategory;
	}
	public void setSelectedPostCategory(PostCategory selectedPostCategory) {
		this.selectedPostCategory = selectedPostCategory;
	}
	
	public List<PostCategory> getSelectedPostCategoryList() {
		return selectedPostCategoryList;
	}

	public void setSelectedPostCategoryList(
			List<PostCategory> selectedPostCategoryList) {
		this.selectedPostCategoryList = selectedPostCategoryList;
	}

	public List<PostCategory> getFilteredPostCategoryList() {
		return filteredPostCategoryList;
	}

	public void setFilteredPostCategoryList(
			List<PostCategory> filteredPostCategoryList) {
		this.filteredPostCategoryList = filteredPostCategoryList;
	}

	public void newPostCategory() {
		System.out.println("Opening dialogue");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);

		RequestContext.getCurrentInstance().openDialog("createPostCategory",
				options, null);

	}
	
	public void onRowSelect(SelectEvent event) {
		System.out.println("PostCategory Selected"
				+ ((PostCategory) event.getObject()).getPostCategoryId());
		for (PostCategory cat : selectedPostCategoryList) {
			//System.out.println(cat.getPostCategoryLabel());
		}

	}

	public void onRowUnselect(UnselectEvent event) {
		System.out.println("Car unSelected"
				+ ((PostCategory) event.getObject()).getPostCategoryId());

	}

	public void deleteSelectedPostCategory() {
		for (PostCategory postCategory : selectedPostCategoryList) {
			//System.out.println(postCategory.getPostCategoryLabel());
			this.deletePostCategory(postCategory);
		}
	}
	public void deletePostCategory(PostCategory postCategory) {
			try{
			postCategoryDataSource.delete(postCategory);
			this.refreshDataSource();
			}catch(Exception ex)
			{
				log.info(ex.getMessage());
			}
		
	}
/*----------------------------------------*/
	public int getEditPostCategoryId() {
		return editPostCategoryId;
	}

	public void setEditPostCategoryId(int editPostCategoryId) {
		this.editPostCategoryId = editPostCategoryId;
	}
	
	public void editPostCategory(int editId)
	{
		Map<String,List<String>> params = new HashMap<String,List<String>>();
		Map<String,Object> options = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		String seditId=String.valueOf(editId);
		options.put("modal", true);
		list.add(seditId);
		params.put("editId", list);
		RequestContext.getCurrentInstance().openDialog("createPostCategory",
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



