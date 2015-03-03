
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

import com.nsmjsf.web.datasources.MapPostCategoryDataSource;
import com.nsmjsf.web.datamodels.MapPostCategory;
import com.nsmjsf.web.lazymodels.LazyMapPostCategoryDataModel;


			
import com.nsmjsf.web.adapters.PostCategoryAdapter;


import com.nsmjsf.web.datasources.PostCategoryDataSource;

import com.nsmjsf.web.datamodels.PostCategory;

import com.nsmjsf.web.wrappers.PostCategoryWrapper;



			
			
			
import com.nsmjsf.web.adapters.PostAdapter;


import com.nsmjsf.web.datasources.PostDataSource;

import com.nsmjsf.web.datamodels.Post;

import com.nsmjsf.web.wrappers.PostWrapper;



			
				   
@ManagedBean
@ViewScoped
public class ViewMapPostCategoryBean implements Serializable {
private static final Log log = LogFactory.getLog(ViewMapPostCategoryBean.class);
 ViewType viewType=ViewType.DATATABLE;
 

	List<MapPostCategory> mapPostCategoryList;
    List<MapPostCategory> selectedMapPostCategoryList;
	List<MapPostCategory> filteredMapPostCategoryList;
	MapPostCategory selectedMapPostCategory;
	LazyDataModel<MapPostCategory> lazyModel;
	MapPostCategoryDataSource mapPostCategoryDataSource;
	int editMapPostCategoryId=0;
	

			   List<PostCategory> postCategoryList;
			   PostCategoryDataSource postCategoryDataSource;
			   public List<PostCategory> getPostCategoryList() {
		return postCategoryList;
	     }
	public void setPostCategoryList(List<PostCategory> postCategoryList) {
		this.postCategoryList = postCategoryList;
	}
			
			
			   List<Post> postList;
			   PostDataSource postDataSource;
			   public List<Post> getPostList() {
		return postList;
	     }
	public void setPostList(List<Post> postList) {
		this.postList = postList;
	}
			
				   
	
	
	public ViewMapPostCategoryBean()
	{
		this.initDataSources();
		this.populateData();
		
		lazyModel=new LazyMapPostCategoryDataModel(this.mapPostCategoryList);
		
	}
	
	
	private void initDataSources()
	{
		mapPostCategoryDataSource=new MapPostCategoryDataSource();
		

			  postCategoryDataSource=new PostCategoryDataSource();
			
			
			  postDataSource=new PostDataSource();
			
				   
	
		
	}
	
	public void refreshDataSource(){
		this.mapPostCategoryList=mapPostCategoryDataSource.getAll();
		lazyModel=new LazyMapPostCategoryDataModel(this.mapPostCategoryList);
		
	}
	
	
	private void populateData()
	{
		mapPostCategoryList=mapPostCategoryDataSource.getAll();
		

			 postCategoryList=postCategoryDataSource.getAll();
	
			
			 postList=postDataSource.getAll();
	
				   
	
		
			}
	public List<MapPostCategory> getMapPostCategoryList() {
		return mapPostCategoryList;
	}
	public void setMapPostCategoryList(List<MapPostCategory> mapPostCategoryList) {
		this.mapPostCategoryList = mapPostCategoryList;
	}
	public LazyDataModel<MapPostCategory> getLazyModel() {
		return lazyModel;
	}
	public void setLazyModel(LazyDataModel<MapPostCategory> lazyModel) {
		this.lazyModel = lazyModel;
	}
	public MapPostCategory getSelectedMapPostCategory() {
		return selectedMapPostCategory;
	}
	public void setSelectedMapPostCategory(MapPostCategory selectedMapPostCategory) {
		this.selectedMapPostCategory = selectedMapPostCategory;
	}
	
	public List<MapPostCategory> getSelectedMapPostCategoryList() {
		return selectedMapPostCategoryList;
	}

	public void setSelectedMapPostCategoryList(
			List<MapPostCategory> selectedMapPostCategoryList) {
		this.selectedMapPostCategoryList = selectedMapPostCategoryList;
	}

	public List<MapPostCategory> getFilteredMapPostCategoryList() {
		return filteredMapPostCategoryList;
	}

	public void setFilteredMapPostCategoryList(
			List<MapPostCategory> filteredMapPostCategoryList) {
		this.filteredMapPostCategoryList = filteredMapPostCategoryList;
	}

	public void newMapPostCategory() {
		System.out.println("Opening dialogue");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);

		RequestContext.getCurrentInstance().openDialog("createMapPostCategory",
				options, null);

	}
	
	public void onRowSelect(SelectEvent event) {
		System.out.println("MapPostCategory Selected"
				+ ((MapPostCategory) event.getObject()).getMapPostCategoryId());
		for (MapPostCategory cat : selectedMapPostCategoryList) {
			//System.out.println(cat.getMapPostCategoryLabel());
		}

	}

	public void onRowUnselect(UnselectEvent event) {
		System.out.println("Car unSelected"
				+ ((MapPostCategory) event.getObject()).getMapPostCategoryId());

	}

	public void deleteSelectedMapPostCategory() {
		for (MapPostCategory mapPostCategory : selectedMapPostCategoryList) {
			//System.out.println(mapPostCategory.getMapPostCategoryLabel());
			this.deleteMapPostCategory(mapPostCategory);
		}
	}
	public void deleteMapPostCategory(MapPostCategory mapPostCategory) {
			try{
			mapPostCategoryDataSource.delete(mapPostCategory);
			this.refreshDataSource();
			}catch(Exception ex)
			{
				log.info(ex.getMessage());
			}
		
	}
/*----------------------------------------*/
	public int getEditMapPostCategoryId() {
		return editMapPostCategoryId;
	}

	public void setEditMapPostCategoryId(int editMapPostCategoryId) {
		this.editMapPostCategoryId = editMapPostCategoryId;
	}
	
	public void editMapPostCategory(int editId)
	{
		Map<String,List<String>> params = new HashMap<String,List<String>>();
		Map<String,Object> options = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		String seditId=String.valueOf(editId);
		options.put("modal", true);
		list.add(seditId);
		params.put("editId", list);
		RequestContext.getCurrentInstance().openDialog("createMapPostCategory",
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



